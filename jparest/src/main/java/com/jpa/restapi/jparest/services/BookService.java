package com.jpa.restapi.jparest.services;

import java.util.List;

import com.jpa.restapi.jparest.dao.BookRepository;
import com.jpa.restapi.jparest.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  // Get all books
  public List<Book> getAllBooks() {
    List<Book> list = (List<Book>) this.bookRepository.findAll();
    return list;
  }

  // Get particular book by id
  public Book getBookById(int id) {
    Book book = null;

    try {
      book = this.bookRepository.findById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return book;
  }

  public Book addBook(Book b) {
    Book book = this.bookRepository.save(b);
    return book;
  }

  public void deleteBook(int bookId) {
    this.bookRepository.deleteById(bookId);
  }

  public void updateBook(Book book, int bookId) {
    book.setId(bookId);
    this.bookRepository.save(book);
  }
}
