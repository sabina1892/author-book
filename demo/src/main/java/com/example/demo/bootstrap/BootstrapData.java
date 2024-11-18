package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        Book ericBook = new Book();
        ericBook.setTitle("Book design");
        ericBook.setIsbn("123");

        Author saveEric = authorRepository.save(eric);
        Book saveEricBook = bookRepository.save(ericBook);
        saveEric.getBooks().add(saveEricBook);
        saveEricBook.getAuthors().add(saveEric);



        Author john = new Author();
        john.setFirstName("John");
        john.setLastName("Smith");

        Book johnBook = new Book();
        johnBook.setTitle("Lacking guys");
        johnBook.setIsbn("111");

        Author saveJohn = authorRepository.save(john);
        Book saveJohnBook = bookRepository.save(johnBook);
        saveJohn.getBooks().add(saveJohnBook);
        saveJohnBook.getAuthors().add(saveJohn);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Arial");
        publisher.setAddress("Baku");
        Publisher publishSaved = publisherRepository.save(publisher);
        saveEricBook.setPublisher(publishSaved);
        saveJohnBook.setPublisher(publishSaved);


        authorRepository.save(saveJohn);
        authorRepository.save(saveEric);
        bookRepository.save(saveEricBook);
        bookRepository.save(saveJohnBook);
        System.out.println(authorRepository.count());
        System.out.println(bookRepository.count());

        //System.out.println(authorRepository.findAll());
    }
}
