package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.example.model.Person;
import org.junit.Assert;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author prabhakar, @Date 18-07-2024
 */
@Configuration
@CucumberContextConfiguration
@ComponentScan(basePackages = "org.example")
public class PersonFeatureSteps {

    // these are the test scenarios in the features class

    private PersonService personService = new PersonServiceImpl();
    private List<Person> personList;
    private int personsInitSize = 0;
    private int personSizeAfterSave = 0;

    private Person savePerson;


    @Given("^I have static methods which initializing a list of persons$")
    public void given_i_have_static_methods_which_initializing_a_list_of_persons() {
        initPersonsList();
        Assert.assertEquals(personList.size(), 4);
    }

    @When("^I Connect I can see the size of the initialized list of person$")
    public void when_i_connect_i_can_see_the_size_of_the_initialized_list_of_person() {
        personsInitSize = personList.size();
    }

    @Then("^The list size equal to 4$")
    public void then_the_list_size_equal_to_4() {
        Assert.assertEquals(personsInitSize, 4);
    }


    //implementing Create new person scenario
    @Given("^The list of persons contain 4 persons already stored$")
    public void given_the_list_of_persons_contain_4_persons_already_stored() {
        initPersonsList();
        personsInitSize = personList.size();
    }

    @When("^I create a new person with random entry$")
    public void when_i_create_a_new_person_with_random_entry() {
        Person person = new Person();
        person.setId(5L);
        person.setFirstName("person1");
        person.setLastName("personLastName");
        person.setAge(45);
        personService.save(person);

        findAllPersons();
        this.personSizeAfterSave = this.personList.size();
    }

    @Then("^I get the ID of the new person and the list contain more than 4 persons$")
    public void then_i_get_the_ID_of_the_new_person_and_the_list_contain_more_than_4_persons() {
        Assert.assertEquals(personsInitSize, 5);
        Assert.assertNotNull(savePerson);
        Assert.assertNotNull(savePerson.getId());
    }


    // Bulk creation
    @When("^I created new person with (.*) and (.*) and (.*)$")
    public void when_i_created_new_person_with_given_date(String firstName, String lastName, int age) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        personService.save(person);
    }

    @Then("^I get the ID of the new person and the list contains more than 4 persons$")
    public void then_i_get_the_ID_of_the_new_person_and_the_list_contains_more_than_4_persons() {
        findAllPersons();
        Assert.assertTrue(personList.size() > 4);
    }

    //update person by id
    @When("^I update a person data with (.*) and (.*) and (.*) and (.*)$")
    public void when_i_update_a_person_data_by_id(Long id, String firstName, String lastName, int age) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        savePerson = personService.update(person);
    }

    @Then("^I get the person Updated$")
    public void then_i_get_the_person_updated() {
        Assert.assertNotNull(savePerson);
    }

    //Delete Given person id

    @When("^I delete a person with ID 1$")
    public void when_i_delete_a_person_with_ID_1L() {
        personService.delete(1L);
    }

    @Then("^The given person is deleted and the list size is equal to 4$")
    public void then_the_given_person_is_deleted_and_the_list_size_is_equal_to_4L() {
        findAllPersons();
        Assert.assertEquals(personList.size(),4);
    }

    private void initPersonsList() {
        personList = this.personService.init();
    }

    private void findAllPersons() {
        personList = this.personService.findAll();
    }
}
