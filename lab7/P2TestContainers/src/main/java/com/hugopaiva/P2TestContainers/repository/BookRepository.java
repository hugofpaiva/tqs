package com.hugopaiva.P2TestContainers.repository;

import com.hugopaiva.P2TestContainers.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
