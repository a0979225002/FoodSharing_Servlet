package tw.org.iii.lipin.foodsharing.ShareHistoryFragment;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Sql_ShareFoodcard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JSONArray array = new JSONArray();
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String sql = null;
        try {
            sql = "select fc.*" +
                    " from foodcard as fc " +
                    " left join user_has_foodcards as uhf " +
                    " on fc.id = uhf.foodcard_id" +
                    " left join user as us" +
                    " on uhf.user_id = us.id" +
                    " where uhf.user_id = ?" +
                    " order by fc.id desc ";
            List<Map<String,Object>> maps = template.queryForList(sql,userid);

            for (Map<String,Object>map:maps){
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
