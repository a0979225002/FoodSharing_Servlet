package tw.org.iii.lipin.foodsharing.newPreview;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Sql_AddFoodCard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();

        String base64img = request.getParameter("base64img");
        String foodname = request.getParameter("foodname");
        String city = request.getParameter("city");
        String dist = request.getParameter("dist");
        String address = request.getParameter("address");
        String dueDate = request.getParameter("dueDate");
        String tag = request.getParameter("tag");
        String qty = request.getParameter("qty");
        String split = request.getParameter("split");
        String createtime = request.getParameter("createtime");
        String userID = request.getParameter("userID");


        String sql = "insert into ";

        int count = template.update(sql);

        out.println(count);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
