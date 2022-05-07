package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.DTO.UserDTO;
import uz.yt.springdata.Repository.UserRepository;
import uz.yt.springdata.Validation.Valid;
import uz.yt.springdata.mapping.UserMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            return new ResponseDTO<>(true,0,"ACCESS",resultUserDTO,null);
        }
        return new ResponseDTO<>(false,-1,"Table is null",null,null);
    }

    public ResponseDTO<UserDTO> getoneByid(Integer id)
    {
        Users users = userRepository.getById(id);
        if(users ==null ||users.getId()==null)
            return new ResponseDTO<>(false,-1,"Users not Found",null,null);
        return new ResponseDTO<>(true,0,"ACCESS",UserMapping.toDTO(users),null);
    }

    public ResponseDTO<UserDTO> updateUser(UserDTO userDTO)
    {
        try {
            List<String> list=Valid.checkAllUsers(userDTO);
            if(list.size()>0)
                return new ResponseDTO<>(false,-1,"Error",null,list);
            Users users = UserMapping.toEntity(userDTO);
            userRepository.save(users);
            return new ResponseDTO<>(true,0,"ACCESS",userDTO,null);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            List<String> list = Valid.checkAllUsers(userDTO);
            return new ResponseDTO<>(false,-4,e.getMessage(),null,list);
        }
    }


    public ResponseDTO<UserDTO> addUser(UserDTO userDTO) {

        List<String> list = Valid.checkAllUsers(userDTO);
        if(list.size()>0) return new ResponseDTO<>(true, 0, "ACCESS", userDTO,list);
        Users users = UserMapping.toEntity(userDTO);
        userRepository.save(users);
        userDTO.setId(users.getId());
        return new ResponseDTO<>(true, 0, "ACCESS", userDTO,null);
    }

    public ResponseDTO<UserDTO> deleteUserbyId(Integer id) {

        Optional<Users> _user = userRepository.findById(id);
        if(!_user.isPresent()) return new ResponseDTO<>(false, -4, "Id not found", null,null);
        Users users = _user.get();
        users.setIsactive(1);
        userRepository.save(users);

        return new ResponseDTO<>(true, 0, "ACCESS",  UserMapping.toDTO(users),null);
    }
}
