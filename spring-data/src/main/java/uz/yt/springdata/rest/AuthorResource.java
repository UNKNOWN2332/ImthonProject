package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DTO.AuthorDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.service.AuthorService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorResource {
    private final AuthorService authorService;
    @GetMapping("getall")
    public ResponseDTO<List<AuthorDTO>>getAll()
    {
        return authorService.getall();
    }
    @GetMapping("getbyid")
    public ResponseDTO<AuthorDTO>getAll(@RequestParam Integer id)
    {
        return authorService.getoneByid(id);
    }


    @PostMapping("add")
    public ResponseDTO<AuthorDTO> add(@RequestBody AuthorDTO authorDTO)
    {
        return authorService.addAuthor(authorDTO);
    }

    @PutMapping ("update")
    public ResponseDTO<AuthorDTO> update(@RequestBody AuthorDTO authorDTO)
    {
        return authorService.updateAuthor(authorDTO);
    }

    @DeleteMapping ("delete")
    public ResponseDTO<AuthorDTO> delete(@RequestParam Integer id)
    {
        return authorService.deleteAuthorbyId(id);
    }



}
