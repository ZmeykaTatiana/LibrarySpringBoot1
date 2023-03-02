package by.itstep.zmeyka.LibrarySpringBoot1.repositories;

import by.itstep.zmeyka.LibrarySpringBoot1.Model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository {
    Optional<Person> findBySurName(String surname);
    Person findByEmail(String email);
}
