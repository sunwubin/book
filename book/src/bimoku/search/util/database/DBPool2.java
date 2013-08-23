package bimoku.search.util.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import bimoku.search.util.database.config.ConfigProperty;
import bimoku.search.util.log.LogOutPut;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool2 {
	private static DBPool2 dbpool;
	private static LinkedList<Connection> connections_list=null;
	
	private ComboPooledDataSource dataSource; 
	static {
		ConfigProperty config=new ConfigProperty();
		dbpool=new DBPool2(
				config.getPropertyValue("bookdb2.username"),
				config.getPropertyValue("bookdb2.password"),
				config.getPropertyValue("bookdb2.jdbcurl"));
	}
	
	public DBPool2(String username,String password,String jdbcurl) {  
        try {  
            dataSource = new ComboPooledDataSource();  
            dataSource.setUser(username);  
            dataSource.setPassword(password);  
            dataSource.setJdbcUrl(jdbcurl);  
            dataSource.setDriverClass("com.mysql.jdbc.Driver");  
            // 设置初始连接池的大小！  
            dataSource.setInitialPoolSize(10);  
            // 设置连接池的最小值！   
            dataSource.setMinPoolSize(1);  
            // 设置连接池的最大值！   
            dataSource.setMaxPoolSize(20);  
            // 设置连接池中的最大Statements数量！  
            dataSource.setMaxStatements(50);  
            // 设置连接池的最大空闲时间！  
            dataSource.setMaxIdleTime(60); 
            connections_list=new LinkedList<Connection>();
            try {
            	for(int i=0;i<20;i++)
				 connections_list.addFirst(dataSource.getConnection());
			} catch (SQLException e) {
				LogOutPut.outError(e.getMessage());
				e.printStackTrace();
			}
        } catch (PropertyVetoException e) { 
        	LogOutPut.outError(e.getMessage());
            throw new RuntimeException(e);  
        }  
    }
	
	public final static DBPool2 getInstance(){
		 return dbpool;
	}
	  public final Connection getConnection() {  
	        try {  
	        	System.out.println("取得链接");
	            //return dataSource.getConnection();
	        	return connections_list.getLast();
	        } catch (Exception e) {  
	        	LogOutPut.outError(e.getMessage());
	            throw new RuntimeException("无法从数据源获取连接 ", e);  
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
						if(conn!=null){
							connections_list.addFirst(conn);
							System.out.println("连接池个数==========="+connections_list.size());
						}
					}catch(Exception e){
						LogOutPut.outError(e.getMessage());
						e.printStackTrace();
					}
				}
			}
  	
  }
	  
	
	  
	 
	
	public static void main(String[] args) throws SQLException {  
        Connection con = null;  
        try {  
            con = DBPool2.getInstance().getConnection();  
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM t_book limit 0,10");  
            while (rs.next()) {  
            	System.out.println(rs.getObject(1)+"||"+rs.getObject(2)+"||"+
            	rs.getObject(3)+"||"+rs.getObject(4)+"||"+rs.getObject(5)+"||"+
            			rs.getObject(6)+"||"+rs.getObject(7));
            }  
        } catch (Exception e) {  
        	LogOutPut.outError(e.getMessage());
        	e.printStackTrace();
        } finally {  
            if (con != null)  
                con.close(); 
        }  
    }

}
