package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.yt.springdata.DAO.Region;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DistrictDTO {
    private Integer id;
    private Integer region_id;
    private String name_uz;
    private String name_ru;
    private String name_eng;

    private RegionDTO regionDTO;

    public DistrictDTO(Integer id, String name_uz, String name_ru, String name_eng, RegionDTO regionDTO) {
        this.id = id;
        this.name_uz = name_uz;
        this.name_ru = name_ru;
        this.name_eng = name_eng;
        this.regionDTO = regionDTO;
    }

    public DistrictDTO(Integer id, Integer region_id, String name_uz, String name_ru, String name_eng) {
        this.id = id;
        this.region_id = region_id;
        this.name_uz = name_uz;
        this.name_ru = name_ru;
        this.name_eng = name_eng;
    }
}
