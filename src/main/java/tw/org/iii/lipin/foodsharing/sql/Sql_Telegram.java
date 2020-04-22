package tw.org.iii.lipin.foodsharing.sql;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Sql_Telegram extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        PrintWriter out = response.getWriter();

        JSONArray jsonArray = new JSONArray();

        try {

            String sql = "select * from just";

//            List<Map<String, Object>> maps = template.queryForList(sql);

             List<Map<String,Object>>maps  = template.queryForList(sql);



            for(Map<String,Object> map:maps){
                jsonArray.put(map);
            }
            out.println(jsonArray.toString());

        }catch (Exception e) {
            out.println(e.toString());
        }

        response.flushBuffer();
    }
}
