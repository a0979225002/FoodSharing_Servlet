package tw.org.iii.lipin.foodsharing.HomeFragment;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searching = request.getParameter("query");
        String userid = request.getParameter("userid");


        PrintWriter out = response.getWriter();
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = null;
        JSONArray array = new JSONArray();
        List<Map<String, Object>> maps = null;
        System.out.println(searching + userid);
        try {
            sql = "select fc.*,us.account,us.img,us.username,us.token" +
                    " from foodcard as fc" +
                    " left join user_has_foodcards as uhf" +
                    " on fc.id = uhf.foodcard_id" +
                    " left join user as us" +
                    " on uhf.user_id = us.id" +
                    " where not uhf.user_id = ?" +
                    " and fc.status = ?" +
                    " and fc.name like '%" + searching + "%'" +
                    " union" +
                    " select  fc.*,us.account,us.img,us.username,us.token" +
                    " from foodcard as fc" +
                    " left join user_has_foodcards as uhf" +
                    " on fc.id = uhf.foodcard_id" +
                    " left join user as us" +
                    " on uhf.user_id = us.id" +
                    " where not uhf.user_id = 60" +
                    " and fc.status = 1" +
                    " and fc.tag like '%" + searching + "%'" +
                    " order by id desc";

            maps = template.queryForList(sql, userid, 1);
            System.out.println(maps.size());

            for (Map<String, Object> map : maps) {

                array.put(map);

            }
            out.println(array.toString());
            System.out.println(array.length());
        }catch (Exception e){
            System.out.println("Search:"+e.toString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
