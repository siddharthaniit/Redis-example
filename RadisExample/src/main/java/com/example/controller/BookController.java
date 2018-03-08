package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Book;
import com.example.repository.BookRepository;

@RestController
public class BookController  {
	
	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(method = RequestMethod.POST, value="book")
	@Cacheable(value = "book" , key = "#p0")
	public Book save(@RequestBody Book book)
	{
		System.out.println(book);
		return bookRepository.save(book);
		
	}
	@RequestMapping(method = RequestMethod.GET, value="/book/{id}")
	@Cacheable(value = "book" , key = "#p0")
	public Book findByBookTitle(@PathVariable String id)
	{
		Book findByTitle = bookRepository.findOne(Integer.valueOf(id));
		return findByTitle;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/book/{id}")
	@CachePut(value = "book" , key = "#p0")
	public Book updateBook(@RequestBody Book book , @PathVariable int id)
	{
		Book findByTitle = bookRepository.save(book);
		return findByTitle;
	}
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, value="/book/{id}")
	@CacheEvict(value="book", key = "#p0")
	public String  deleteBook(@PathVariable int id)
	{
		bookRepository.delete(id);
		return "Success";
	}
	
	@RequestMapping(value="hi")
	public String hi()
	{
		return "hii";
	}

}
