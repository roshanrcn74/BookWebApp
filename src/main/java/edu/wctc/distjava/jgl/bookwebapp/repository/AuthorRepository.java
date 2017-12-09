package edu.wctc.distjava.jgl.bookwebapp.repository;


import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
//    @Query()
//    public abstract List<Author> findByAuthorName(@Param ("authorName"), String authorName);

}
