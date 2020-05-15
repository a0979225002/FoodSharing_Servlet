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

public class Sql_cancelQueue extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String giverid = request.getParameter("giverid");
        String foodcardID = request.getParameter("foodcardID");
        System.out.println(giverid+":::"+foodcardID);
        String sql = null;
        System.out.println("我在這");
        try {
            sql = " update foodcard_has_takers" +
                    " set inline = ?," +
                    " giveraccept = ?" +
                    " where foodcard_id = ?" +
                    " and  user_id = ?";

            int count = template.update(sql,0,0,foodcardID,giverid);

            System.out.println(count);
            out.println(count);
        }catch (Exception e){
            System.out.println(e.toString());
            out.println(0);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
