package tw.org.iii.lipin.foodsharing.Notification;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String foodcardID = request.getParameter("foodid");
        String comment = request.getParameter("comment");


        System.out.println(userid+":::"+foodcardID);
        String sql = null;
        try {
            sql = "update foodcard_has_takers as fht" +
                    " set fht.comment = ? " +
                    " where fht.foodcard_id = ?" +
                    " and  fht.user_id = ?";
            int count =template.update(sql,comment,foodcardID,userid);
            out.println(count);
        }catch (Exception e){
            System.out.println("UpdateComment:"+e.toString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
