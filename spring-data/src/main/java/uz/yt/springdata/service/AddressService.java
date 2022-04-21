package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.DAO.Address;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DTO.AddressDTO;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.RegionDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.Repository.AddressRepository;
import uz.yt.springdata.Repository.DistrictRepository;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.mapping.AddressMapping;
import uz.yt.springdata.mapping.DistrictMapping;
import uz.yt.springdata.mapping.RegionMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    public ResponseDTO<List<AddressDTO>> findall()
    {
        List<Address> addresses = addressRepository.findAll();
        if(!addresses.isEmpty())
        {
            List<AddressDTO> resultAddressDTO = new ArrayList<>();
            for (Address address : addresses) {
                AddressDTO addressDTO = AddressMapping.toDTO(address);

                DistrictDTO districtDTO = DistrictMapping.toDTO(districtRepository.getById(address.getDistrict_id()));
                districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(address.getRegion_id())));

                addressDTO.setDistrictDTO(DistrictMapping.toDTO(districtRepository.getById(districtDTO.getId())));
                addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
                resultAddressDTO.add(addressDTO);
            }
            return new ResponseDTO<>(true,0,"ACCESS",resultAddressDTO);
        }
        return new ResponseDTO<>(false,-1,"ERROR",null);
    }

    public ResponseDTO<AddressDTO> findbyid(Integer id)
    {
        Address address = addressRepository.getById(id);
        if(address.getId()!=null && address!=null)
        {
            AddressDTO addressDTO = AddressMapping.toDTO(address);
            DistrictDTO districtDTO = DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(address.getRegion_id())));
            RegionDTO regionDTO = RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id()));
            addressDTO.setDistrictDTO(districtDTO);
            addressDTO.setRegionDTO(regionDTO);
            return new ResponseDTO<>(true,0,"ACCESS",addressDTO);}

        return new ResponseDTO<>(false,-1,"Id is null",null);

    }
    public ResponseDTO<AddressDTO> addnew(AddressDTO addressDTO)
    {
        try {


            if (addressDTO.getId() != null && districtRepository.existsById(addressDTO.getId()))
                return new ResponseDTO<>(false, -1, "ID is have", null);
            Address address = AddressMapping.toEntity(addressDTO);
            addressRepository.save(address);
            DistrictDTO districtDTO = DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(address.getRegion_id())));
            RegionDTO regionDTO = RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id()));
            addressDTO.setDistrictDTO(districtDTO);
            addressDTO.setRegionDTO(regionDTO);

            addressDTO.setId(address.getId());
            return new ResponseDTO<>(true,0,"ACCESS",addressDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null);
        }
    }
    public ResponseDTO<AddressDTO> update(AddressDTO addressDTO)
    {
        try{
            if (addressDTO.getId()==null) return new ResponseDTO<>(false,-3,"Id is null",null);
            if (!districtRepository.existsById(addressDTO.getId())) return new ResponseDTO<>(false,-4,"id is no",null);
            Address address = AddressMapping.toEntity(addressDTO);
            addressRepository.save(address);
            DistrictDTO districtDTO = DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(address.getRegion_id())));
            RegionDTO regionDTO = RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id()));
            addressDTO.setDistrictDTO(districtDTO);
            addressDTO.setRegionDTO(regionDTO);

            addressDTO.setId(address.getId());

            return new ResponseDTO<>(true,0,"ACCESS",addressDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false,-1,e.getMessage(),null);
        }
    }
}
