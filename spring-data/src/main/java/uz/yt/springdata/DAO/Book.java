package uz.yt.springdata.DAO;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id ;

    @Column(name = "nameuz")
    private String nameuz;

    @Column(name = "nameru")
    private String nameru;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "published_day")
    private Date published_date;

    @Column(name = "page_count")
    private Integer page_count;

    @Column(name = "author_id")
    private Integer author_id;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publisher_id")
    private Integer publisher_id;

    private Integer isactive=0;


    public Book(Integer id, String nameuz, String nameru, Double cost, Date published_date, Integer page_count, Integer author_id, String genre, Integer publisher_id) {
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
