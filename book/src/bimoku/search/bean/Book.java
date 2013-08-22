package bimoku.search.bean;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import bimoku.search.worker.ProductEnum;

@XmlRootElement(name = "Book")
public class Book implements Serializable {
	
	private int id;
	private String isbn;
	private String uuid;
	private String bookName;
	private String author;
	private String translator;
	private String press;
	private String version;
	private String outline;
	private int wantread;
	private int reading;
	private int hasread;
	private String directory;
	private String catelog;
	private String authorintro;
	private double price;
	private String cover_pric;
	private String classfy;
	private String relationship;
	private String t_bookcol;
	
	private ProductEnum productenum;
	
	

}
