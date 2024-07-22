package org.example;

import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prabhakar, @Date 18-07-2024
 */
public class PersonServiceImpl implements  PersonService{

    List<Person> personList = new ArrayList<Person>();

    @Override
    public List<Person> init() {
        personList.add(new Person(1L,"prabhakar","kimavath",25));
        personList.add(new Person(2L,"sudhakar","naik",29));
        personList.add(new Person(3L,"veerababu","panasa",27));
        personList.add(new Person(4L,"suresh","vajjapally",27));
        return personList;
    }

    @Override
    public Person save(Person person) {
        if (person == null){
            return person;
        }
        person.setId((long) (personList.size() + 1));
        personList.add(person);
        return person;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Person update(Person person) {
        if (person == null) {
            System.err.println("person is null");
            return null;
        }
        int personIndex = findPersonIndex(person.getId());
        if (person.getId() == -1){
            System.err.println("No person id found with "+person.getId());
            return null;
        }

        personList.set(personIndex, person);
        return person;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            System.err.println("person is null");
        }
        int personIndex = findPersonIndex(id);
        if (personIndex == -1){
            System.err.println("No person id found with "+personIndex);
        }

        personList.remove(personIndex);
    }

    private int findPersonIndex(Long id){
        for (int i =0;i<personList.size();i++){
            if (personList.get(i).getId() == id)
                return i;
        }
        return -1;
    }
}
