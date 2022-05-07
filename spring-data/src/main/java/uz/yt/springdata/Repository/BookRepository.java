package uz.yt.springdata.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yt.springdata.DAO.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Page<Book> getAllByCost(Pageable pageable,Double cost);



}
