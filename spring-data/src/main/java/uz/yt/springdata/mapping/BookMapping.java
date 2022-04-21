package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.Book;
import uz.yt.springdata.DTO.BookDTO;
import uz.yt.springdata.DTO.ResponseDTO;

public class BookMapping {
    public static BookDTO toDTO(Book book)
    {
        return new BookDTO(book.getId(),
                book.getNameuz(),book.getNameru(),
                book.getCost(),
                book.getPublished_date(),
                book.getPage_count(),book.getAuthor_id(),
                book.getGenre(),book.getPublisher_id());
    }

    public static Book toEntity(BookDTO bookDTO)
    {
        return new Book(bookDTO.getId(),
                bookDTO.getNameuz(),
                bookDTO.getNameru(),
                bookDTO.getCost(),
                bookDTO.getPublished_date(),
                bookDTO.getPage_count(),
                bookDTO.getAuthor_id(),
                bookDTO.getGenre(),
                bookDTO.getPublisher_id());
    }

    public static void setEntity(Book book , BookDTO bookDTO)
    {
        book.setId(bookDTO.getId());
        book.setNameuz(bookDTO.getNameuz());
        book.setNameru(bookDTO.getNameru());
        book.setCost(bookDTO.getCost());
        book.setPublished_date(bookDTO.getPublished_date());
        book.setPage_count(bookDTO.getPage_count());
        book.setGenre(bookDTO.getGenre());
        book.setPublisher_id(bookDTO.getPublisher_id());
    }
}
