package com.booksearch.Services;

import java.util.Set;

import org.springframework.stereotype.Service;
import com.booksearch.Model.*;

@Service
public interface BookService {

	public void addBook(Book book);
	public Book readBook(Long bookId);
	public void updateBook(Long bookId, Book bookView);
	public void deleteBook(Long bookId);
	public Set<Book> getAllBooks();
	public Set<Book> getBooksBetweenPrices(Integer minprice, Integer maxprice);
	public Set <Book> getBooksByAuthor(String author);
}
