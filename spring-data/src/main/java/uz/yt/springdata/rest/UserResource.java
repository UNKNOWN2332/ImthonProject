package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DTO.AuthorDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.DTO.UserDTO;
import uz.yt.springdata.service.AuthorService;
import uz.yt.springdata.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserResource {
    private final UserService userService;
    @GetMapping("getall")
    public ResponseDTO<List<UserDTO>> getAll()
    {
        return userService.getall();
    }
    @GetMapping("getbyid")
    public ResponseDTO<UserDTO>getAll(@RequestParam Integer id) {return userService.getoneByid(id);}


    @PostMapping("add")
    public ResponseDTO<UserDTO> add(@RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }

    @PutMapping ("update")
    public ResponseDTO<UserDTO> update(@RequestBody UserDTO userDTO)
    {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping ("delete")
    public ResponseDTO<UserDTO> delete(@RequestParam Integer id)
    {
        return userService.deleteUserbyId(id);
    }

}
