package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.DAO.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>
{

}
