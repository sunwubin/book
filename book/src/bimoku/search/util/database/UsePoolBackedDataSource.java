package bimoku.search.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;
/** 
 * 此示例演示如何以编程方式，直接使用数据库连接池中的数据源 
 */  
public class UsePoolBackedDataSource {
	public static void main(String[] argv) {  
        try {  
            //  
            // 收购之前，您的数据源！  
  
            // 获取数据源...这是唯一的C3P0的特定代码在这里  
            DataSource unpooled = DataSources.unpooledDataSource(  
                    "jdbc:mysql://127.0.0.1:3306/lesogo_game2?user=root&password=123456&useUnicode=true", "root",  
                    "123456");  
            DataSource pooled = DataSources.pooledDataSource(unpooled);  
  
            Connection con = null;  
            Statement stmt = null;  
            ResultSet rs = null;  
            try {  
                con = pooled.getConnection();  
                stmt = con.createStatement();  
                rs = stmt.executeQuery("SELECT * FROM LESOGO_USER");  
                while (rs.next())  
                    System.out.println(rs.getString(1));  
            } finally {  
                attemptClose(rs);  
                attemptClose(stmt);  
                attemptClose(con);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    static void attemptClose(ResultSet o) {  
        try {  
            if (o != null)  
                o.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    static void attemptClose(Statement o) {  
        try {  
            if (o != null)  
                o.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    static void attemptClose(Connection o) {  
        try {  
            if (o != null)  
                o.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    private UsePoolBackedDataSource() {  
    }  
}
