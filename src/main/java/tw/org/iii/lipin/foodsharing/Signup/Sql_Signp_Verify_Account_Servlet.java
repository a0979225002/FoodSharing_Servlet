package tw.org.iii.lipin.foodsharing.Signup;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * user表查詢帳號與電話是否有跟sql重複
 */
public class Sql_Signp_Verify_Account_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();

        String account = request.getParameter("account");

        String sql ="select count(*) " +
                    "from user " +
                    "where (account=?)";

        long count =  template.queryForObject(sql,Long.class,account);

        out.println(count);
        System.out.println(account);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
