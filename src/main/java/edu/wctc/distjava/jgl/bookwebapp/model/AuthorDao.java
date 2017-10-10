package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author jlombardo
 */
public class AuthorDao implements IAuthorDao {
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    private final String AUTHOR_NAME = "author_name";
    private final String DATE_ADDED = "date_added";

    public AuthorDao(String driverClass, String url, 
            String userName, String password,
            DataAccess db) {
        
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }
    
    @Override
    public int addAuthor(List<Object> colValue) 
            throws SQLException, ClassNotFoundException{
        db.openConnection(driverClass, url, userName, password);
        int recordAdded = db.addRecord(AUTHOR_TBL, Arrays.asList(AUTHOR_NAME, DATE_ADDED), colValue);
        db.closeConnection();
        return recordAdded;
    }
    
    @Override
    public int updateAuthor(List<Object> colValue, Object pkValue)
            throws SQLException, ClassNotFoundException{
        db.openConnection(driverClass, url, userName, password);
        int recordUpdated = db.updateRecord(AUTHOR_TBL, Arrays.asList(AUTHOR_NAME, DATE_ADDED), colValue, AUTHOR_PK, pkValue);
        db.closeConnection();
        return recordUpdated;
    }
    
    @Override
    public final int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        if(id == null || id < 1) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        db.openConnection(driverClass, url, userName, password);
        int recDeleted = db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);
        db.closeConnection();
        return recDeleted;
    }
    
    @Override
    public final List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);
        List<Author> list = new Vector<>();
        List<Map<String,Object>> rawData = 
                db.getAllRecords(AUTHOR_TBL, 0);
        
        Author author = null;
        
        for(Map<String,Object> rec : rawData) {
              author = new Author(); 
              
              Object objRecId = rec.get(AUTHOR_PK);
              Integer recId = objRecId == null ? 
                      0 : Integer.parseInt(objRecId.toString());
              author.setAuthorId(recId);
             
              Object objName = rec.get("author_name");
              String authorName = objName == null ? "" : objName.toString();
              author.setAuthorName(authorName);
              
              Object objRecAdded = rec.get("date_added");
              Date recAdded = objRecAdded == null ? null : (Date)objRecAdded;
              author.setDateAdded(recAdded);
              
              list.add(author); 
        }
        db.closeConnection();
        return list;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }
    
    

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDao dao = new AuthorDao(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin",
            new MySqlDataAccess()
        );
        
        int recsDeleted = dao.removeAuthorById(20);
        if (recsDeleted == 1){
            System.out.println("Record deleted successfully");
        } else {
            System.out.println(" Record is not deleted, Please check the ID");
        }
        
        dao.addAuthor(Arrays.asList("Steve Jobs", "2017-07-23"));
        
        dao.updateAuthor(Arrays.asList("Brian Thomas", "2017-09-23"), 9);
        
        List<Author> list = dao.getListOfAuthors();
        for(Author a: list) {
            System.out.println(a.getAuthorId() + ", "
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
    
}
