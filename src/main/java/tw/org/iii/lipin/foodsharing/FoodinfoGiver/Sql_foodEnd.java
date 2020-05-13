package tw.org.iii.lipin.foodsharing.FoodinfoGiver;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.foodcard_main;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Sql_foodEnd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String sql = null;
        int count = 0;
        String foodid = request.getParameter("foodid");
        String Userid = request.getParameter("userid");
        System.out.println(foodid+":::"+Userid);
        try {
            sql = "select foodcard.status" +
                    " from foodcard" +
                    " left join user_has_foodcards as uhf" +
                    " on foodcard.id = uhf.foodcard_id" +
                    " left join user as us" +
                    " on uhf.user_id = us.id" +
                    " where foodcard.id = ?" +
                    " and  us.id = ?";
                foodcard_main foodcard = template.queryForObject(sql,new BeanPropertyRowMapper<>(foodcard_main.class),foodid,Userid);

                if (foodcard.getStatus().equals("1")) {
                    sql = "update foodcard " +
                            " left join user_has_foodcards as uhf" +
                            " on foodcard.id = uhf.foodcard_id" +
                            " left join user as us" +
                            " on uhf.user_id = us.id" +
                            " set status = ?" +
                            " where foodcard.id = ?" +
                            " and  us.id = ?";
                    count = template.update(sql, 0, foodid, Userid);
                    if (count ==1){
                    out.println("結束分享");
                    }
                }else if (foodcard.getStatus().equals("0")){
                    sql = "update foodcard " +
                            " left join user_has_foodcards as uhf" +
                            " on foodcard.id = uhf.foodcard_id" +
                            " left join user as us" +
                            " on uhf.user_id = us.id" +
                            " set status = ?" +
                            " where foodcard.id = ?" +
                            " and  us.id = ?";
                    count = template.update(sql, 1, foodid, Userid);
                    if (count ==1){
                        out.println("分享中");
                    }
                }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
