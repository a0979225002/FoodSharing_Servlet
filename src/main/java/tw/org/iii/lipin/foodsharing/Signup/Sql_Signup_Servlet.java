package tw.org.iii.lipin.foodsharing.Signup;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;
import utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * user表加入註冊的帳號密碼
 */
public class Sql_Signup_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        Date date = null;

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String createtime = request.getParameter("createTime");
        String md5passwd = MD5Utils.md5(password,MD5Utils.md5key);

        try {
            date =  getDataTime(createtime);
        } catch (ParseException e) {
            out.println(e.toString());
        }


        String sql = "insert into user(account,password,phone,createtime)" +
                        "value (?,?,?,?)";

        int count =  template.update(sql,account,md5passwd,phone,date);

        out.println(count);

        System.out.println(createtime +":aaaaaa");

    }

    /**
     * 將日期轉sql日期存取格式
     * @param createtime
     * @return
     * @throws ParseException
     */
    private Date getDataTime(String createtime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //轉java date格式
        java.util.Date javaDate = simpleDateFormat.parse(createtime);
        //轉 sql格式
        Date sqlDate = new Date(javaDate.getTime());

        return sqlDate;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
    }
}
