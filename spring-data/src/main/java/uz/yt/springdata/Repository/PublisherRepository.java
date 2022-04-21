package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.DAO.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
}
