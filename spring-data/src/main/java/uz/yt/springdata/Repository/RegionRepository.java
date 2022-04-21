package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.DAO.Region;

public interface RegionRepository extends JpaRepository<Region,Integer> {
}
