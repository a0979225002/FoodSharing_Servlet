package tw.org.iii.lipin.foodsharing.FoodinfoGiver;

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

public class Sql_getTaker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        JSONArray array = new JSONArray();
        String sql = null;
        List<Map<String, Object>> maps = null;
        String foodid = request.getParameter("foodid");
        String Userid = request.getParameter("userid");

        try {
            sql = "select fht.*,us.account,us.img,us.username,us.token" +
                    " from foodcard_has_takers as fht " +
                    " left join user as us" +
                    " on fht.user_id = us.id" +
                    " where fht.foodcard_id = ?" +
                    " and fht.inline = ?" +
                    " order by fht.createtime ";
            maps= template.queryForList(sql,foodid,1);

        }catch (Exception e){
            System.out.println(e.toString());
        }
        for (Map<String,Object> map:maps){
            array.put(map);
        }
        out.println(array.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
