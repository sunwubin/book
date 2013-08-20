package bimoku.search.util.log;

import org.apache.log4j.Logger;

public class LogOutPut {
	
	public static synchronized void outError(String str){
		Logger log = Logger.getLogger("myTest1");   
        for(int i=0;i<10;i++){  
            log.error(str);   
        }  
	}
	
	public static void main(String[] args){
		 Logger log = Logger.getLogger("myTest1");   
	        for(int i=0;i<10;i++){   
	            //log.debug("DDDDDDDDDDDDD");   
	            //log.info("IIIIIIIIIIIIIIIIII");   
	            //log.warn("WWWWWWWWWWWWWWWWWWWWWW");   
	            log.error("cuowu\n");   
	        }  
	}

}
