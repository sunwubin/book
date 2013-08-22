package bimoku.search.util.cache;

public class CacheOper {
	
	
	/**
	 * 检查虚拟机内存小于10M则清空缓存
	 * @param key
	 * @param data
	 */
	private void addCacheData(String key,Object data){
		if(Runtime.getRuntime().freeMemory()<5L*1024L*1024L){
		 //removeAllCacheData();
		 //System.gc();
		 return ;
		}
	}

}
