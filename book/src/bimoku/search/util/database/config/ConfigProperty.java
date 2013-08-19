package bimoku.search.util.database.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import bimoku.search.util.log.LogOutPut;

public class ConfigProperty {

	public String getPropertyValue(String propertyName){
		String re=null;
		 InputStream in=getClass().getResourceAsStream("db.config.properties");
		 Properties props=new Properties();
		 try {
			props.load(in);
			re=props.getProperty(propertyName);
		} catch (IOException e) {
			LogOutPut.outError(e.getMessage());
			e.printStackTrace();
		}finally{
			
		}
		 return re;
		 
		 
	}
	public static void main(String[] args) throws Exception{
	
		System.out.println(new ConfigProperty().getPropertyValue("bookdb.username"));
	}
}
