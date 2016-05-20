package br.com.devmedia.revjpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

final class GenericDAO<T extends Serializable> {
    private Class<T> clazz;
    private EntityManager manager;
    
    GenericDAO(Class<T> clazz, EntityManager manager) {
        this.clazz = clazz;
        this.manager = manager;
    }
    
    T findById(Long id) {
        return this.manager.find(clazz, id);
    }
    
    List<T> findAll() {
        return this.manager.createQuery("select t from " + this.clazz.getSimpleName() + " t", this.clazz).getResultList();
    }
    
    List<T> find(String jpql, Object... params) {
        return this.createQueryWithParams(jpql, params).getResultList();
    }
    
    T findOne(String jpql, Object... params) {
        return this.createQueryWithParams(jpql, params).getSingleResult();
    }
    
    long count() {
        return (long) this.manager.createQuery("select count(t) from " + this.clazz + " t", this.clazz).getSingleResult();
    }
    
    void save(T entity) {
        this.manager.getTransaction().begin();
        this.manager.persist(entity);
        this.manager.getTransaction().commit();
    }
    
    void update(T entity) {
        this.manager.getTransaction().begin();
        this.manager.merge(entity);
        this.manager.getTransaction().commit();
    }
    
    void delete(Long id) {
        T obj = this.manager.getReference(this.clazz, id);
        
        this.manager.getTransaction().begin();
        this.manager.remove(obj);
        this.manager.getTransaction().commit();
    }
    
    private TypedQuery<T> createQueryWithParams(String jpql, Object... params) {
        TypedQuery<T> query = this.manager.createQuery(jpql, this.clazz);
        for(int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query;
    }
}