package bimoku.search.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/** 
 * 此示例演示如何以编程方式，直接使用unpooled数据源 
 */
public final class UseJndiDataSource {
 
	public static void main(String[] argv){
		try{
			/** 
             * 让一个命令行参数指定名称，我们将查找的数据源。 
             */ 
			String jndiName=argv[0];
			 /** 
             * 创建InitialContext的，和查找惯常做法的DataSource。 我们使用的是没有的的InitialContext的构造参数版本，因此通过jndi.properties的文件， 
             * 系统属性，或通过其他手段，JNDI环境，必须先设置。 
             */  
            InitialContext ctx = new InitialContext();  
  
            /** 
             * 获取数据源...这是唯一的C3P0的特定代码在这里 
             */  
            DataSource ds = (DataSource) ctx.lookup(jndiName);  
  
            /** 
             * 掌握了这样一个连接东西，通常的方式 
             */  
            Connection con = null;  
            Statement stmt = null;  
            ResultSet rs = null;  
            try {  
                con = ds.getConnection();  
                stmt = con.createStatement();  
                rs = stmt.executeQuery("SELECT * FROM LESOGO_USER");  
                while (rs.next())  
                    System.out.println(rs.getString(1));  
            } finally {  
                /** 
                 * 我试图约在资源管理，`神经质显式关闭每个资源，但如果你只收盘家长资源的习惯（如连接）， 让他们接近自己的孩子，都C3P0的数据源一定会妥善处理。 
                 */  
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
  
    private UseJndiDataSource() {  
    }  
		
	
}
