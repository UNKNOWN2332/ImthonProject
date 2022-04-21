package uz.yt.springdata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DTO.UserDTO;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer>
{
    Optional<Users> findByUsername(String username);
    Optional<Users> findByPhonenumber(String phonenumber);
}
