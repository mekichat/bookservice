package com.example.bookservice;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BookServiceController {

    @Autowired
    private BookServiceRepository bookRepository;

    @GetMapping(path="/book")
    @CrossOrigin(origins ="*")
    List<Book> getAllBook(){
        var book = new ArrayList<Book>();
        for(Book m1 : bookRepository.findAll()){
            book.add(m1);
        }
        return book;
    }


    @GetMapping(path="/book/{id}")
    @CrossOrigin(origins ="*")
    Book getSingleBook(@PathVariable Integer id){        
        return bookRepository.findById(id).get();
    }

    @PostMapping(path="/book", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Object> addBook(@RequestBody Book m){
        bookRepository.save(m);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(m.getId())
        .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(path="/book/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook){
        Book dbBook = bookRepository.findById(id).get();
        dbBook.setTitle(updatedBook.getTitle());
        dbBook.setWriter(updatedBook.getWriter());
        dbBook.setType(updatedBook.getType());        
        dbBook.setPublished(updatedBook.getPublished());
        //dbBook.setLanguage(updatedBook.getLanguage());

        bookRepository.save(dbBook);       

        return dbBook;

    }

    @PatchMapping(path= "/book/{id}", consumes = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> partialBookUpdate(@PathVariable Integer id, @RequestBody Book updatedBook) {

        Book dbBook = bookRepository.findById(id).get();

        try {
            if(updatedBook.getTitle() != null){
                dbBook.setTitle(updatedBook.getTitle());
    
            }
            if(updatedBook.getPublished()!= 0){ 
                dbBook.setPublished(updatedBook.getPublished());
    
            }
            if(updatedBook.getWriter()!= null){ 
                dbBook.setWriter(updatedBook.getWriter());
    
            }            
            if(updatedBook.getType() != null){
                dbBook.setType(updatedBook.getType());
    
            }
            /* if(updatedBook.getLanguage() != null){
                dbBook.setLanguage(updatedBook.getLanguage());
    
            }  */
            
            bookRepository.save(dbBook);
            return ResponseEntity.ok().build(); 
            
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }
      
    }

    @DeleteMapping(path="/book/{id}", consumes = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> deleteBook(@PathVariable Integer id){
        Book deleteBook = bookRepository.findById(id).get();
        try {
            bookRepository.delete(deleteBook);
            return ResponseEntity.ok().build(); 
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }        

    }
    
}

