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

public class updateTakeornot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String sql = null;
        int count = 0;
        String foodid = request.getParameter("foodid");
        String Userid = request.getParameter("userid");
        int takeornot = Integer.valueOf(request.getParameter("takeornot"));
        try {
            if (takeornot ==0) {
                sql = "update foodcard_has_takers as fht" +
                        " set takeornot = ?" +
                        " where foodcard_id = ?" +
                        " and user_id = ?";
                count = template.update(sql, 1,foodid, Userid);
                out.println(1);
            }else if (takeornot ==1){
                sql = "update foodcard_has_takers as fht" +
                        " set takeornot = ?" +
                        " where foodcard_id = ?" +
                        " and user_id = ?";
                count = template.update(sql, 0,foodid, Userid);
                out.println(0);
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
