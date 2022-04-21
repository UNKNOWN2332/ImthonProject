package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublisherDTO {
    private Integer id;
    private String name;
    private Integer adres_id;
    private AddressDTO addressDTO;

    public PublisherDTO(Integer id, String name, AddressDTO addressDTO) {
        this.id = id;
        this.name = name;
        this.addressDTO = addressDTO;
    }

    public PublisherDTO(Integer id, String name, Integer adres_id) {
        this.id = id;
        this.name = name;
        this.adres_id = adres_id;
    }
}
