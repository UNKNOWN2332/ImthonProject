package uz.yt.springdata.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "birthdate")
    private Date birthDate;
    @Column(name = "isactive")
    private Integer isacitve =0;

    public Author(Integer id,String firstName, String lastName, Date birthDate) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
