package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.Repository.DistrictRepository;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.mapping.DistrictMapping;
import uz.yt.springdata.mapping.RegionMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    public ResponseDTO<List<DistrictDTO>> findall()
    {
        List<District> districts = districtRepository.findAll();
        if(!districts.isEmpty())
        {
            List<DistrictDTO> resultDistrictDTO = new ArrayList<>();
            for (District district : districts) {
                DistrictDTO districtDTO = DistrictMapping.toDTO(district);
                districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(district.getRegion_id())));
                resultDistrictDTO.add(districtDTO);
            }
            return new ResponseDTO<>(true,0,"ACCESS",resultDistrictDTO);
        }
        return new ResponseDTO<>(false,-1,"ERROR",null);
    }

    public ResponseDTO<DistrictDTO> findbyid(Integer id)
    {
        District district = districtRepository.getById(id);
        if(district.getId()!=null && district!=null)
        {
            DistrictDTO districtDTO = DistrictMapping.toDTO(district);
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(district.getRegion_id())));
        return new ResponseDTO<>(true,0,"ACCESS",DistrictMapping.toDTO(district));}

        return new ResponseDTO<>(false,-1,"Id is null",null);

    }
    public ResponseDTO<DistrictDTO> addnew(DistrictDTO districtDTO)
    {
        try {

            if (districtDTO.getId() != null && districtRepository.existsById(districtDTO.getId()))
                return new ResponseDTO<>(false, -1, "ID is have", null);
            District district = DistrictMapping.toEntity(districtDTO);
            districtRepository.save(district);
            districtDTO = DistrictMapping.toDTO(district);
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(district.getRegion_id())));
            districtDTO.setId(district.getId());
            return new ResponseDTO<>(true,0,"ACCESS",districtDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null);
        }
    }
    public ResponseDTO<DistrictDTO> update(DistrictDTO districtDTO)
    {
        try{
        if (districtDTO.getId()==null) return new ResponseDTO<>(false,-3,"Id is null",null);
        if (!districtRepository.existsById(districtDTO.getId())) return new ResponseDTO<>(false,-4,"id is no",null);
        District district =DistrictMapping.toEntity(districtDTO);
        districtDTO = DistrictMapping.toDTO(district);
        districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(district.getRegion_id())));
        districtRepository.save(district);

        return new ResponseDTO<>(true,0,"ACCESS",districtDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null);
        }
    }

}
