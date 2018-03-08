package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
	
	Book findByTitle(String title);
	
//	void delect(String title);

}
