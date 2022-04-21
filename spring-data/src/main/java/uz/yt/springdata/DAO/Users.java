package uz.yt.springdata.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String firstname;
    private String lastname;
    private String phonenumber;
    private Double account;
    private String username;
    private String password;
    private Integer isactive=0;

    public Users(String firstname, String lastname, String phonenumber, Double account, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.account = account;
        this.username = username;
        this.password = password;
    }
}
