package uz.yt.springdata.mapping;

import lombok.RequiredArgsConstructor;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.DistrictDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class DistrictMapping {

    public static DistrictDTO toDTO(District district)
    {

        return district==null ? null : new DistrictDTO(district.getId(),
                district.getName_uz(),
                district.getName_ru(),
                district.getName_eng(),
                RegionMapping.toDTOs(district.getRegion())
               );
    }
    public static DistrictDTO toDTOs(District district)
    {
        return new DistrictDTO(district.getId(),
                district.getName_uz(),
                district.getName_ru(),
                district.getName_eng());
    }



    public static District toEntity(DistrictDTO districtDTO)
    {
        return new District(districtDTO.getId(),
                districtDTO.getName_uz(),
                districtDTO.getName_ru(),
                districtDTO.getName_eng());
    }
}
