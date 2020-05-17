package tw.org.iii.lipin.foodsharing.ProfileFragment;

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

public class getUserFraction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String sql = null;
        JSONArray array = new JSONArray();
        try {
            sql = "select user.fraction from user " +
                    "where user.id = ?";
            List<Map<String,Object>>maps =  template.queryForList(sql,userid);

            for (Map<String,Object>map: maps){
                array.put(map);
            }
            out.println(array.toString());
            System.out.println("我有來"+array.toString());

        }catch (Exception e){

            System.out.println("getUserFraction:"+e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
