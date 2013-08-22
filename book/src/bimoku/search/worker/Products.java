package bimoku.search.worker;


import java.util.LinkedList;

public class Products {

	private static LinkedList<Object> products_list=null;
	
	static{
		products_list=new LinkedList<Object>();
	}
	
	public static boolean isNull(){	
		if(products_list!=null&&products_list.size()==0){
			return true;
		}else 
			return false;
	}
	public static synchronized void add(Object o){
		products_list.addFirst(o);
	}
	public static synchronized void remove(int  index){
		products_list.remove(index);
	}
	
	public static synchronized void remove_last(){
		if(products_list.size()>0)
		 products_list.removeLast();
	}
	public static int size(){
		if(products_list!=null)
		return products_list.size();
		else return -1;
	}
	
	/**
	 * 得到最后一个删除最后一个产品
	 * @return
	 */
	public static synchronized Object get_last(){
		if(products_list.size()>0){
			Object o= products_list.getLast();
			products_list.removeLast();
			return o;
		}else{
			return null;
		}
	}
	public static Object get(int index)
	{
		return products_list.get(index);
	}
	
	
	
	
	
	
	
}
