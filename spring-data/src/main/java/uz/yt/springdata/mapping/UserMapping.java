package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DTO.UserDTO;

public class UserMapping {
    public static UserDTO toDTO (Users users)
    {
        return new UserDTO(users.getId(),
                users.getFirstname(),
                users.getLastname(),
                users.getPhonenumber(),
                users.getAccount(),
                users.getUsername(),
                users.getPassword());
    }

    public static Users toEntity (UserDTO userDTO)
    {
        return new Users(
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getPhonenumber(),
                userDTO.getAccount(),
                userDTO.getGmail(),userDTO.getPassword());
    }
    public static void setEntity(Users users, UserDTO userDTO)
    {
        users.setId(userDTO.getId());
        users.setFirstname(userDTO.getName());
        users.setLastname(userDTO.getSurname());
        users.setPhonenumber(userDTO.getPhonenumber());
        users.setAccount(userDTO.getAccount());
        users.setUsername(userDTO.getGmail());
        users.setPassword(userDTO.getPassword());
    }
}
