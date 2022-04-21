package uz.yt.springdata.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class District {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer id;
        private Integer region_id;
        private String name_uz;
        private String name_ru;
        private String name_eng;
}
