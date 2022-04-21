package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.Author;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.AuthorDTO;
import uz.yt.springdata.DTO.RegionDTO;

public class RegionMapping {
    public static RegionDTO toDTO(Region region)
    {
        return new RegionDTO(region.getId(),
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

    public static Region setEntity(Region region,RegionDTO regionDTO)
    {
        region.setId(regionDTO.getId());
        region.setNameuz(regionDTO.getNameuz());
        region.setNameru(regionDTO.getNameru());
        region.setNameeng(regionDTO.getNameeng());
        return region;
    }
    public static RegionDTO setDTO(Region region,RegionDTO regionDTO)
    {
        regionDTO.setId(region.getId());
        regionDTO.setNameuz(region.getNameuz());
        regionDTO.setNameru(region.getNameru());
        regionDTO.setNameeng(region.getNameeng());
        return regionDTO;
    }
}
