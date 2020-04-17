package tw.org.iii.lipin.foodsharing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Sql_Telegram extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        PrintWriter out = response.getWriter();

        JSONArray jsonArray = new JSONArray();
        Map<String,Object> Jsonmap = null;

        try {

            String sql = "select * from just";

            List<Map<String, Object>> maps = template.queryForList(sql);

            Iterator iterator = maps.iterator();

//            while (iterator.hasNext()){
//                Map<String,Object> map = (Map<String, Object>) iterator.next();
//
//                map.get("id");
//                map.get("account");
//                jsonArray.put(map);
//            }

            for(Map<String,Object> map:maps){
                jsonArray.put(map);
            }
            out.println(jsonArray.toString());

        }catch (Exception e) {
            out.println(e.toString());
        }


    }
}
