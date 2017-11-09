/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public int removeAuthorById(String id) throws Exception {
        String jpql = "DELETE FROM Author a where a.authorId = :id";
        
        //"DELETE FROM Employee e WHERE e.department IS NULL"
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", Integer.parseInt(id));
        return q.executeUpdate();
    }
    
//    public void removeAuthor(Author author){
//        getEm().remove(getEm().merge(author));
//    }
    
    public void addAuthor(List<Object> colValues) throws Exception {
        Author author = new Author();
        author.setAuthorName(colValues.get(0).toString());
        //author.setDateAdded((Date)colValues.get(1));
        author.setDateAdded(new Date());
        getEm().persist(author);
    }

    public List<Author> getAuthorList() throws Exception {

        //List<Author> authorList = new ArrayList<>();
        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500); //optional
        //authorList = q.getResultList();

        return q.getResultList();
    }

    public  Author findAuthor(String id) throws Exception {
//        String jpql = "select a from Author a where a.authorId = :id";
//        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
//        q.setParameter("id", new Integer(id));
////        return q.getResultList().get(0);
//        return q.getSingleResult();
          int authorId = Integer.parseInt(id);
          return getEm().find(Author.class, authorId);
    }
    
    public void updateAuthorById(List<Object> colValues, String id) throws ParseException{
        int authorId = Integer.parseInt(id);
        Author author = getEm().find(Author.class, authorId);
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded(getDate(colValues.get(1).toString()));
        
        getEm().merge(author);    
    }
    
    public final Date getDate(String sDate) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        return date;
    }
    
    public final String currentDate(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }
}
