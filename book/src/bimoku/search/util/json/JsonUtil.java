package bimoku.search.util.json;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtil {
	
	private static JsonUtil instance;
	private static JSONArray jsonArray;
	
	
	private JsonUtil(){}
	public static synchronized JsonUtil getInstance(){
		if(instance==null){
			instance=new JsonUtil();
		}
		return instance;
	}
	
	
	public   static synchronized  String list_hashmap_json(List<HashMap<String,Object>> list){
		if(list==null)
			return null;
	
		jsonArray=new JSONArray();
		jsonArray.put(list);
		try {
			return jsonArray.get(0).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "[]";
	}
	
	
	
	

}
