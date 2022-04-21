package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer id;
    private Integer region_id;
    private Integer district_id;
    private String street;
    private String homenumber;
    private String orient;
    private RegionDTO regionDTO;
    private DistrictDTO districtDTO;

    public AddressDTO(Integer id, String street, String homenumber, String orient, RegionDTO regionDTO, DistrictDTO districtDTO) {
        this.id = id;
        this.street = street;
        this.homenumber = homenumber;
        this.orient = orient;
        this.regionDTO = regionDTO;
        this.districtDTO = districtDTO;
    }

    public AddressDTO(Integer id, Integer region_id, Integer district_id, String street, String homenumber, String orient) {
        this.id = id;
        this.region_id = region_id;
        this.district_id = district_id;
        this.street = street;
        this.homenumber = homenumber;
        this.orient = orient;
    }
}
