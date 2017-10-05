/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Roshan
 */
public final class AuthorDao implements IAuthorDao {

//    private String url;
//    private String driverClass;
//    private String userName;
//    private String passWord;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    public AuthorDao(/*String url, String driverClass, String userName,
            String passWord,*/ DataAccess db) {
//        setUrl(url);
//        setDriverClassName(driverClass);
//        setUserName(userName);
//        setPassWord(passWord);
        setDb(db);
    }

    @Override
    public List<Author> getListofAuthors()
            throws SQLException, ClassNotFoundException {

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData = db.getAllRecords("AUTHOR_TBL", 0);
        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();
            Object objRecId = rec.get("AUTHOR_PK");
            Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;

            author.setDateAdded(recAdded);

//            author.setAuthorId(Integer.parseInt(rec.get("author_id").toString()));
//            
//            author.setAuthorName(rec.get("author_name").toString());
//            author.setDateAdded((Date)rec.get("date_added"));
            list.add(author);
//            Integer autourId = null;
//            Object objAuthorId = rec.get("author_id");
//            authorId = Integer.parseInt(objAuthorId.toString());
//            Set<String> keys = rec.keySet();
//            for (String key : keys){
//                author.setAuthorId(key);
//            }

        }

        return list;
    }

    @Override
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException{
    if (id == null || id < 1 ){
        throw new IllegalArgumentException("It must be integer greater than 0");
    }
    return db.deleteRecordbyId("AUTHOR_TBL", "AUTHOR_PK", id);  
}
//    public String getUrl() {
//        return url;
//    }

//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getDriverClassName() {
//        return driverClass;
//    }
//
//    public void setDriverClassName(String driverClassName) {
//        this.driverClass = driverClassName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    //psvm
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataAccess db = new MySqlServerDatabase(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin");

        AuthorDao dao = new AuthorDao(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book1",
//                "root",
//                "admin",
                db
        );

//        System.out.println(dao.getListofAuthors());
        List<Author> list = dao.getListofAuthors();

        list.forEach((author) -> {
            System.out.println(author.getAuthorId() + " " + author.getAuthorName() + " " + author.getDateAdded());
        });
        System.out.println("Number of record deleted :" + dao.removeAuthorById( 8));
        
        List<Author> list1 = dao.getListofAuthors();

        list1.forEach((author) -> {
            System.out.println(author.getAuthorId() + " " + author.getAuthorName() + " " + author.getDateAdded());
        });
    }

}
    