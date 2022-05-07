package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.RegionDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.Validation.Valid;
import uz.yt.springdata.mapping.RegionMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    public ResponseDTO<List<RegionDTO>> getAll() {
        List<Region> regions = regionRepository.findAll();
        if(!regions.isEmpty()) {
            List<RegionDTO> ResultRegionDTO = new ArrayList<>();

            for (Region region : regions) {
                ResultRegionDTO.add(RegionMapping.toDTO(region));
            }
            return new ResponseDTO<>(
                    true,
                    0,
                    "ACCESS",
                    ResultRegionDTO,
                    null);
        }
        return new ResponseDTO<>(
                false,
                -1,
                "ERROR",
                null,
                null);
    }






    public ResponseDTO<RegionDTO> getOneById(Integer id)
    {
        if(id==null) return new ResponseDTO<>(false,-2,"Id is null",null,null);
        Region region = regionRepository.getById(id);
        if(region.getId() != null)
        {
            return new ResponseDTO<>(true,0,"ACCESS",RegionMapping.toDTO(region),null);
        }
        return new ResponseDTO<>(false,-3,"NOT FOUND",null,null);

    }






    public ResponseDTO<RegionDTO> addRegion(RegionDTO regionDTO) {

        if(regionDTO.getId()!=null)
            if(regionRepository.existsById(regionDTO.getId())) return new ResponseDTO<>(false ,-3,"Id is have",null,null);
        List<String> list = Valid.checkRegionAll(regionDTO);
        if(list.size()>0) return new ResponseDTO<>(false,-1,"ERROR",null,list);
        Region region = RegionMapping.toEntity(regionDTO);
        regionRepository.save(region);
        regionDTO.setId(region.getId());
        return new ResponseDTO<>(true,0,"ACCESS",regionDTO,list);
    }







    public ResponseDTO<RegionDTO> update(RegionDTO regionDTO) {
        if(regionDTO.getId()==null) return new ResponseDTO<>(false,-1,"Id is null",null,null);
        if(!regionRepository.existsById(regionDTO.getId())) return new ResponseDTO<>(false ,-3,"Id not found",null,null);
        List<String> list = Valid.checkRegionAll(regionDTO);
        if(list.size()>0) return new ResponseDTO<>(false,-1,"ERROR",null,list);

        Region region = RegionMapping.toEntity(regionDTO);
        regionRepository.save(region);
        return new ResponseDTO<>(true,0,"ACCESS",regionDTO,null);
    }
}
