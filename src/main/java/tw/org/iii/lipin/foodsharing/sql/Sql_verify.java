package tw.org.iii.lipin.foodsharing.sql;


import org.springframework.jdbc.core.JdbcTemplate;


import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Sql_verify extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
//        request.getRequestDispatcher("/hello.jsp").forward(request,response);
//        request.setCharacterEncoding("text/html;charset=UTF-8");
        try {

            String sql ="select * from just where account='bbbb'";

//            items tatal =
//                    template.queryForObject(sql,new BeanPropertyRowMapper<items>(items.class));
//
//            out.println(tatal);



        }catch (Exception e){
            out.println(e.toString());
        }
    }
}
