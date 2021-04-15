package com.example.bookservice;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.assertj.core.api.Assertions.assertThat; 

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BookServiceTests {
    BookServiceRepository bookServiceRepository;
    ArrayList<Book> testlist;
    Book testBook;
    @BeforeEach
    void init()
    {
        testlist = new ArrayList<Book>();
        testlist.add(new Book());
        testlist.add(new Book());
        testlist.add(new Book());
        testBook = new Book();      
        testBook.setId(5);
        testBook.setTitle("The Note Book");
        testBook.setPublished(2011);
        
        bookServiceRepository = Mockito.mock(BookServiceRepository.class); 
        when(bookServiceRepository.findAll()).thenReturn(testlist);
        when(bookServiceRepository.findById(testBook.getId())).thenReturn(Optional.of(testBook));
    }

    @Test
    void getAllShouldReturnAllRecords()
    {
        var allBooks = new BookService(bookServiceRepository);
        assertArrayEquals( testlist.toArray() ,allBooks.getAll().toArray());            
    }

    @Test
    void getShouldReturnASingleRecord()
    {
        var singleBook = new BookService(bookServiceRepository);        
        assertThat(testBook).usingRecursiveComparison().isEqualTo(singleBook.get(5)); 
                  
    }
    
}
