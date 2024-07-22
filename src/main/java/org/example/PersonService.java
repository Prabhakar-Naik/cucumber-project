package org.example;

import org.example.model.Person;

import java.util.List;

/**
 * @author prabhakar, @Date 18-07-2024
 */
public interface PersonService {

    List<Person> init();

    Person save(Person person);

    List<Person> findAll();

    Person update(Person person);

    void delete(Long id);
}
