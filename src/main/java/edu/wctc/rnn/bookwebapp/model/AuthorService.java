
package edu.wctc.rnn.bookwebapp.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AuthorService {
    public List<Author> getAuthorList(){
        return Arrays.asList(
                new Author(1,"Mark Twan",new Date()),
                new Author(2,"Stephen Twan",new Date()),
                new Author(3,"Gorge Twan",new Date())
        );
    } 
}
