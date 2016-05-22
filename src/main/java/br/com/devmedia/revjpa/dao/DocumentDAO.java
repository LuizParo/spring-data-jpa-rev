package br.com.devmedia.revjpa.dao;

import java.util.List;

import br.com.devmedia.revjpa.entity.Document;
import br.com.devmedia.revjpa.util.JPAUtil;

public class DocumentDAO {
    private GenericDAO<Document> dao;
    
    public DocumentDAO() {
        this.dao = new GenericDAO<>(Document.class, JPAUtil.getInstance().getEntityManager());
    }

    public Document findById(Long id) {
        return this.dao.findById(id);
    }

    public List<Document> findAll() {
        return this.dao.findAll();
    }

    public Long count() {
        return this.dao.count();
    }

    public void save(Document entity) {
        this.dao.save(entity);
    }

    public void update(Document entity) {
        this.dao.update(entity);
    }

    public void delete(Long id) {
        this.dao.delete(id);
    }
}