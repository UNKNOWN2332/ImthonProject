package uz.yt.springdata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.yt.springdata.DAO.Address;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublisherDTO {
    private Integer id;
    private String name;
    private AddressDTO addressDTO;

}
