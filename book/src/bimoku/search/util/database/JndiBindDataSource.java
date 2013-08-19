package bimoku.search.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

/**
 * 如何获得c3p0的数据源并将其绑定到一个JNDI名称服务。
 * @author sun
 *
 */
public final class JndiBindDataSource {
	
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 public static void main(String[] argv) {  
	        try {  
	            /** 
	             * 让一个命令行参数指定名称，将我们的数据源的绑定。 
	             */  
	            String jndiName = argv[0];  
	            /** 
	             * 获取数据源使用预设池params ... 
	             * 这是唯一的C3P0的特定代码在这里 
	             */  
	            DataSource unpooled = DataSources  
	                    .unpooledDataSource("jdbc:mysql://127.0.0.1:3306/game?user=root&password=123456&useUnicode=true", "root", "123456");  
	            DataSource pooled = DataSources.pooledDataSource(unpooled);  
	            System.out.println("是否能够获得Connection : "+pooled.getConnection());  
	            /** 
	             * 创建的InitialContext，并绑定的数据源，以它在通常的方式。 
	             * 我们使用的是没有的的InitialContext的构造参数版本，因此通过jndi.properties的文件， 
	             * 系统属性，或通过其他手段，JNDI环境，必须先设置。 
	             */  
	            InitialContext ctx = new InitialContext();  
	            System.out.println("jndiName : " + jndiName);  
	            ctx.rebind(jndiName, pooled);  
	            System.out.println("DataSource bound to nameservice under the name \"" + jndiName + '\"');  
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
	  
	    private JndiBindDataSource() {  
	    
	
	    }

}
