package tw.org.iii.lipin.foodsharing.newPreview;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveEditFoodCard extends HttpServlet {
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
        String foodid = request.getParameter("foodid");

        String status = "1";// 卡片發布狀態 發布時是1,不發布是0

        String sql = null;

        System.out.println(address);

        try {
            sql = "update foodcard " +
                    " left join user_has_foodcards as uhf" +
                    " on foodcard.id = uhf.foodcard_id" +
                    " left join user " +
                    " on uhf.user_id = user.id" +
                    " set foodcard.name = ? ," +
                    " foodcard.category = ? ," +
                    " foodcard.city = ? ," +
                    " foodcard.dist = ? ," +
                    " foodcard.address = ? ," +
                    " foodcard.due_date = ? ," +
                    " foodcard.tag = ? ," +
                    " foodcard.qty = ? ," +
                    " foodcard.split = ? ," +
                    " foodcard.detail = ? ," +
                    " foodcard.status = ? ," +
                    " foodcard.foodimg = ? " +
                    " where foodcard.id = ? " ;
            int count =  template.update(sql,foodname,category,city,dist,address,dueDate,tag,qty,split,detail,status,foodimg,foodid);

            out.println(count);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
