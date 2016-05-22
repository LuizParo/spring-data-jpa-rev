package br.com.devmedia.revjpa.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.devmedia.revjpa.entity.Document;
import br.com.devmedia.revjpa.entity.Person;

public class PersonDAOTest {
    private PersonDAO dao;
    private Person personOne;
    private Person personTwo;
    private Person personThree;

    @Before
    public void setUp() {
        this.dao = new PersonDAO();
        this.personOne = new Person();
        this.personOne.setFirstName("Luiz");
        this.personOne.setLastName("Paro");
        this.personOne.setAge(24);
        
        this.personTwo = new Person();
        this.personTwo.setFirstName("João");
        this.personTwo.setLastName("Silva");
        this.personTwo.setAge(30);
        
        this.personThree = new Person();
        this.personThree.setFirstName("Carlos");
        this.personThree.setLastName("Paro");
        this.personThree.setAge(22);
        
        this.dao.save(this.personOne);
        this.dao.save(this.personTwo);
        this.dao.save(this.personThree);
    }
    
    @After
    public void tearDown() {
        this.dao.delete(this.personOne.getId());
        this.dao.delete(this.personTwo.getId());
        this.dao.delete(this.personThree.getId());
    }
    
    @Test
    public void shouldFindPersonByItsId() {
        Person recoveredPerson = this.dao.findById(this.personOne.getId());
        Assert.assertNotNull(recoveredPerson);
    }
    
    @Test
    public void shouldFindAllPeople() {
        List<Person> allPeople = this.dao.findAll();
        Assert.assertFalse(allPeople.isEmpty());
        Assert.assertEquals(3, allPeople.size());
    }
    
    @Test
    public void shouldCountPeopleOnDatabase() {
        Assert.assertEquals(new Long(3L), this.dao.count());
    }
    
    @Test
    public void shouldRecoverPeopleByLastName() {
        List<Person> peopleWithSameLastName = this.dao.findByLastName("Paro");
        Assert.assertFalse(peopleWithSameLastName.isEmpty());
        Assert.assertEquals(2, peopleWithSameLastName.size());
    }
    
    @Test
    public void shouldRecoverPeopleBetweenAge() {
        List<Person> peopleWithAgeRange = this.dao.findAgeIsBetween(24, 30);
        Assert.assertFalse(peopleWithAgeRange.isEmpty());
        Assert.assertEquals(2, peopleWithAgeRange.size());
    }
    
    @Test
    public void shouldRecoverPersonByFullName() {
        Assert.assertEquals(this.personOne, this.dao.findByFullName("Luiz", "Paro"));
    }
    
    @Test
    public void shouldUpdatePersonOnDatabase() {
        this.personOne.setFirstName("Mario");
        this.dao.update(this.personOne);
        Assert.assertEquals("Mario", this.dao.findById(this.personOne.getId()).getFirstName());
    }
    
    @Test
    public void shouldPersistPersonWithDocument() {
        this.personOne.setDocument(new Document("852.987.456-21", "54.962.951-41"));
        this.dao.update(this.personOne);
        
        Assert.assertNotNull(this.personOne.getDocument().getId());
    }
    
    @Test
    public void shouldRecoverPersonByItsCpf() {
        this.personOne.setDocument(new Document("852.987.456-21", "54.962.951-41"));
        this.dao.update(this.personOne);
        
        Assert.assertEquals(this.personOne, this.dao.findByCpf("852.987.456-21"));
    }
}