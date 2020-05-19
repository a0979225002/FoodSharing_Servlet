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

public class CommentNotice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JSONArray array = new JSONArray();
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String sql = null;
        foodcard_has_takers fht = null;
        try {
            sql = "select fht.*," +
                    " fc.name,fc.category,fc.city,fc.dist,fc.address,fc.split,fc.due_date,fc.tag,fc.qty,fc.detail,fc.status,fc.foodimg,fc.createtime," +
                    " us.id,us.account,us.img,us.username,us.token" +
                    " from foodcard_has_takers as fht " +
                    " left join foodcard as fc" +
                    " on fht.foodcard_id = fc.id" +
                    " left join user_has_foodcards as uhf" +
                    " on fc.id = uhf.foodcard_id" +
                    " left join user as us" +
                    " on uhf.user_id = us.id" +
                    " where fht.takeornot = ?" +
                    " and fht.giveraccept = ?" +
                    " and fht.user_id = ?" +
                    " and fht.comment is null  " +
                    " order by fht.modified desc ";

            List<Map<String,Object>> maps = template.queryForList(sql,1,1,userid);

            for (Map<String,Object>map :maps){
                array.put(map);
            }
            out.println(array.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
