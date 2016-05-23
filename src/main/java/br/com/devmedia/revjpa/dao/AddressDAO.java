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
        return dao.findById(id);
    }

    public List<Address> findAll() {
        return dao.findAll();
    }

    public Long count() {
        return dao.count();
    }

    public void save(Address entity) {
        dao.save(entity);
    }

    public void update(Address entity) {
        dao.update(entity);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public void delete(Address entity) {
        dao.delete(entity);
    }
}