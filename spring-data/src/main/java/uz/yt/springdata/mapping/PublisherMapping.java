package uz.yt.springdata.mapping;

import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DAO.Publisher;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.PublisherDTO;

public class PublisherMapping {
    public static PublisherDTO toDTO(Publisher publisher)
    {
        return new PublisherDTO(publisher.getId(),
                publisher.getName(),
                publisher.getAdres_id());
    }

    public static Publisher toEntity(PublisherDTO publisherDTO)
    {
        return new Publisher(publisherDTO.getId(),
                publisherDTO.getName(),
                publisherDTO.getAdres_id());

    }

    public static Publisher setEntity(Publisher publisher,PublisherDTO publisherDTO)
    {
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setAdres_id(publisherDTO.getAdres_id());
        return publisher;
    }
    public static PublisherDTO setDTO(Publisher publisher,PublisherDTO publisherDTO)
    {
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setAdres_id(publisher.getAdres_id());
        return publisherDTO;
    }
}
