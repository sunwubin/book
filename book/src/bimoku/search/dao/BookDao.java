package bimoku.search.dao;

public interface BookDao {
   public String query(String whereStr,int startRow,int sizeRow);
   public String query(int id);
   

}
