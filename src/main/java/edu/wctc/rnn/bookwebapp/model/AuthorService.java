
package edu.wctc.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AuthorService {
    
    private IAuthorDao authorDao;
    
    public AuthorService(IAuthorDao authorDao){
        setAuthorDao(authorDao);
    }
    
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException{
//        return Arrays.asList(
//                new Author(1,"Mark Twan",new Date()),
//                new Author(2,"Stephen Twan",new Date()),
//                new Author(3,"Gorge Twan",new Date())
//        );
        return authorDao.getListofAuthors();
    }
    
    public int removeAuthorById(String id) throws ClassNotFoundException, SQLException, NumberFormatException{
        if (id == null ){
        throw new IllegalArgumentException("It must be integer greater than 0");
        }
        Integer value = Integer.parseInt(id);
        return authorDao.removeAuthorById(value);
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        IAuthorDao dao = new AuthorDao(
                new MySqlServerDatabase(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book1",
                "root",
                "admin")
        );
        
        AuthorService authorService = new AuthorService(dao);
        
        int recsDeleted = authorService.removeAuthorById("3");
        
         List<Author> list = authorService.getAuthorList();
        
        for(Author a: list) {
            System.out.println(a.getAuthorId() + ", "
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
