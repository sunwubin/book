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
	public Book getBean(@PathParam("id") Integer id) {
//		Book book=new Book();
//		book.setId(id);
//		BookDao bookDao=new BookDaoImpl();
//		try {
//			book=(Book)bookDao.query(book);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return book;
		return null;
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
	@Path("/query/list")
	@Produces("text/plain")
	public String getBeans() {
		return null;
	}

	@Override
	@GET
	@Path("/query/list/{id}/{size}")
	@Produces("application/json")
	public String getBeans
	(@PathParam("id") Integer id,
			@PathParam("size") Integer size,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse) {
		return null;
	}

}
