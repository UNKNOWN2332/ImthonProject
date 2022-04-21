package uz.yt.springdata.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Repository;
import uz.yt.springdata.DAO.Book;

import java.math.BigDecimal;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Long countAllByCost(BigDecimal cost);
    Page<Book> findAllByGenre(String genre , Pageable pageable);
}
