package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Author;
import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DTO.AuthorDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.DTO.UserDTO;
import uz.yt.springdata.Repository.UserRepository;
import uz.yt.springdata.mapping.AuthorMapping;
import uz.yt.springdata.mapping.UserMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public ResponseDTO<List<UserDTO>> getall()
    {
        List<Users> users = userRepository.findAll();
        if(!users.isEmpty())
        {
            List<UserDTO> resultUserDTO = new ArrayList<>();
            for (Users user : users) {
                resultUserDTO.add(UserMapping.toDTO(user));
            }
            return new ResponseDTO<>(true,0,"ACCESS",resultUserDTO);
        }
        return new ResponseDTO<>(false,-1,"Table is null",null);
    }

    public ResponseDTO<UserDTO> getoneByid(Integer id)
    {
        Users users = userRepository.getById(id);
        if(users !=null && users.getId()!=null)
        {
            return new ResponseDTO<>(true,0,"ACCESS",UserMapping.toDTO(users));
        }
        return new ResponseDTO<>(false,-1,"Users not Found",null);
    }

    public ResponseDTO<UserDTO> updateUser(UserDTO userDTO)
    {
        try {
            if(userDTO.getId()==null) return new ResponseDTO<>(false,-2,"ID is null",null);
            Optional<Users> userid = userRepository.findById(userDTO.getId());
            Optional<Users> userusername = userRepository.findByUsername(userDTO.getGmail());
            Optional<Users> userphonenumber = userRepository.findByPhonenumber(userDTO.getPhonenumber());

            if(!userid.isPresent()) return new ResponseDTO<>(false,-3,"Id is have",null);
            if(!userusername.isPresent()) return new ResponseDTO<>(false,-4,"Username is have",null);
            if(!userphonenumber.isPresent()) return new ResponseDTO<>(false,-5,"phone number is have",null);
            if(userDTO.getName()==null) return new ResponseDTO<>(false,-6,"name is null",null);
            if(userDTO.getSurname()==null) return new ResponseDTO<>(false,-7,"surname is null",null);
            if(userDTO.getPassword()==null) return new ResponseDTO<>(false,-8,"password is null",null);
            if(userDTO.getPhonenumber()==null) return new ResponseDTO<>(false,-9,"phone number is null",null);
            if(userDTO.getGmail()==null) return new ResponseDTO<>(false,-10,"gmail is null",null);

            Users users = UserMapping.toEntity(userDTO);
            userRepository.save(users);
            return new ResponseDTO<>(true,0,"ACCESS",userDTO);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-4,e.getMessage(),null);
        }
    }


    public ResponseDTO<UserDTO> addUser(UserDTO userDTO) {
        if (userDTO.getId() !=null)
            if (userRepository.existsById(userDTO.getId())) return new ResponseDTO<>(false, -3, "Id is have", null);
        Optional<Users> userusername = userRepository.findByUsername(userDTO.getGmail());
        Optional<Users> userphonenumber = userRepository.findByPhonenumber(userDTO.getPhonenumber());
        if(userusername.isPresent()) return new ResponseDTO<>(false,-4,"Username is have",null);
        if(userphonenumber.isPresent()) return new ResponseDTO<>(false,-5,"phone number is have",null);
        if(userDTO.getName()==null) return new ResponseDTO<>(false,-6,"name is null",null);
        if(userDTO.getSurname()==null) return new ResponseDTO<>(false,-7,"surname is null",null);
        if(userDTO.getPassword()==null) return new ResponseDTO<>(false,-8,"password is null",null);
        if(userDTO.getPhonenumber()==null) return new ResponseDTO<>(false,-9,"phone number is null",null);
        if(userDTO.getGmail()==null) return new ResponseDTO<>(false,-10,"gmail is null",null);
        Pattern pattern = Pattern.compile("\\+998[[3][7-9]][0-9]-[0-9]{3}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(userDTO.getPhonenumber());
        if(!matcher.find())
            return new ResponseDTO<>(false,-11,"Nomer Format Notogri",null);

        Users users = UserMapping.toEntity(userDTO);
        userRepository.save(users);
        userDTO.setId(users.getId());
        return new ResponseDTO<>(true, 0, "ACCESS", userDTO);
    }

    public ResponseDTO<UserDTO> deleteUserbyId(Integer id) {

        Optional<Users> _user = userRepository.findById(id);
        if(!_user.isPresent()) return new ResponseDTO<>(false, -4, "Id not found", null);
        Users users = _user.get();
        users.setIsactive(1);
        userRepository.save(users);

        return new ResponseDTO<>(true, 0, "ACCESS",  UserMapping.toDTO(users));
    }
}
