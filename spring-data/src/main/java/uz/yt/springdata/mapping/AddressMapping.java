package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.Address;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DTO.AddressDTO;
import uz.yt.springdata.DTO.DistrictDTO;

public class AddressMapping {
    public static AddressDTO toDTO(Address address)
    {
        return new AddressDTO(address.getId(),
                address.getRegion_id(),
                address.getDistrict_id(),
                address.getStreet(),
                address.getHomenumber(),
                address.getOrient());
    }

    public static Address toEntity(AddressDTO addressDTO)
    {
        return new Address(addressDTO.getId(),
                addressDTO.getRegion_id(),
                addressDTO.getDistrict_id(),
                addressDTO.getStreet(),
                addressDTO.getHomenumber(),
                addressDTO.getOrient());
    }

    public static Address setEntity(Address address,AddressDTO addressDTO)
    {
        address.setId(addressDTO.getId());
        address.setRegion_id(addressDTO.getRegion_id());
        address.setDistrict_id(addressDTO.getDistrict_id());
        address.setStreet(addressDTO.getStreet());
        address.setHomenumber(addressDTO.getHomenumber());
        address.setOrient(addressDTO.getOrient());
        return address;
    }
    public static AddressDTO setDTO(Address address,AddressDTO addressDTO)
    {
        addressDTO.setId(address.getId());
        addressDTO.setRegion_id(address.getRegion_id());
        addressDTO.setDistrict_id(address.getDistrict_id());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHomenumber(address.getHomenumber());
        addressDTO.setOrient(address.getOrient());
        return addressDTO;
    }
}
