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

public class SQl_queueTaker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String queue = request.getParameter("queue");     //索取數量
        String foodcardID = request.getParameter("foodcardID");  //此食物卡片的id
        String giverid = request.getParameter("giverid"); //想要排隊的userid
        String nowdate = request.getParameter("nowdate");
        String sql = null;

        try {
            sql = "select * from foodcard_has_takers" +
                    " where foodcard_id = ?" +
                    " and  user_id = ?";

            foodcard_has_takers fht =
                    template.queryForObject(sql,new BeanPropertyRowMapper<>(foodcard_has_takers.class),
                            foodcardID,giverid);

            if (fht.getQty() == 0) {
                sql = "insert into foodcard_has_takers " +
                        " (foodcard_id, user_id, qty, inline, giveraccept, takeornot, createtime )" +
                        " value (?,?,?,?,?,?,?)";

                int count = template.update(sql, foodcardID, giverid, queue, 1, 0, 0, nowdate);
                out.println(count);
            }else if (fht.getQty() != 0 ){

                sql = " update foodcard_has_takers" +
                        " set inline = ?," +
                        " qty = ?" +
                        " where foodcard_id = ?" +
                        " and  user_id = ?";

                int count = template.update(sql,1,queue,foodcardID,giverid);

                System.out.println(count);
                out.println(count);
            }

        }catch (Exception e){
            System.out.println(e.toString());
            out.println(0);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
