package bimoku.search.util.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bimoku.search.util.log.LogOutPut;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	 private static DBPool dbPool;  
	    private ComboPooledDataSource dataSource;  
	    static {  
	        dbPool = new DBPool();  
	    }  
	    public DBPool() {  
	        try {  
	            dataSource = new ComboPooledDataSource();  
	            dataSource.setUser("root");  
	            dataSource.setPassword("root");  
	            dataSource.setJdbcUrl("jdbc:mysql://192.168.1.108:3306/bimokudb?user=root&password=root&useUnicode=true");  
	            dataSource.setDriverClass("com.mysql.jdbc.Driver");  
	            // 设置初始连接池的大小！  
	            dataSource.setInitialPoolSize(2);  
	            // 设置连接池的最小值！   
	            dataSource.setMinPoolSize(2);  
	            // 设置连接池的最大值！   
	            dataSource.setMaxPoolSize(10);  
	            // 设置连接池中的最大Statements数量！  
	            dataSource.setMaxStatements(50);  
	            // 设置连接池的最大空闲时间！  
	            dataSource.setMaxIdleTime(60);  
	        } catch (PropertyVetoException e) { 
	        	LogOutPut.outError(e.getMessage());
	            throw new RuntimeException(e);  
	            
	        }  
	    }
	    
	    public DBPool(
	    		String username,
	    		String password,
	    		String jdbcurl,
	    		String driverStr,
	    		int poolSize,
	    		int minPoolSize,
	    		int maxPoolSize,
	    		int maxStatements,
	    		int maxIdleTime
	    		) {  
	        try {  
	            dataSource = new ComboPooledDataSource();  
	            dataSource.setUser("root");  
	            dataSource.setPassword("root");  
	            dataSource.setJdbcUrl("jdbc:mysql://192.168.1.108:3306/bimokudb?user=root&password=root&useUnicode=true");  
	            dataSource.setDriverClass("com.mysql.jdbc.Driver");  
	            // 设置初始连接池的大小！  
	            dataSource.setInitialPoolSize(2);  
	            // 设置连接池的最小值！   
	            dataSource.setMinPoolSize(1);  
	            // 设置连接池的最大值！   
	            dataSource.setMaxPoolSize(10);  
	            // 设置连接池中的最大Statements数量！  
	            dataSource.setMaxStatements(50);  
	            // 设置连接池的最大空闲时间！  
	            dataSource.setMaxIdleTime(60);  
	        } catch (PropertyVetoException e) { 
	        	LogOutPut.outError(e.getMessage());
	            throw new RuntimeException(e);  
	        }  
	    } 
	  
	    
	    
	    public DBPool(String username,String password,String jdbcurl) {  
	        try {  
	            dataSource = new ComboPooledDataSource();  
	            dataSource.setUser(username);  
	            dataSource.setPassword(password);  
	            dataSource.setJdbcUrl(jdbcurl);  
	            dataSource.setDriverClass("com.mysql.jdbc.Driver");  
	            // 设置初始连接池的大小！  
	            dataSource.setInitialPoolSize(2);  
	            // 设置连接池的最小值！   
	            dataSource.setMinPoolSize(1);  
	            // 设置连接池的最大值！   
	            dataSource.setMaxPoolSize(10);  
	            // 设置连接池中的最大Statements数量！  
	            dataSource.setMaxStatements(50);  
	            // 设置连接池的最大空闲时间！  
	            dataSource.setMaxIdleTime(60);  
	        } catch (PropertyVetoException e) { 
	        	LogOutPut.outError(e.getMessage());
	            throw new RuntimeException(e);  
	            
	        }  
	    }
	    
	    
	    public final static DBPool getInstance() {  
	        return dbPool;  
	    }  
	  
	    public final Connection getConnection() {  
	        try {  
	            return dataSource.getConnection();  
	        } catch (SQLException e) {  
	        	LogOutPut.outError(e.getMessage());
	            throw new RuntimeException("无法从数据源获取连接 ", e);  
	        }  
	    }  
	  
	    public static void main(String[] args) throws SQLException {  
	        Connection con = null;  
	        try {  
	            con = DBPool.getInstance().getConnection();  
	            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM t_book limit 1,300");  
	            while (rs.next()) {  
	                System.out.println(rs.getObject(1));  
	                System.out.println(rs.getObject(2));  
	                System.out.println(rs.getObject(3)); 
	                System.out.println(rs.getObject(4));
	                System.out.println(rs.getObject(5));
	            }  
	        } catch (Exception e) {  
	        	LogOutPut.outError(e.getMessage());
	        	e.printStackTrace();
	        } finally {  
	            if (con != null)  
	                con.close(); 
	        }  
	    }
	    
	    public static synchronized void close(Connection conn,Statement stmt,ResultSet rs){
	    	
	    		try {
	    			if(rs!=null)
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					LogOutPut.outError(e.getMessage());
				}finally{
					try{
						if(stmt!=null)
							stmt.close();
					}catch(Exception e){
						e.printStackTrace();
						LogOutPut.outError(e.getMessage());
					}finally{
						try{
							if(conn!=null)
								conn.close();
						}catch(Exception e){
							LogOutPut.outError(e.getMessage());
							e.printStackTrace();
						}
					}
				}
	    	
	    }
}
