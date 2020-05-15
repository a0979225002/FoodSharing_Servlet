package tw.org.iii.lipin.foodsharing.Sql_foodinfoTaker;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.foodcard_has_takers;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class gettakeornot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String giverid = request.getParameter("giverid");
        String foodcardID = request.getParameter("foodcardID");
        System.out.println(giverid+":::"+foodcardID);
        String sql = null;
        try {
            sql = "select * from foodcard_has_takers" +
                    " where foodcard_id = ?" +
                    " and  user_id = ?";

            foodcard_has_takers fht =
                    template.queryForObject(sql,new BeanPropertyRowMapper<>(foodcard_has_takers.class),
                            foodcardID,giverid);

            System.out.println(fht.getInline());
            out.println(fht.getTakeornot());
        }catch (Exception e){
            System.out.println(e.toString());
            out.println(0);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
