package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.DAO.District;

public interface DistrictRepository extends JpaRepository<District,Integer> {
}
