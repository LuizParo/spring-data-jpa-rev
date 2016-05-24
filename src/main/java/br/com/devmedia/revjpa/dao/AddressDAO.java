package br.com.devmedia.revjpa.dao;

import java.util.List;

import br.com.devmedia.revjpa.entity.Address;
import br.com.devmedia.revjpa.util.JPAUtil;

public class AddressDAO {
    private GenericDAO<Address> dao;
    
    public AddressDAO() {
        this.dao = new GenericDAO<>(Address.class, JPAUtil.getInstance().getEntityManager());
    }

    public Address findById(Long id) {
        return this.dao.findById(id);
    }

    public List<Address> findAll() {
        return this.dao.findAll();
    }

    public Long count() {
        return this.dao.count();
    }

    public void save(Address entity) {
        this.dao.save(entity);
    }

    public void update(Address entity) {
        this.dao.update(entity);
    }

    public void delete(Long id) {
        this.dao.delete(id);
    }

    public void delete(Address entity) {
        this.dao.delete(entity);
    }
}