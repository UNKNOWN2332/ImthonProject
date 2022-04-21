package uz.yt.springdata.mapping;

import lombok.RequiredArgsConstructor;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.RegionDTO;
import uz.yt.springdata.Repository.RegionRepository;
@RequiredArgsConstructor
public class DistrictMapping {

    public static DistrictDTO toDTO(District district)
    {


        return new DistrictDTO(district.getId(),
                district.getRegion_id(),
                district.getName_uz(),
                district.getName_ru(),
                district.getName_eng());



    }

    public static District toEntity(DistrictDTO districtDTO)
    {
        return new District(districtDTO.getId(),
                districtDTO.getRegion_id(),
                districtDTO.getName_uz(),
                districtDTO.getName_ru(),
                districtDTO.getName_eng());
    }

    public static District setEntity(District district,DistrictDTO districtDTO)
    {
        district.setId(districtDTO.getId());
        district.setRegion_id(districtDTO.getRegion_id());
        district.setName_uz(districtDTO.getName_uz());
        district.setName_ru(districtDTO.getName_ru());
        district.setName_eng(districtDTO.getName_eng());
        return district;
    }
    public static DistrictDTO setDTO(District district,DistrictDTO districtDTO)
    {
        districtDTO.setId(district.getId());
        districtDTO.setRegion_id(district.getRegion_id());
        districtDTO.setName_uz(district.getName_uz());
        districtDTO.setName_ru(district.getName_ru());
        districtDTO.setName_eng(district.getName_eng());
        return districtDTO;
    }
}
