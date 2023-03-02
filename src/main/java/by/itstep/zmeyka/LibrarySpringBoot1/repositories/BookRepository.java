package by.itstep.zmeyka.LibrarySpringBoot1.repositories;

import by.itstep.zmeyka.LibrarySpringBoot1.Model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByTitleStartingWith(String title);
}
