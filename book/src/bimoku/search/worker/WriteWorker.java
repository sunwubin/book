package bimoku.search.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bimoku.search.util.database.DBPool2;
import bimoku.search.util.database.Db;
import bimoku.search.util.log.LogOutPut;


/**
 * 执行写入总库的操作
 * 步骤 ：
 * 
 *  1.从队列中得到生产者提供的产品
 *  2.判断产品是否是否为标准的产品
 *  3.对产品进行操作
 *  4.维护产品队列中的产品
 *  
 * @author sun
 *
 */
public class WriteWorker implements Runnable  {
	StringBuilder stringBuilder=new StringBuilder();
	List<Integer> ids=new ArrayList<Integer>();
	//StringBuilder sql=new StringBuilder("insert into t_book(ID,ISBN,UUID,BOOKNAME,AUTHOR,TRANSLATOR,PRESS,VERSION,OUTLINE,WANTREAD,READING,HASREAD,DIRECTORY,CATELOG,AUTHORINTRO,PRICE,COVER_PIC,CLASSFY,RELATIONSHIP,t_bookcol) values ");
	StringBuilder sql=new StringBuilder("insert into t_test (BOOKNAME,TYPE) values ");
	@Override
	public void run() {
		while(true){
			if(Products.size()==0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					LogOutPut.outError(e.getMessage());
					e.printStackTrace();
				}
				continue;
			}
			for(int i=0;i<20;i++){
		    //  System.out.println(Products.size());
		      HashMap<String,Object> list=(HashMap<String, Object>) Products.get_last();
		     // System.out.println("读取="+list.get("ID").toString());
		      //ids.add(i);
		      if(list!=null)
		       stringBuilder.append("('"+list.get("BOOKNAME").toString().replaceAll("\'", "\\\\'")+"','"+list.get("TYPE").toString().replaceAll("\'", "\\\\'")+"'),");
			}
			if(stringBuilder.length()>0){
				 stringBuilder = new StringBuilder(stringBuilder.substring(0,stringBuilder.length()-1));
				 System.out.println(stringBuilder);
				 System.out.println("产品队列中数据1_"+Products.size());
		         working();
		         System.out.println("产品队列中数据2_"+Products.size());
			}
			
		}
	}
	
	/**
	 * 必须加同步锁执行
	 * 1.添加到总库中
	 * 2.删除生产队列中的产品
	 * 
	 */
	public synchronized void working(){
		try{
		  Db.ExecuteNonQuery(DBPool2.getInstance().getConnection(), sql+(stringBuilder).toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 stringBuilder=new StringBuilder("");
//		      for(int i=0;i<10;i++){
//		    	 // Products.remove(ids.get(i));
//		    	  Products.remove_last();
//		      }
		      System.out.println("产品队列中===="+Products.size());
		}
		 
	      
	}

}
