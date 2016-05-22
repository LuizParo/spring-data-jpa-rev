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

    public List<Person> findByLastName(String lastName) {
        String jpql = "select p from Person p where p.lastName = ?";
        return this.dao.find(jpql, lastName);
    }
    
    public List<Person> findAgeIsBetween(int min, int max) {
        String jpql = "select p from Person p where p.age between ? and ?";
        return this.dao.find(jpql, min, max);
    }
    
    public Person findByFullName(String firstName, String lastName) {
        String jpql = "select p from Person p where p.firstName = ? and p.lastName = ?";
        return this.dao.findOne(jpql, firstName, lastName);
    }
    
    public Person findByCpf(String cpf) {
        String jpql = "select p from Person p where p.document.cpf = ?";
        return this.dao.findOne(jpql, cpf);
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