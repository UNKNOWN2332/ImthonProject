package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.Author;
import uz.yt.springdata.DAO.Book;
import uz.yt.springdata.DTO.AuthorDTO;

public class AuthorMapping {
    public static AuthorDTO toDTO(Author author)
    {
        return new AuthorDTO(author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthDate());
    }

    public static Author toEntity(AuthorDTO authorDTO)
    {
        return new Author(authorDTO.getId(),
                authorDTO.getName(),
                authorDTO.getSurname(),
                authorDTO.getBirthdate());
    }

    public static Author setEntity(Author author,AuthorDTO authorDTO)
    {
                author.setId(authorDTO.getId());
                author.setFirstName(authorDTO.getName());
                author.setLastName(authorDTO.getSurname());
                author.setBirthDate(authorDTO.getBirthdate());
                return author;
    }
    public static AuthorDTO setDTO(Author author,AuthorDTO authorDTO)
    {
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getFirstName());
        authorDTO.setSurname(author.getLastName());
        authorDTO.setBirthdate(author.getBirthDate());
        return authorDTO;
    }
}
