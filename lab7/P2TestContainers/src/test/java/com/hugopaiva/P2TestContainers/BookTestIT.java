package com.hugopaiva.P2TestContainers;

import com.hugopaiva.P2TestContainers.model.Book;
import com.hugopaiva.P2TestContainers.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookTestIT {

    @Autowired
    private BookRepository bookRepository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    public void testCreateBook() {
        Book book = new Book();
        book.setName("Testcontainers Hugo");
        bookRepository.saveAndFlush(book);
    }

    @Test
    @Order(2)
    public void testListBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1).extracting(Book::getName).contains("Testcontainers Hugo");
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        Book book = bookRepository.getOne(1L);
        book.setName("Updated Testcontainers Hugo");
        bookRepository.saveAndFlush(book);
    }

    @Test
    @Order(4)
    public void testListBooksAfterUpdate() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1).extracting(Book::getName).contains("Updated Testcontainers Hugo");
    }

    @Test
    @Order(5)
    public void testDeleteBook() {
        bookRepository.deleteAll();
        assertThat(bookRepository.count()).isEqualTo(1);
    }


}
