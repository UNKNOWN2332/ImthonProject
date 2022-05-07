package uz.yt.springdata.mapping;

import org.jetbrains.annotations.Contract;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.RegionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RegionMapping{
    public static RegionDTO toDTO(Region region)
    {
        List<DistrictDTO> district = region.getDistrict().
                stream()
                .map(DistrictMapping::toDTOs).
                collect(Collectors.toList());

        return new RegionDTO(region.getId(),
                region.getNameuz(),
                region.getNameru(),
                region.getNameeng(),
                district);
    }


    public static RegionDTO toDTOs(Region region)
    {
        return region==null ?null: new RegionDTO(region.getId(),
                region.getNameuz(),
                region.getNameru(),
                region.getNameeng());
    }
    public static Region toEntity(RegionDTO regionDTO)
    {

        return new Region(regionDTO.getId(),
                regionDTO.getNameuz(),
                regionDTO.getNameru(),
                regionDTO.getNameeng());
    }

//    public static Region setEntity(Region region,RegionDTO regionDTO)
//    {
//        region.setId(regionDTO.getId());
//        region.setNameuz(regionDTO.getNameuz());
//        region.setNameru(regionDTO.getNameru());
//        region.setNameeng(regionDTO.getNameeng());
//        return region;
//    }
//    public static RegionDTO setDTO(Region region,RegionDTO regionDTO)
//    {
//        regionDTO.setId(region.getId());
//        regionDTO.setNameuz(region.getNameuz());
//        regionDTO.setNameru(region.getNameru());
//        regionDTO.setNameeng(region.getNameeng());
//        return regionDTO;
//    }
}
