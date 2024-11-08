package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class BootstrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("David");

        Book bbb = new Book();
        bbb.setTitle("Book design");
        bbb.setIsbn("123");

        Author saveEric = authorRepository.save(eric);
        Book saveBbb = bookRepository.save(bbb);
        saveEric.getBooks().add(saveBbb);

        Author john = new Author();
        john.setFirstName("John");
        john.setLastName("Smith");

        Book ccc = new Book();
        ccc.setTitle("Lacking guys");
        ccc.setIsbn("111");

        Author saveJohn = authorRepository.save(john);
        Book saveCcc = bookRepository.save(ccc);
        saveJohn.getBooks().add(saveCcc);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Arial");
        publisher.setAddress("Baku");
        Publisher publishSaved = publisherRepository.save(publisher);
        saveBbb.setPublisher(publishSaved);
        saveCcc.setPublisher(publishSaved);


        authorRepository.save(saveJohn);
        authorRepository.save(saveEric);
        bookRepository.save(saveBbb);
        bookRepository.save(saveCcc);
        System.out.println(authorRepository.count());
        System.out.println(bookRepository.count());

        //System.out.println(authorRepository.findAll());
    }
}
