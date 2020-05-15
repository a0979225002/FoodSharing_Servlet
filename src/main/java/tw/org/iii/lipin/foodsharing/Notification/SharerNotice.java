package tw.org.iii.lipin.foodsharing.Notification;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.foodcard_has_takers;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class SharerNotice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JSONArray array = new JSONArray();
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String sql = null;
        foodcard_has_takers fht = null;

        try {
            sql = "select fhs.*,foodcard.*,u.account,u.img,u.username,u.token" +
                    " from foodcard_has_takers fhs" +
                    " left join user u on fhs.user_id = u.id "+
                    " left join foodcard on fhs.foodcard_id = foodcard.id "+
                    " left join user_has_foodcards uhf on foodcard.id = uhf.foodcard_id "+
                    " where fhs.giveraccept = ? " +
                    " and fhs.inline = ?" +
                    " and foodcard.status = ?" +
                    " and uhf.user_id in ( "+
                    " select u2.id from user as u2 "+
                    " where u2.id = ? "+
                    " ); ";
            List<Map<String,Object>> maps = template.queryForList(sql,0,1,1,userid);

            for (Map<String,Object>map :maps){
                array.put(map);
            }
            System.out.println(array.toString());
            out.println(array.toString());

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
