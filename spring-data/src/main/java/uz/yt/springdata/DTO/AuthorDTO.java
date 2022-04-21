package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Integer id;

    private String Name;
    private String surname;
    private Date birthdate;
}
