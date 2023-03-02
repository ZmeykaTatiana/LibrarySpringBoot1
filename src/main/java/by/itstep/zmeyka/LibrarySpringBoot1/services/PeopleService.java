package by.itstep.zmeyka.LibrarySpringBoot1.services;

import by.itstep.zmeyka.LibrarySpringBoot1.Model.Book;
import by.itstep.zmeyka.LibrarySpringBoot1.Model.Person;
import by.itstep.zmeyka.LibrarySpringBoot1.repositories.PeopleRepository;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


//TODO with Security



@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        Optional<Person>person=peopleRepository.findById(id);
        return  person.orElse(null);

    }
    @Transactional
    public void save(Person person){
          peopleRepository.save(person);

    }
    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
@Transactional
    public void delete(int id){
        peopleRepository.delete(id);

    }

    public Optional<Person> getPersonByEmail(String email){
        return  peopleRepository.findBySurName(email);

    }


    //TODO
    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());

                if (diffInMillies > 864000000)
                    book.setExpired(true);
            });

            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
