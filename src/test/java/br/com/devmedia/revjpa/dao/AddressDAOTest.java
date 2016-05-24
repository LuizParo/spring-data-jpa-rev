package br.com.devmedia.revjpa.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.devmedia.revjpa.entity.Address;
import br.com.devmedia.revjpa.entity.Address.TypeAddress;

public class AddressDAOTest {
    private Address addressOne;
    private Address addressTwo;
    private Address addressThree;
    private AddressDAO addressDAO;

    @Before
    public void setUp() {
        this.addressOne = new Address("São Paulo", "Test Street", TypeAddress.RESIDENTIAL);
        this.addressTwo = new Address("Salvador", "Test Street", TypeAddress.COMMERCIAL);
        this.addressThree = new Address("Rio de Janeiro", "Test Street", TypeAddress.RESIDENTIAL);
        
        this.addressDAO = new AddressDAO();
        this.addressDAO.save(this.addressOne);
        this.addressDAO.save(this.addressTwo);
        this.addressDAO.save(this.addressThree);
    }
    
    @After
    public void tearDown() {
        this.addressDAO.delete(this.addressOne.getId());
        this.addressDAO.delete(this.addressTwo.getId());
        this.addressDAO.delete(this.addressThree.getId());
    }

    @Test
    public void shouldFindAddressByItsId() {
        Address recoveredAddress = this.addressDAO.findById(this.addressOne.getId());
        Assert.assertNotNull(recoveredAddress);
    }
    
    @Test
    public void shouldFindAddressByItsCity() {
        List<Address> addresses = this.addressDAO.findByCity("São Paulo");
        Assert.assertEquals(1, addresses.size());
        Assert.assertEquals("São Paulo", addresses.get(0).getCity());
    }
    
    @Test
    public void shouldFindAllAddresses() {
        List<Address> allAddresses = this.addressDAO.findAll();
        Assert.assertFalse(allAddresses.isEmpty());
        Assert.assertEquals(3, allAddresses.size());
    }
    
    @Test
    public void shouldCountAddressesOnDatabase() {
        Assert.assertEquals(new Long(3L), this.addressDAO.count());
    }
    
    @Test
    public void shouldUpdateAddressOnDatabase() {
        this.addressOne.setStreet("Street Test Two");
        this.addressDAO.update(this.addressOne);
        Assert.assertEquals("Street Test Two", this.addressDAO.findById(this.addressOne.getId()).getStreet());
    }
}