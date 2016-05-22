package br.com.devmedia.revjpa.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.devmedia.revjpa.entity.Document;

public class DocumentDAOTest {
    private Document documentOne;
    private Document documentTwo;
    private Document documentThree;
    private DocumentDAO documentDAO;

    @Before
    public void setUp() {
        this.documentOne = new Document("987.654.321-85", "58.963.741-58");
        this.documentTwo = new Document("321.654.987-69", "36.547.852-98");
        this.documentThree = new Document("741.852.963.25", "54.962.871-25");
        
        this.documentDAO = new DocumentDAO();
        this.documentDAO.save(this.documentOne);
        this.documentDAO.save(this.documentTwo);
        this.documentDAO.save(this.documentThree);
    }
    
    @After
    public void tearDown() {
        this.documentDAO.delete(this.documentOne.getId());
        this.documentDAO.delete(this.documentTwo.getId());
        this.documentDAO.delete(this.documentThree.getId());
    }

    @Test
    public void shouldFindDocumentByItsId() {
        Document recoveredDocument = this.documentDAO.findById(this.documentOne.getId());
        Assert.assertNotNull(recoveredDocument);
    }
    
    @Test
    public void shouldFindAllDocuments() {
        List<Document> allDocuments = this.documentDAO.findAll();
        Assert.assertFalse(allDocuments.isEmpty());
        Assert.assertEquals(3, allDocuments.size());
    }
    
    @Test
    public void shouldCountDocumentsOnDatabase() {
        Assert.assertEquals(new Long(3L), this.documentDAO.count());
    }
    
    @Test
    public void shouldUpdateDocumentOnDatabase() {
        this.documentOne.setRg("25.753.951.96");
        this.documentDAO.update(this.documentOne);
        Assert.assertEquals("25.753.951.96", this.documentDAO.findById(this.documentOne.getId()).getRg());
    }
}