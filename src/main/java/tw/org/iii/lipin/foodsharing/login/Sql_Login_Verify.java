package tw.org.iii.lipin.foodsharing.login;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tw.org.iii.lipin.domain.User_main;
import utils.JDBCUtils;
import utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Sql_Login_Verify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        boolean verify = false;
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("passwd") ;//拿取客戶端密碼

            String sql = "select * from user where account = ?";

            User_main table =   template.queryForObject(sql,new BeanPropertyRowMapper<>(User_main.class),account);

            String passwd = table.getPassword();//拿取該帳號的sql加密驗證碼

            verify = MD5Utils.md5_verify(password,MD5Utils.md5key,passwd);
        }catch (Exception e){

        }
        System.out.println(verify);
        //驗證加密是否與原密碼一樣
        if (verify == true){
            out.println(1);
        }else if (verify == false){
            out.println(0);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }
}
