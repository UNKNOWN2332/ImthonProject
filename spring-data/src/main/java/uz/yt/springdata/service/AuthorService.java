package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Author;
import uz.yt.springdata.DTO.AuthorDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.Repository.AuthorRepository;
import uz.yt.springdata.Validation.Valid;
import uz.yt.springdata.mapping.AuthorMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public ResponseDTO<List<AuthorDTO>> getAll()
    {
        List<Author> authors = authorRepository.findAll();
        if(!authors.isEmpty())
        {
            List<AuthorDTO> resultAuthorDTO = new ArrayList<>();
            for (Author author : authors) {
                resultAuthorDTO.add(AuthorMapping.toDTO(author));
            }
            return new ResponseDTO<>(true,0,"ACCESS",resultAuthorDTO,null);
        }
        return new ResponseDTO<>(false,-1,"Table is null",null,null);
    }

    public ResponseDTO<AuthorDTO> getOneById(Integer id)
    {
        Author author = authorRepository.getById(id);
        if(author.getId()!=null)
        {
            return new ResponseDTO<>(true,0,"ACCESS",AuthorMapping.toDTO(author),null);
        }
        return new ResponseDTO<>(false,-1,"Author not Found",null,null);
    }

    public ResponseDTO<AuthorDTO> updateAuthor(AuthorDTO authorDTO)
    {
        try {
            if (authorDTO.getId() == null) return new ResponseDTO<>(false, -2, "Id is null", null,null);
            List<String> list = Valid.chechAuthorAll(authorDTO);
            if(list.size()>0) return new ResponseDTO<>(false, -1,"Error",null,list);

            Optional<Author> _author = authorRepository.findById(authorDTO.getId());
            if(_author.isEmpty()) return new ResponseDTO<>(false, -3, "Id not found", null,null);
            Author author = _author.get();
            AuthorMapping.setEntity(author,authorDTO);
            authorRepository.save(author);
            return new ResponseDTO<>(true, 0, "ACCESS", authorDTO,null);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-4,e.getMessage(),null,null);
        }
    }


    public ResponseDTO<AuthorDTO> addAuthor(AuthorDTO authorDTO) {
        if (authorDTO.getId() !=null)
        if (authorRepository.existsById(authorDTO.getId())) return new ResponseDTO<>(false, -3, "Id is have", null,null);
        List<String> list = Valid.chechAuthorAll(authorDTO);
        if(list.size()>0) return new ResponseDTO<>(false, -1,"Error",null,list);


        Author author = AuthorMapping.toEntity(authorDTO);
        authorRepository.save(author);
        return new ResponseDTO<>(true, 0, "ACCESS", authorDTO,null);
    }

    public ResponseDTO<AuthorDTO> deleteAuthorById(Integer id) {

        Optional<Author> _author = authorRepository.findById(id);
        if(_author.isEmpty()) return new ResponseDTO<>(false, -4, "Id not found", null,null);
        Author author = _author.get();
        author.setIsacitve(1);
        authorRepository.save(author);

        return new ResponseDTO<>(true, 0, "ACCESS",  AuthorMapping.toDTO(author),null);
    }
}