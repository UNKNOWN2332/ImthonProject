package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yt.springdata.DAO.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
