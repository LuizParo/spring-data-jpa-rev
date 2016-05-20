package br.com.devmedia.revjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {
    private static JPAUtil instance;
    private EntityManagerFactory emf;
    
    private JPAUtil() {
        this.emf = Persistence.createEntityManagerFactory("REVJPA");
    }
    
    public static synchronized JPAUtil getInstance() {
        if(instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}