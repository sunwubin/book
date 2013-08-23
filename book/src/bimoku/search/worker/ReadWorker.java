package bimoku.search.worker;

import java.util.HashMap;
import java.util.List;

import bimoku.search.util.database.DBPool2;
import bimoku.search.util.database.Db;
import bimoku.search.util.log.LogOutPut;

/**
 * 获得各大网站中的产品数据
 * 步骤：
 * 1.根据类型得到不同网站中的产品放入到产品队列中。
 * 2.这只是一个生产者。
 * 
 * @author sun
 *
 */
public class ReadWorker implements Runnable {
	
	private ProductEnum productEnum;

	private int ddi=0,jdi=0,tbi=0,dbi=0,ami=0,hdi=0;
	private int jdcount=0,ddcount=0,tbcount=0,dbcount=0,amcount=0,hdcount=0;
	private int size=15;
	
	public ReadWorker(){
		
	}
	public ReadWorker(ProductEnum productEnum){
		this.productEnum=productEnum;		
	}
	
	
	@Override
	public void run() {
		
		while(true){
			
			if(Products.size()>=3000){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					LogOutPut.outError(e.getMessage());
					e.printStackTrace();
				}
				continue;
			}
			
			System.out.println("需要取得链接");
			switch(this.productEnum){
			case DD:
				ddcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_bookdd ");
				if(ddcount>ddi){
					List<HashMap<String,Object>> ddlist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'DD' as TYPE from t_bookdd limit "+ddi+","+size);
					for(int i=0;i<ddlist.size();i++){
					    Products.add(ddlist.get(i));
					}
					//System.out.println("行数"+ddi+"总行数"+ddcount);
					ddi=ddi+size;
				}
				break;
			case JD:
				jdcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_bookjd ");
				if(jdcount>jdi){
					List<HashMap<String,Object>> jdlist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'TB' as TYPE from t_bookjd limit "+ddi+","+size);
					for(int i=0;i<jdlist.size();i++){
					    Products.add(jdlist.get(i));
					}
					jdi=jdi+size;
				}
				break;
			case TB:

				tbcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_booktb ");
				if(tbcount>tbi){
					List<HashMap<String,Object>> tblist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'TB' as TYPE from t_booktb limit "+ddi+","+size);
					for(int i=0;i<tblist.size();i++){
					    Products.add(tblist.get(i));
					}
					tbi=tbi+size;
				}
				break;
			case DB:
				dbcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_bookdb ");
				if(dbcount>dbi){
					List<HashMap<String,Object>> dblist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'DB' as TYPE from t_bookdb limit "+dbi+","+size);
					for(int i=0;i<dblist.size();i++){
					    Products.add(dblist.get(i));
					}
					dbi=dbi+size;
				}
				break;
			case AM:
				amcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_bookamazon ");
				if(amcount>ami){
					List<HashMap<String,Object>> amlist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'AM' as TYPE from t_bookamazon limit "+ami+","+size);
					for(int i=0;i<amlist.size();i++){
					    Products.add(amlist.get(i));
					}
					ami=ami+size;
				}
				break;
			case HD:
				hdcount=Db.ExecuteQueryNum(DBPool2.getInstance().getConnection(), "select count(*) from t_bookpub ");
				if(hdcount>ami){
					List<HashMap<String,Object>> hdlist=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select *,'AM' as TYPE from t_bookpub limit "+ami+","+size);
					for(int i=0;i<hdlist.size();i++){
					    Products.add(hdlist.get(i));
					}
					hdi=hdi+size;
				}
				break;
			default:
				break;
			}
			
			
		   
		}
		
	}

	
}
