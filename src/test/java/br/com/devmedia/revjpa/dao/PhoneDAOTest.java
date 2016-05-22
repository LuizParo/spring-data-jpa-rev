package br.com.devmedia.revjpa.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.devmedia.revjpa.entity.Person;
import br.com.devmedia.revjpa.entity.Phone;
import br.com.devmedia.revjpa.entity.Phone.TypePhone;

public class PhoneDAOTest {
    private Phone phoneOne;
    private Phone phoneTwo;
    private Phone phoneThree;
    private PhoneDAO phoneDAO;

    @Before
    public void setUp() {
        this.phoneOne = new Phone(TypePhone.RESIDENTIAL, "58 3632-8574", new Person("Luiz", "Paro", 24));
        this.phoneTwo = new Phone(TypePhone.COMMERCIAL, "58 4832-8574", new Person("João", "Silva", 30));
        this.phoneThree = new Phone(TypePhone.CELLPHONE, "58 99632-8574", new Person("Carlos", "Paro", 22));
        
        this.phoneDAO = new PhoneDAO();
        this.phoneDAO.save(this.phoneOne);
        this.phoneDAO.save(this.phoneTwo);
        this.phoneDAO.save(this.phoneThree);
    }
    
    @After
    public void tearDown() {
        this.phoneDAO.delete(this.phoneOne.getId());
        this.phoneDAO.delete(this.phoneTwo.getId());
        this.phoneDAO.delete(this.phoneThree.getId());
    }
    
    @Test
    public void shouldFindDocumentByItsId() {
        Phone recoveredPhone = this.phoneDAO.findById(this.phoneOne.getId());
        Assert.assertNotNull(recoveredPhone);
    }
    
    @Test
    public void shouldFindAllDocuments() {
        List<Phone> allPhones = this.phoneDAO.findAll();
        Assert.assertFalse(allPhones.isEmpty());
        Assert.assertEquals(3, allPhones.size());
    }
    
    @Test
    public void shouldCountPhonesOnDatabase() {
        Assert.assertEquals(new Long(3L), this.phoneDAO.count());
    }
    
    @Test
    public void shouldUpdateDocumentOnDatabase() {
        this.phoneOne.setNumber("19 3578-9874");
        this.phoneDAO.update(this.phoneOne);
        Assert.assertEquals("19 3578-9874", this.phoneDAO.findById(this.phoneOne.getId()).getNumber());
    }
}