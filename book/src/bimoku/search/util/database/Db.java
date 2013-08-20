package bimoku.search.util.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bimoku.search.util.log.LogOutPut;

public class Db {
	
	/**
	 * @param sql
	 * @return
	 */
	public static synchronized List<HashMap<String,Object>> ExecuteQuery(Connection conn,String sql){
		List<HashMap<String,Object>> datas=null;
		PreparedStatement sta=null;
		ResultSet rs=null;
		if(conn==null){
		 conn=DBPool.getInstance().getConnection();
		}
		try{
			sta=conn.prepareStatement(sql);
			rs=sta.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int recount = rsmd.getColumnCount();
			String[] colLabels = new String[recount];
			for (int i = 0; i < recount; i++) {
				colLabels[i] = rsmd.getColumnLabel(i + 1);
			}
			datas=new ArrayList<HashMap<String,Object>>();
			while (rs.next()) {
				rs.getObject(1);
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colLabels.length; i++) {
					data.put(colLabels[i], rs.getObject(colLabels[i]));
					// System.out.println(colLabels[i]
					// +"----->"+data.get(colLabels[i]));
				}
				datas.add(data);
			}
		}catch(Exception e){
			LogOutPut.outError(e.getMessage());
			e.printStackTrace();
		}finally{
			
			DBPool.close(conn, sta, rs);
			
		}
		return datas;
		
	}
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public static synchronized int  ExecuteNonQuery(Connection conn,String sql){
		int reNum=-1;
		if(conn==null) {
			conn=DBPool.getInstance().getConnection();
		}
		Statement stat=null;
		try{
			stat=conn.createStatement();
			reNum=stat.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBPool.close(conn, null, null);
		}
		return reNum;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static synchronized int ExecuteNonQuery(Connection conn,String sql,Object[] params){
		int reNum=-1;
		if(conn==null){
			conn=DBPool.getInstance().getConnection();
		}
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			reNum=ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBPool.close(conn, ps, null);
		}
		
		return reNum;
	}
	

	/**
	 * 执行带参数的sql语句返回List<HashMap<String,Object>>
	 * @param sql 传入SQL语句
	 * @param params 参数
	 * @return List<HashMap<String,Object>>
	 */
	public static synchronized List<HashMap<String,Object>> ExecuteQuery(Connection conn,String sql,Object[] params){
		List<HashMap<String,Object>> datas=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(conn==null){
			conn=DBPool.getInstance().getConnection();
		}
		try{
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int recount = rsmd.getColumnCount();
			String[] colLabels = new String[recount];
			for (int i = 0; i < recount; i++) {
				colLabels[i] = rsmd.getColumnLabel(i + 1);
			}
			datas = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colLabels.length; i++) {
					data.put(colLabels[i], rs.getObject(colLabels[i]));
				}
				datas.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBPool.close(conn, ps, rs);
			}
		return datas;
	}
	
	
	
	
	

}
