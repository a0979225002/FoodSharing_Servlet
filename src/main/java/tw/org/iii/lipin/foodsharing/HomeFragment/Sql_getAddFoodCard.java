package tw.org.iii.lipin.foodsharing.HomeFragment;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Sql_getAddFoodCard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        PrintWriter out = response.getWriter();
        JSONArray array = new JSONArray();
        String sql = null;
        List<Map<String, Object>> maps = null;
        String dist = request.getParameter("dist");
        try {
            //比對是否有地址傳回
            if (dist != null){
                sql = "select * from foodcard where (dist = ? and status = ?)";
                maps= template.queryForList(sql,dist,1);


            }else {
                //如果沒有取得客戶端地址,拿取最新發布的20筆
                sql = "select * from foodcard where status = ? order by id desc LIMIT ?";
                maps= template.queryForList(sql,1,20);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }

             for (Map<String,Object> map:maps){
                 array.put(map);
             }
             out.println(array.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
