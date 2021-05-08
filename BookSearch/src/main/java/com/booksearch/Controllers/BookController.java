package com.booksearch.Controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.booksearch.Services.BookService;
import com.booksearch.Model.*;

@EnableSwagger2
@RestController
public class BookController {

	@Autowired	
	private BookService bookService;
	
	
	/*Creates a new book*/
	@PostMapping(path = "/newbook",
	consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void addBook(@RequestBody Book bookView) {
		bookService.addBook(bookView);
	}
	
	/*Returns an existing book*/
	@GetMapping(path = "/{bookId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Book readBook(@PathVariable("bookId") Long bookId) {
		return bookService.readBook(bookId);
	}
	
	/*Updates a book*/
	@PutMapping(path = "/{bookId}/update")
	public void updateBook(@PathVariable("bookId") Long bookId,
			@RequestBody Book bookView) {		
		bookService.updateBook(bookId, bookView);
	}
	
	/*Deletes a book*/
	@DeleteMapping(path = "/{bookId}")
	public void deleteBook(@PathVariable("bookId") Long bookId) {
		bookService.deleteBook(bookId);
	}
	
	/*Retrieves a list with the books (All the books or filtered by price and/or author)*/
	@GetMapping(path = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Set<Book> getAllBooks(
			@RequestParam(required = false) Integer minprice, @RequestParam(required = false) Integer maxprice){
		if(minprice == null &&maxprice == null) {
		return bookService.getAllBooks();
		}
		else return bookService.getBooksBetweenPrices(minprice, maxprice);
	}
	
	@GetMapping(path = "/books/authors/{author}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Set<Book> getBooksByAuthor(@PathVariable("author") String author){
		return bookService.getBooksByAuthor(author);
	}
}

