package tw.org.iii.lipin.foodsharing.newPreview;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import tw.org.iii.lipin.domain.foodcard_main;

public class Sql_AddFoodCard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();

        String foodimg = request.getParameter("foodimg");
        String foodname = request.getParameter("foodname");
        String category = request.getParameter("category");
        String city = request.getParameter("city");
        String dist = request.getParameter("dist");
        String address = request.getParameter("address");
        String dueDate = request.getParameter("dueDate");
        String tag = request.getParameter("tag");
        String qty = request.getParameter("qty");
        String split = request.getParameter("split");
        String detail = request.getParameter("detail");
        String createtime = request.getParameter("createtime");
        String userID = request.getParameter("userID");

        String status = "1";// 卡片發布狀態 發布時是1,不發布是0

        String sql = null;

        System.out.println(foodname);

    try {
         sql = "insert into foodcard " +
                "(name,category,city,dist,address,due_date,tag,qty,split,detail,status,createtime,foodimg)" +
                "value (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //將物卡片加入
        int count = template.update(sql,foodname,category,city,dist,address,dueDate,tag,qty,split,detail,status,createtime,foodimg);
        //如果有加入成功 == 1
        if (count ==1){
            //拿取當下加入的卡片id
             sql = "select * from foodcard where(name=? and createtime =?)  ";
             foodcard_main table =  template.queryForObject(
                     sql,new BeanPropertyRowMapper<>(foodcard_main.class),foodname,createtime);
             int foodid = table.getId();

             //如果有拿取到卡片,沒拿到id == 0
             if (foodid != 0){
                 //加入關聯表中
                 sql = "insert into user_has_foodcards (user_id,foodcard_id) value (?,?)";
                 int count2 = template.update(sql,userID,foodid);
                 out.println(count2);
                 System.out.println(count2+"加入成功摟"+userID+"::"+foodid);
             }else {
                 out.println(foodid);
             }
        }else {
            out.println(0);
        }
    }catch (Exception e){
        System.out.println(e.toString());
    }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
