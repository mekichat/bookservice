package com.example.bookservice;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BookTests {
    @Test
    void calculateBookPriceReturnsCorrectValue(){
        Integer numberOfBooks = 5;
        Book book = new Book();
        book.setTitle("Rich Dad Poor Dad");
        book.setType("Personal Finance");
        book.setWriter("Robert Kiyosaki");
        book.setLanguage("en");
        book.setPublished(2017);
        
        assertThat(book.calculateBookPrice(numberOfBooks)).isEqualTo(1500);
    
    }
    
}
