package com.jpa.restapi.jparest.controllers;

import java.util.List;

import com.jpa.restapi.jparest.entities.Book;
import com.jpa.restapi.jparest.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/books") // Get Request Method mapping no need of ResquestMapping annotation
  public ResponseEntity<List<Book>> getBooks() {

    List<Book> list = this.bookService.getAllBooks(); // Spring Boot Convert book object to json automatically with the
    // help of jackson dependencies which is auto config when RestAPI project is
    // created

    if (list.size() <= 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
    // return ResponseEntity.status(HttpStatus.OK).body(list);
    return ResponseEntity.status(HttpStatus.OK).body(list);
  }

  // Getting Single Book from book id
  @GetMapping("/books/{id}")
  public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
    Book book = this.bookService.getBookById(id);

    if (book == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(book);

  }

  // Adding Book
  @PostMapping("/books")
  public ResponseEntity<Book> addBook(@RequestBody Book book) {

    Book b = null;
    try {
      b = this.bookService.addBook(book);
      System.out.println(book);
      return ResponseEntity.status(HttpStatus.CREATED).body(b);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }

  // Deleting Single Book from book id
  @DeleteMapping("/books/{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
    try {
      this.bookService.deleteBook(bookId);
      return ResponseEntity.status(HttpStatus.OK).build();
      // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      // return ResponseEntity.ok().build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // Updating Single book info
  @PutMapping("/books/{bookId}")
  public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
    try {
      this.bookService.updateBook(book, bookId);
      return ResponseEntity.ok().body(book);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
