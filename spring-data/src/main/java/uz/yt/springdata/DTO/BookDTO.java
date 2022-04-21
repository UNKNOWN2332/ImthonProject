package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;

@Data
@NoArgsConstructor
public class BookDTO {
    private Integer id ;

    private String nameuz;

    private String nameru;

    private Double cost;

    private Date published_date;

    private Integer page_count;

    private Integer author_id;

    private String genre;

    private Integer publisher_id;
    private AuthorDTO authorDTO;
    private PublisherDTO publisherDTO;

    public BookDTO(Integer id, String nameuz, String nameru, Double cost, Date published_date, Integer page_count, Integer author_id, String genre, Integer publisher_id) {
        this.id = id;
        this.nameuz = nameuz;
        this.nameru = nameru;
        this.cost = cost;
        this.published_date = published_date;
        this.page_count = page_count;
        this.author_id = author_id;
        this.genre = genre;
        this.publisher_id = publisher_id;
    }
}
