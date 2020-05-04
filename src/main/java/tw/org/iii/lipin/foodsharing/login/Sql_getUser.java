package tw.org.iii.lipin.foodsharing.login;

import org.json.JSONArray;
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
import java.util.List;
import java.util.Map;

public class Sql_getUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        JSONArray array = new JSONArray();
        boolean verify = false;
        String sql = null;
        String account = null;
        try {
            account = request.getParameter("account");
            String password = request.getParameter("passwd");

            System.out.println(account);

            sql = "select * from user where account = ?";

            User_main table =   template.queryForObject(sql,new BeanPropertyRowMapper<>(User_main.class),account);

            String passwd = table.getPassword();//拿取該帳號的sql加密驗證碼
            verify = MD5Utils.md5_verify(password,MD5Utils.md5key,passwd);


        }catch (Exception e) {
            System.out.println(e.toString());
        }
        if (verify){

            List<Map<String,Object>> list =  template.queryForList(sql,account);

            for (Map<String,Object> maps : list){
                array.put(maps);
            }
            out.println(array.toString());

            System.out.println("測試＝＝＝＝＝＝"+array.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
