package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author service class that acts as a provider to the upper layers with the operations of the Author objects
 * @author jlombardo
 */
public class AuthorService {
    private IAuthorDao authorDao;

    /**
     * Constructor with the DAO object
     * @param authorDao 
     */
    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    /**
     * Removes the Author from the author record from the database. 
     * Returns 1 if the record is removed. Otherwise, returns 0.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws NumberFormatException 
     */
    public final int removeAuthorById(String id) 
            throws ClassNotFoundException, SQLException, 
            NumberFormatException {
        
        if (id == null) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        
        Integer value = Integer.parseInt(id);

        return authorDao.removeAuthorById(value);
    }
    
    /**
     * 
     * @return 
     */
    public String getDate(){
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate= formatter.format(date);  
        return strDate;
    }
    
    /**
     * Adds the author record to the database with the given parameters. Throws an exception
     * if the given author id is already existing in the database.
     * @param colValues
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int addAuthor(List<Object> colValues) 
            throws SQLException, ClassNotFoundException{
        
        return authorDao.addAuthor(colValues);
    }
    
    /**
     * Updates the author record with the given details for the selected author record
     * @param colValues
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int updateAuthorById(List <Object> colValues, String id) 
            throws SQLException, ClassNotFoundException{   
        return authorDao.updateAuthor(colValues, id);
    }

    /**
     * Gets the complete author list from the database
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {

        return authorDao.getListOfAuthors();
    }
    
    /**
     * Finds the Author record for a given author id
     * @param authorId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Author findAuthor(String authorId) 
            throws ClassNotFoundException, SQLException{
        int id = Integer.parseInt(authorId);   
        return authorDao.findAuthorById(id);
    }

    /**
     * 
     * @return 
     */
    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public final void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    /**
     * Main method - only for testing
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );

        AuthorService authorService
                = new AuthorService(dao);
        
        int recsDeleted = authorService.removeAuthorById("13");
        
        System.out.println(recsDeleted + " Author deleted");
        
        int recsAdded = authorService.addAuthor(Arrays.asList("Rikad Powel", "2017-10-12"));
        
        System.out.println(recsAdded + " Author added");
        
        int recsUpdated = authorService.updateAuthorById(Arrays.asList("Joe Root", "2017-09-12"), "15");
        
        System.out.println(recsUpdated + " Author updated");

        List<Author> list = authorService.getAuthorList();

        list.forEach((a) -> {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        });
        
        System.out.println("Author Found : " + authorService.findAuthor("15"));
        
    }
}
