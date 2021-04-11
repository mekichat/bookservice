package com.example.bookservice;

import org.springframework.data.repository.CrudRepository;

public interface BookServiceRepository extends CrudRepository<Book, Integer> {
    
}
