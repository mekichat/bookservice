package com.example.bookservice;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BookService {
    private final BookServiceRepository bookServiceRepository;

    BookService(BookServiceRepository bookServiceRepository) {
        super();
        this.bookServiceRepository = bookServiceRepository;
    }

    List<Book> getAll(){
        var l = new ArrayList<Book>();
        for(Book r : bookServiceRepository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    Book get(Integer id){
        return bookServiceRepository.findById(id).get();
    }

    ResponseEntity<Object> add(@RequestBody Book m){
        bookServiceRepository.save(m);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(m.getId())
        .toUri();

        return ResponseEntity.created(location).build();
    }

    Book update(@PathVariable Integer id, @RequestBody Book updatedBook){
        Book dbBook = bookServiceRepository.findById(id).get();
        dbBook.setTitle(updatedBook.getTitle());
        dbBook.setWriter(updatedBook.getWriter());
        dbBook.setType(updatedBook.getType());        
        dbBook.setPublished(updatedBook.getPublished());
        dbBook.setLanguage(updatedBook.getLanguage());

        bookServiceRepository.save(dbBook);
        return dbBook;
    }

    ResponseEntity<Void> partialUpdate(@PathVariable Integer id, @RequestBody Book updatedBook) {
        Book dbBook = bookServiceRepository.findById(id).get();

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
            if(updatedBook.getLanguage() != null){
                dbBook.setLanguage(updatedBook.getLanguage());    
            }             
            bookServiceRepository.save(dbBook);
            return ResponseEntity.ok().build(); 
            
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }      
    }

    ResponseEntity<Void> delete(@PathVariable Integer id){
        Book deleteBook = bookServiceRepository.findById(id).get();
        try {
            bookServiceRepository.delete(deleteBook);
            return ResponseEntity.ok().build(); 
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }
    }
}
