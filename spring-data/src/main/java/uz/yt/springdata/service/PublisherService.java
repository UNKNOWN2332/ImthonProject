package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Publisher;
import uz.yt.springdata.DTO.*;
import uz.yt.springdata.Repository.DistrictRepository;
import uz.yt.springdata.Repository.PublisherRepository;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.Validation.Valid;
import uz.yt.springdata.mapping.AddressMapping;
import uz.yt.springdata.mapping.DistrictMapping;
import uz.yt.springdata.mapping.PublisherMapping;
import uz.yt.springdata.mapping.RegionMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public ResponseDTO<List<PublisherDTO>> getAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        if(!publishers.isEmpty()) {
            List<PublisherDTO> ResultPublisherDTO =
            publishers.stream().map(
                    publisher ->{
                        PublisherDTO publisherDTO = PublisherMapping.toDTO(publisher);
                        publisherDTO.setAddressDTO(AddressMapping.toDTO(publisher.getAddress()));
                        publisherDTO.getAddressDTO().setDistrictDTO(DistrictMapping.toDTO(districtRepository.getById(publisher.getAddress().getDistrict_id())));
                        publisherDTO.getAddressDTO().setRegionDTO(RegionMapping.toDTO(regionRepository.getById(publisher.getAddress().getRegion_id())));
                        publisherDTO.getAddressDTO().getDistrictDTO().setRegionDTO(RegionMapping.toDTO(regionRepository.getById(publisher.getAddress().getRegion_id())));
                        return publisherDTO;
                    }
            ).collect(Collectors.toList());

            return new ResponseDTO<>(true,0,"ACCESS",ResultPublisherDTO,null);
        }
        return new ResponseDTO<>(false,-1,"DB error",null,null);
    }

    public ResponseDTO<PublisherDTO> getOneById(Integer id)
    {
        if(id==null) return new ResponseDTO<>(false,-2,"Id is null",null,null);
        Publisher publisher = publisherRepository.getById(id);
            if(publisher.getId() != null)
        {
            PublisherDTO publisherDTO = PublisherMapping.toDTO(publisher);

            publisherDTO.setAddressDTO(AddressMapping.toDTO(publisher.getAddress()));
            publisherDTO.getAddressDTO().setDistrictDTO(DistrictMapping.toDTO(districtRepository.getById(publisher.getAddress().getDistrict_id())));
            publisherDTO.getAddressDTO().setRegionDTO(RegionMapping.toDTO(regionRepository.getById(publisher.getAddress().getRegion_id())));
            return new ResponseDTO<>(true,0,"ACCESS",publisherDTO,null);
        }
        return new ResponseDTO<>(false,-3,"NOT FOUND",null,null);

    }

    public ResponseDTO<PublisherDTO> addPublisher(PublisherDTO publisherDTO) {
        if(publisherDTO.getId()!=null)
            if(regionRepository.existsById(publisherDTO.getId())) return new ResponseDTO<>(false ,-3,"Id is have",null,null);
        List<String> list = Valid.chechPublisherAll(publisherDTO);
        if(list.size()>0) return new ResponseDTO<>(false,-1,"Error",null,list);


        Publisher publisher = PublisherMapping.toEntity(publisherDTO);
        publisherRepository.save(publisher);
        publisherDTO.setAddressDTO(AddressMapping.toDTO(publisher.getAddress()));
        publisherDTO.getAddressDTO().setDistrictDTO(DistrictMapping.toDTO(districtRepository.getById(publisher.getAddress().getDistrict_id())));
        publisherDTO.getAddressDTO().setRegionDTO(RegionMapping.toDTO(regionRepository.getById(publisher.getAddress().getRegion_id())));
        publisherDTO.setId(publisher.getId());
        return new ResponseDTO<>(true,0,"ACCESS",publisherDTO,null);
    }
    public ResponseDTO<PublisherDTO> update(PublisherDTO publisherDTO) {
      try {


          if (publisherDTO.getId() == null) return new ResponseDTO<>(false, -1, "Id is null", null,null);
          if (!publisherRepository.existsById(publisherDTO.getId()))
              return new ResponseDTO<>(false, -3, "Id not have", null, null);
          List<String> list = Valid.chechPublisherAll(publisherDTO);
          if(list.size()>0) return new ResponseDTO<>(false,-1,"Error",null,list);
          Publisher publisher = PublisherMapping.toEntity(publisherDTO);
          publisherRepository.save(publisher);

          publisherDTO.setAddressDTO(AddressMapping.toDTO(publisher.getAddress()));
          publisherDTO.getAddressDTO().setDistrictDTO(DistrictMapping.toDTO(districtRepository.getById(publisher.getAddress().getDistrict_id())));
          publisherDTO.getAddressDTO().setRegionDTO(RegionMapping.toDTO(regionRepository.getById(publisher.getAddress().getRegion_id())));
          publisherDTO.setId(publisher.getId());
          return new ResponseDTO<>(true, 0, "ACCESS", publisherDTO, null);
      }catch (Exception e )
      {
          e.printStackTrace();
          return new ResponseDTO<>(false,-1,e.getMessage(),null,null);
      }

}
    }
