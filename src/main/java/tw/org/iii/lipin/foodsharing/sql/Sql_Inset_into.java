package tw.org.iii.lipin.foodsharing.sql;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



public class Sql_Inset_into extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("hello.jsp").forward(request,response);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        PrintWriter out = response.getWriter();

        String account = request.getParameter("account");
        String passwd  = request.getParameter("passwd");
        String realname  = request.getParameter("realname");


        passwd = DigestUtils.md5Hex(passwd);


//        String sql = "insert into just(account,passed,realname) value (?,?,?)";

//        int count = template.update(sql,account,passwd,realname);


//        template.update();
//        template.queryForList();
//
//
//
//
//        out.println(count);
    }
}
