package tw.org.iii.lipin.foodsharing.Notification;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.foodcard_has_takers;
import tw.org.iii.lipin.domain.foodcard_main;
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
        String qty = request.getParameter("qty");
        System.out.println(userid+":::"+foodcardID);
        String sql = null;
        try {
            sql = "select foodcard.qty from foodcard_has_takers as fht" +
                    " left join foodcard" +
                    " on fht.foodcard_id = foodcard.id" +
                    " where fht.foodcard_id = ?" +
                    " and  fht.user_id = ?";

            foodcard_main foodcard =  template.queryForObject(
                    sql,new BeanPropertyRowMapper<>(foodcard_main.class),foodcardID,userid);

            System.out.println(foodcard.getQty()+":::"+qty);

            int newqty = foodcard.getQty()- Integer.valueOf(qty);

            sql = " update foodcard_has_takers as fht" +
                    " left join foodcard" +
                    " on fht.foodcard_id = foodcard.id" +
                    " set fht.giveraccept = ?," +
                    " foodcard.qty = ?" +
                    " where foodcard_id = ?" +
                    " and  user_id = ?";

            int count = template.update(sql,1,newqty,foodcardID,userid);

            out.println(count);

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
