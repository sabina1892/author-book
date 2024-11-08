package com.example.demo.services;

import com.example.demo.domain.Author;

import java.util.Iterator;

public interface AuthorService {
    Iterable<Author> findAll();
}
