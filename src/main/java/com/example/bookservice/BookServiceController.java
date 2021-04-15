package com.example.bookservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookServiceController {

    @Autowired
    private BookService bookService;

    @GetMapping(path="/book")
    @CrossOrigin(origins ="*")
    List<Book> getAllBook(){        
        return bookService.getAll();
    }

    @GetMapping(path="/book/{id}")
    @CrossOrigin(origins ="*")
    Book getSingleBook(@PathVariable Integer id){        
        return bookService.get(id);
    }

    @PostMapping(path="/book", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Object> addBook(@RequestBody Book m){
        return bookService.add(m);
    }

    @PutMapping(path="/book/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook){
        return bookService.update(id, updatedBook);
    }

    @PatchMapping(path= "/book/{id}", consumes = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> partialBookUpdate(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookService.partialUpdate(id, updatedBook);
    }

    @DeleteMapping(path="/book/{id}")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> deleteBook(@PathVariable Integer id){
         return bookService.delete(id);
    }
    
}

