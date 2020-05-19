package tw.org.iii.lipin.foodsharing.ProfileFragment;

import org.json.JSONArray;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.User_main;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class getTransactionRatio extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String sql = null;

        try {
            sql = "select count(fht.tokerfraction) from foodcard_has_takers as fht" +
                    " left join foodcard f on fht.foodcard_id = f.id" +
                    " left join user_has_foodcards uhf on f.id = uhf.foodcard_id" +
                    " left join user u on uhf.user_id = u.id" +
                    " where u.id = ?" +
                    " and fht.comment is not null" +
                    " and fht.takeornot =?";

            int count = template.queryForObject(sql,new BeanPropertyRowMapper<>(Integer.class),userid,1);

            out.println(count);

        }catch (Exception e){
            System.out.println("getTransactionRatio:"+e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
