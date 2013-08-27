package bimoku.search.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import bimoku.search.bean.Book;
import bimoku.search.dao.BookDao;
import bimoku.search.dao.impl.BookDaoImpl;
import bimoku.search.service.BookService;


@Path(value="/book")
public class BookServiceImpl implements BookService {
	
	public BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	@GET
	@Produces("text/plain")
	public String doGet() {
		return null;
	}

	@Override
	@GET
	@Produces("text/plain")
	@Path("/request/{param}")
	public String doRequest(String param, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		return null;
	}

	@Override
	@GET
	@Path("/query/{id}")
	@Produces({ "application/xml", "application/json" })
	public String getBean(@PathParam("id") Integer id) {
		System.out.println(id);
		return bookDao.query(id);
	}

	@Override
	@POST
	@Path("/add")
	public Book postData(Book book) {
		return null;
	}

	@Override
	@DELETE
	@Path("/delete/{id}")
	public void deleteData(@PathParam("id") Integer id) {
		
	}

	@Override
	@GET
	@Path("/query/all")
	@Produces("text/plain")
	public String getBeans() {
		return null;
	}

	
	/**
	 * 搜索相关图书
	 * 1.按照图书名称搜索
	 * 2.按照作者搜索
	 * 
	 */
	@Override
	@GET
	@Path("/query/list/{startRow}/{rowSize}")
	@Produces("application/json")
	public String getBeans
	(@PathParam("startRow") Integer id,
			@PathParam("rowSize") Integer size,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse) {
		String author=null;
		String bookname=null;
		author=servletRequest.getParameter("author");
		bookname=servletRequest.getParameter("bookname");
		
		return bookDao.query("", 1, 2);
	}
	
	public static void main(String[] args){
		
		System.out.println(new BookDaoImpl().query(2));
	}
	
	
	

}
