package br.com.devmedia.revjpa.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.devmedia.revjpa.entity.Person;

public class PersonDAOTest {
    private PersonDAO dao;
    private Person person;

    @Before
    public void setUp() {
        this.dao = new PersonDAO();
        this.person = new Person();
        this.person.setFirstName("Luiz");
        this.person.setLastName("Paro");
        this.person.setAge(24);
    }
    
    @After
    public void tearDown() {
        this.dao.delete(this.person.getId());
    }

    @Test
    public void shouldPersistPersonOnDatabase() {
        this.dao.save(this.person);
        Assert.assertNotNull(this.person.getId());
    }
    
    @Test
    public void shouldFindPersonByItsId() {
        this.dao.save(this.person);
        Person recoveredPerson = this.dao.findById(this.person.getId());
        
        Assert.assertNotNull(recoveredPerson);
    }
    
    @Test
    public void shouldFindAllPeople() {
        this.dao.save(this.person);
        List<Person> allPerson = this.dao.findAll();
        Assert.assertFalse(allPerson.isEmpty());
        Assert.assertEquals(1, allPerson.size());
    }
    
    @Test
    public void shouldCountPeopleOnDatabase() {
        this.dao.save(this.person);
        Assert.assertEquals(new Long(1L), this.dao.count());
    }
    
    @Test
    public void shouldUpdatePersonOnDatabase() {
        this.dao.save(this.person);
        this.person.setFirstName("Carlos");
        this.dao.update(this.person);
        Assert.assertEquals("Carlos", this.dao.findById(this.person.getId()).getFirstName());
    }
}