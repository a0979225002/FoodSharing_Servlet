package tw.org.iii.lipin.foodsharing.HomeFragment;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class due_DateListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    JdbcTemplate template = null;
    String sql = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        template = new JdbcTemplate(JDBCUtils.getDataSource());
        Timer timer = new Timer();
        Timertack sql = new Timertack();
        timer.schedule(sql,0,1000*60);

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
    public class Timertack extends TimerTask {
        @Override
        public void run() {
            try {
                if (template == null){template = new JdbcTemplate(JDBCUtils.getDataSource());}
                sql = "select id,due_date from foodcard where(status = ?)";
                List<Map<String,Object>> maps =  template.queryForList(sql,1);
                for (Map<String, Object> map:maps){
                    Date nowdate = new Date();//取得現在時間
                    Date duetime = null;
                    try {
                        duetime = sdf.parse(map.get("due_date").toString());//將sql中截止日其轉成date格式
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (duetime.before(nowdate)){//如果截止日期小於現在時間時 ＝true
                        sql = "update foodcard set status = ? where id = ?";
                        template.update(sql,0,map.get("id").toString());


                    }
            }

            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}
