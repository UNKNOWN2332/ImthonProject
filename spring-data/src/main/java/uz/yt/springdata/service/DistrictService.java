package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.Repository.DistrictRepository;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.Validation.Valid;
import uz.yt.springdata.mapping.DistrictMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public ResponseDTO<Page<DistrictDTO>> findAll(Integer page, Integer size)
    {

        PageRequest pageRequest = PageRequest.of(page,size);
        Page<District> districts = districtRepository.findAll(pageRequest);
        if(!districts.isEmpty())
        {
            List<DistrictDTO> districtDTOList =districts
                    .stream()
                    .map(DistrictMapping::toDTO)
                    .collect(Collectors.toList());


            Page<DistrictDTO>  result = new PageImpl<>(districtDTOList,pageRequest,districts.getTotalElements());
            return new ResponseDTO<>(true,0,"ACCESS",result,null);
        }
        return new ResponseDTO<>(false,-1,"DB Error",null,null);
    }

    public ResponseDTO<DistrictDTO> findById(Integer id)
    {
        District district = districtRepository.getById(id);
        if(district.getId()!=null && district!=null)
        {
            DistrictDTO districtDTO = DistrictMapping.toDTO(district);
        return new ResponseDTO<>(true,0,"ACCESS",DistrictMapping.toDTO(district),null);}

        return new ResponseDTO<>(false,-1,"Id is null",null,null);

    }
    public ResponseDTO<DistrictDTO> addNew(DistrictDTO districtDTO)
    {
        try {

            if (districtDTO.getId() != null && districtRepository.existsById(districtDTO.getId()))
                return new ResponseDTO<>(false, -1, "Id is have", null,null);
            List<String> list = Valid.chechDistrictAll(districtDTO);
            if(list.size()>0) return new ResponseDTO<>(false, -1 , "Error",null,list);
            District district = DistrictMapping.toEntity(districtDTO);
            districtRepository.save(district);
            districtDTO = DistrictMapping.toDTO(district);
            districtDTO.setId(district.getId());
            return new ResponseDTO<>(true,0,"ACCESS",districtDTO,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null,null);
        }
    }
    public ResponseDTO<DistrictDTO> update(DistrictDTO districtDTO)
    {
        try{
        if (districtDTO.getId()==null) return new ResponseDTO<>(false,-3,"Id is null",null,null);
        if (!districtRepository.existsById(districtDTO.getId())) return new ResponseDTO<>(false,-4,"id not found",null,null);
            List<String> list = Valid.chechDistrictAll(districtDTO);
            if(list.size()>0) return new ResponseDTO<>(false, -1 , "Error",null,list);
        District district =DistrictMapping.toEntity(districtDTO);
        districtDTO = DistrictMapping.toDTO(district);
        districtRepository.save(district);

        return new ResponseDTO<>(true,0,"ACCESS",districtDTO,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null,null);
        }
    }

}
