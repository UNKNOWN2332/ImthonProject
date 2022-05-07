package uz.yt.springdata.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nameuz;
    private String nameru;
    private String nameeng;

    @OneToMany(mappedBy = "region")
    private List<District> district;

    public Region(Integer id, String nameuz, String nameru, String nameeng) {
        this.id = id;
        this.nameuz = nameuz;
        this.nameru = nameru;
        this.nameeng = nameeng;
    }
}
