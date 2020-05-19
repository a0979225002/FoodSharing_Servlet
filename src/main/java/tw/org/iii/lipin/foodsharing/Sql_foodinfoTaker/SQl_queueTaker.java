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
import java.sql.SQLException;

public class SQl_queueTaker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        String queue = request.getParameter("queue");     //索取數量
        String foodcardID = request.getParameter("foodcardID");  //此食物卡片的id
        String giverid = request.getParameter("giverid"); //想要排隊的userid
        String nowdate = request.getParameter("nowdate");
        String sql = null;
        foodcard_has_takers fht = null;
        int count = 0;

        System.out.println("有來媽");
        try {

            //查詢是否有該筆數
                sql = "select count(*) from foodcard_has_takers" +
                        " where foodcard_id = ?" +
                        " and  user_id = ?";

                count = template.queryForObject(sql,Integer.class,foodcardID,giverid);

            //如果沒有就新增
                if (count == 0){
                    sql = "insert into foodcard_has_takers " +
                            " (foodcard_id, user_id, takerqty, inline, giveraccept, takeornot, createtime )" +
                            " value (?,?,?,?,?,?,?)";

                    count = template.update(sql, foodcardID, giverid, queue, 1, 0, 0, nowdate);
                    out.println(count);

                    System.out.println(count);
             //如果有的話
                }else if (count !=0){
                    sql = "select * from foodcard_has_takers" +
                            " where foodcard_id = ?" +
                            " and user_id = ?";
                    fht = template.queryForObject(sql,new BeanPropertyRowMapper<>(foodcard_has_takers.class),foodcardID,giverid);
                    System.out.println(fht.getInline()+"查驗"+fht.getInline());
                    if (fht.getInline() ==1){
                        sql = " update foodcard_has_takers" +
                                " set inline = ?," +
                                " takerqty = ?" +
                                " where foodcard_id = ?" +
                                " and  user_id = ?";

                        count = template.update(sql,0,queue,foodcardID,giverid);

                        System.out.println(count);
                        out.println(count);
                    }else if (fht.getInline() ==0){
                        sql = " update foodcard_has_takers" +
                                " set inline = ?," +
                                " takerqty = ?" +
                                " where foodcard_id = ?" +
                                " and  user_id = ?";

                        count = template.update(sql,1,queue,foodcardID,giverid);

                        System.out.println(count);
                        out.println(count);
                    }

                }

        }catch (Exception e){
            System.out.println(e.toString()+123);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
