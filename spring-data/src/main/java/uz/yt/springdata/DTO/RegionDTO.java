package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    private Integer id;
    private String nameuz;
    private String nameru;
    private String nameeng;

}
