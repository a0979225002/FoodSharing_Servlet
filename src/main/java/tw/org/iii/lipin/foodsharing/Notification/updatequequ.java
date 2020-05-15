package tw.org.iii.lipin.foodsharing.Notification;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class updatequequ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String foodcardID = request.getParameter("foodid");
        System.out.println(userid+":::"+foodcardID);
        String sql = null;
        try {
            sql = " update foodcard_has_takers" +
                    " set giveraccept = ?" +
                    " where foodcard_id = ?" +
                    " and  user_id = ?";

            int count = template.update(sql,1,foodcardID,userid);

            out.println(count);

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
