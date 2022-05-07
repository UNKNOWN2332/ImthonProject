package uz.yt.springdata.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class District {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer id;
        private String name_uz;
        private String name_ru;
        private String name_eng;



        @ManyToOne
        @JoinColumn(name = "region_id",referencedColumnName = "id")
        private Region region;

        public District(Integer id, String name_uz, String name_ru, String name_eng) {
                this.id = id;
                this.name_uz = name_uz;
                this.name_ru = name_ru;
                this.name_eng = name_eng;
        }
}
