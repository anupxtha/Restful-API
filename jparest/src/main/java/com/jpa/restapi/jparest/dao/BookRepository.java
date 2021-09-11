package com.jpa.restapi.jparest.dao;

import com.jpa.restapi.jparest.entities.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

  public Book findById(int id);

}
