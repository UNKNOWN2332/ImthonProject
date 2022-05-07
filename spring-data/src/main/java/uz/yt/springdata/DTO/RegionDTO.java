package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    private Integer id;
    private String nameuz;
    private String nameru;
    private String nameeng;
    private List<DistrictDTO> districtDTO;

    public RegionDTO(Integer id, String nameuz, String nameru, String nameeng) {
        this.id = id;
        this.nameuz = nameuz;
        this.nameru = nameru;
        this.nameeng = nameeng;
    }
}
