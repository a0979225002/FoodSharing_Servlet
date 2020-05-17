package tw.org.iii.lipin.foodsharing.Notification;

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

public class UpdateComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String foodcardID = request.getParameter("foodid");
        String comment = request.getParameter("comment");
        String Rating = request.getParameter("Rating");
        String giverid = request.getParameter("giverid");
        int count;
        System.out.println(giverid+":::"+userid+":::"+comment+":::"+Rating+":::"+foodcardID);
        String sql = null;
        try {
            sql = "update foodcard_has_takers as fht" +
                    " set fht.comment = ?," +
                    " fht.tokerfraction = ?" +
                    " where fht.foodcard_id = ?" +
                    " and  fht.user_id = ?";
           count =template.update(sql,comment,Rating,foodcardID,userid);

            //如果留言存取成功,將評論分數加入user中分數
            if (count ==1){
             sql = "select user.fraction from user " +
                     "where user.id = ?";
             User_main user_main = template.queryForObject(sql,new BeanPropertyRowMapper<>(User_main.class),giverid);
             float rating = Float.valueOf(Rating);
             int newrating = (int) (rating*10);
             int fraction = user_main.getFraction()+newrating;

             sql = "update user " +
                     " set user.fraction = ? " +
                     " where user.id = ?";
              count = template.update(sql,fraction,giverid);

              out.println(count);
            }else {
                out.println(count);
            }
        }catch (Exception e){
            System.out.println("UpdateComment:"+e.toString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
