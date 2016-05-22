package br.com.devmedia.revjpa.dao;

import java.util.List;

import br.com.devmedia.revjpa.entity.Phone;
import br.com.devmedia.revjpa.util.JPAUtil;

public class PhoneDAO {
    private GenericDAO<Phone> dao;
    
    public PhoneDAO() {
        this.dao = new GenericDAO<>(Phone.class, JPAUtil.getInstance().getEntityManager());
    }

    public Phone findById(Long id) {
        return this.dao.findById(id);
    }

    public List<Phone> findAll() {
        return this.dao.findAll();
    }

    public Long count() {
        return this.dao.count();
    }

    public void save(Phone entity) {
        this.dao.save(entity);
    }

    public void update(Phone entity) {
        this.dao.update(entity);
    }

    public void delete(Long id) {
        this.dao.delete(id);
    }
}