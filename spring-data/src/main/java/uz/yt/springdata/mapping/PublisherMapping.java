package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DAO.Publisher;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.PublisherDTO;

public class PublisherMapping {
    public static PublisherDTO toDTO(Publisher publisher)
    {
        return publisher==null ? null :new PublisherDTO(publisher.getId(),
                publisher.getName(),
                AddressMapping.toDTO(publisher.getAddress()));
    }

    public static Publisher toEntity(PublisherDTO publisherDTO)
    {
        return publisherDTO==null ? null : new Publisher(publisherDTO.getId(),
                publisherDTO.getName(),
                AddressMapping.toEntity(publisherDTO.getAddressDTO()));

    }

    public static Publisher setEntity(Publisher publisher,PublisherDTO publisherDTO)
    {
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setAddress(AddressMapping.toEntity(publisherDTO.getAddressDTO()));
        return publisherDTO ==null ? null :publisher;
    }
    public static PublisherDTO setDTO(Publisher publisher,PublisherDTO publisherDTO)
    {
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setAddressDTO(AddressMapping.toDTO(publisher.getAddress()));
        return publisher==null ? null :publisherDTO;
    }
}
