package bimoku.search.dao.impl;


import java.util.HashMap;
import java.util.List;

import bimoku.search.dao.BookDao;
import bimoku.search.util.database.DBPool2;
import bimoku.search.util.database.Db;
import bimoku.search.util.json.JsonUtil;

public class BookDaoImpl implements BookDao {

	@Override
	public String query(String whereStr,int startRow,int sizeRow) {
		List<HashMap<String,Object>> list=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select * from t_bookdb limit 1,20 ");
		return JsonUtil.list_hashmap_json(list);
		 
	}

	@Override
	public String query(int id) {
		Object[] params=new Object[1];
		params[0]=id;
		List<HashMap<String,Object>> list=Db.ExecuteQuery(DBPool2.getInstance().getConnection(), "select * from t_bookdb where t_bookdb.id=? ",params);
		return JsonUtil.list_hashmap_json(list);
	}
}
