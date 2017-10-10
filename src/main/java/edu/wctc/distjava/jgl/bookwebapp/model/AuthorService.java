package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jlombardo
 */
public class AuthorService {
    private IAuthorDao authorDao;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    private final String AUTHOR_NAME = "author_name";
    private final String DATE_ADDED = "date_added";

    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public final int removeAuthorById(String id) 
            throws ClassNotFoundException, SQLException, 
            NumberFormatException {
        
        if (id == null) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        
        Integer value = Integer.parseInt(id);

        return authorDao.removeAuthorById(value);
    }
    
    public int addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException{
        
        return authorDao.addAuthor(colValues);
    }
    
    public int updateAuthorById(List <Object> colValues, String id) throws SQLException, ClassNotFoundException{   
        return authorDao.updateAuthor(colValues, id);
    }

    public List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {

        return authorDao.getListOfAuthors();
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

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
        
        int recsUpdated = authorService.updateAuthorById(Arrays.asList("Jim Carter", "2017-09-12"), "14");
        
        System.out.println(recsUpdated + " Author updated");

        List<Author> list = authorService.getAuthorList();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
