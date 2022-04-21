package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Address;
import uz.yt.springdata.DAO.Publisher;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.*;
import uz.yt.springdata.Repository.AddressRepository;
import uz.yt.springdata.Repository.DistrictRepository;
import uz.yt.springdata.Repository.PublisherRepository;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.mapping.AddressMapping;
import uz.yt.springdata.mapping.DistrictMapping;
import uz.yt.springdata.mapping.PublisherMapping;
import uz.yt.springdata.mapping.RegionMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public ResponseDTO<List<PublisherDTO>> getall() {
        List<Publisher> publishers = publisherRepository.findAll();
        if(!publishers.isEmpty()) {
            List<PublisherDTO> ResultPublisherDTO = new ArrayList<>();

            for (Publisher publisher : publishers) {
                PublisherDTO publisherDTO = PublisherMapping.toDTO(publisher);

                AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisher.getAdres_id()));
                addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));

                DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
                districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
                addressDTO.setDistrictDTO(districtDTO);

                publisherDTO.setAddressDTO(addressDTO);
                ResultPublisherDTO.add(publisherDTO);

            }
            return new ResponseDTO<>(true,0,"ACCESS",ResultPublisherDTO);
        }
        return new ResponseDTO<>(false,-1,"ERROR",null);
    }

    public ResponseDTO<PublisherDTO> getoneByid(Integer id)
    {
        if(id==null) return new ResponseDTO<>(false,-2,"Id is null",null);
        Publisher publisher = publisherRepository.getById(id);
            if(publisher!=null && publisher.getId()!=null)
        {
            PublisherDTO publisherDTO = PublisherMapping.toDTO(publisher);

            AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisher.getAdres_id()));
            addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));

            DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
            addressDTO.setDistrictDTO(districtDTO);

            publisherDTO.setAddressDTO(addressDTO);
            return new ResponseDTO<>(true,0,"ACCESS",publisherDTO);
        }
        return new ResponseDTO<>(false,-3,"NOT FOUND",null);

    }

    public ResponseDTO<PublisherDTO> addpublisher(PublisherDTO publisherDTO) {
        if(publisherDTO.getId()!=null)
            if(regionRepository.existsById(publisherDTO.getId())) return new ResponseDTO<>(false ,-3,"Id is have",null);
        Publisher publisher = PublisherMapping.toEntity(publisherDTO);
        publisherRepository.save(publisher);


        AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisher.getAdres_id()));
        addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));

        DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
        districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
        addressDTO.setDistrictDTO(districtDTO);

        publisherDTO.setAddressDTO(addressDTO);
        publisherDTO.setId(publisher.getId());
        return new ResponseDTO<>(true,0,"ACCESS",publisherDTO);
    }
    public ResponseDTO<PublisherDTO> update(PublisherDTO publisherDTO) {
        if(publisherDTO.getId()==null) return new ResponseDTO<>(false,-1,"Id is null",null);
        if(!publisherRepository.existsById(publisherDTO.getId())) return new ResponseDTO<>(false ,-3,"Id not have",null);
        Publisher publisher = PublisherMapping.toEntity(publisherDTO);
        publisherRepository.save(publisher);
        AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisher.getAdres_id()));
        addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));

        DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
        districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
        addressDTO.setDistrictDTO(districtDTO);

        publisherDTO.setAddressDTO(addressDTO);
        publisherDTO.setId(publisher.getId());
        return new ResponseDTO<>(true,0,"ACCESS",publisherDTO);

}
    }
