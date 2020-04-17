package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * SQL連接工具類
 */
public class JDBCUtils {
    private static DataSource DataSource;

    static {
        //加載配置文件
        Properties properties = new Properties();
        InputStream sql_res =
                JDBCUtils.class.getClassLoader()
                        .getResourceAsStream("druid.properties");
        try {
            properties.load(sql_res);
            //使用配置文件內的參數,連接ＳＱＬ
            DataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.toString();
        }
    }
    //得到數據方法
    public static DataSource getDataSource(){
        return DataSource;
    }

    /**
     * 暫時用不到,可作聊天功能
     */
    //連接ＳＱＬ方法
    public static Connection getConnection() throws SQLException {
     return DataSource.getConnection() ;
    }
}
