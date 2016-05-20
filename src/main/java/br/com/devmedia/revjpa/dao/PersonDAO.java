package br.com.devmedia.revjpa.dao;

import java.util.List;

import br.com.devmedia.revjpa.entity.Person;
import br.com.devmedia.revjpa.util.JPAUtil;

public class PersonDAO {
    private GenericDAO<Person> dao;
    
    public PersonDAO() {
        this.dao = new GenericDAO<>(Person.class, JPAUtil.getInstance().getEntityManager());
    }

    public Person findById(Long id) {
        return this.dao.findById(id);
    }

    public List<Person> findAll() {
        return this.dao.findAll();
    }

    public List<Person> find(String jpql, Object... params) {
        return this.dao.find(jpql, params);
    }

    public Person findOne(String jpql, Object... params) {
        return this.dao.findOne(jpql, params);
    }

    public Long count() {
        return this.dao.count();
    }

    public void save(Person entity) {
        this.dao.save(entity);
    }

    public void update(Person entity) {
        this.dao.update(entity);
    }

    public void delete(Long id) {
        this.dao.delete(id);
    }
}