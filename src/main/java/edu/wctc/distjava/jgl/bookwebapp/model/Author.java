package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Size;

/**
 * This is the POJO used to hold the details of the Author object. Also contains
 * the getter and setter methods for the properties of the author
 * @author jlombardo
 */
@Entity
@Table(name = "author")
public class Author implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "author_id")
    private Integer authorId;
    
    /**
     * 
     */
    @Size(max = 80)
    @Column(name = "author_name")
    private String authorName;
    
    /**
     * 
     */
    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;

    /**
     * The default constructor
     */
    public Author() {
    }

    /**
     * 
     * @param authorId 
     */
    public Author(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 
     * @param authorId
     * @param authorName
     * @param dateAdded 
     */
    public Author(Integer authorId, String authorName, Date dateAdded) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    /**
     * 
     * @return 
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 
     * @param authorId 
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 
     * @return 
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 
     * @param authorName 
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 
     * @return 
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * 
     * @param dateAdded 
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.authorId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.authorId, other.authorId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }        
}
