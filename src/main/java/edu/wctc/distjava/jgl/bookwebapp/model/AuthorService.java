/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roshan
 */
@Stateless
public class AuthorService implements Serializable {

    private static final long serialVersionUID = 1L;

    //private final String AUTHOR_TBL = "author";
    //private final String AUTHOR_PK = "author_id";

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorService() {

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public final int removeAuthorById(String id) throws Exception {
        String jpql = "DELETE FROM Author a where a.authorId = :id";
        
        //"DELETE FROM Employee e WHERE e.department IS NULL"
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", Integer.parseInt(id));
        return q.executeUpdate();
    }

    public List<Author> getAuthorList() throws Exception {

        //List<Author> authorList = new ArrayList<>();
        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500); //optional
        //authorList = q.getResultList();

        return q.getResultList();
    }

    public final List<Author> findById(String id) throws Exception {
        String jpql = "select a from Author a where a.authorId = :id";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        return q.getResultList();
    }
    
    public int updateAuthorById(String id){
        String jpql = "UPDATE Author a SET a.authorName = (a.authorName) where a.id = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", new Integer(id));
        return q.executeUpdate();    
        
    }

}
