package uz.yt.springdata.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DTO.AddressDTO;
import uz.yt.springdata.DTO.DistrictDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.service.AddressService;
import uz.yt.springdata.service.DistrictService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("address")
public class AddressResource {
    private final AddressService addressService;
    //sout all info in region
    @GetMapping("get-all")
    public ResponseDTO<List<AddressDTO>> getAll()
    {
        return addressService.findAll();
    }


    //sout region by id
    @GetMapping("get-by-id")
    public ResponseDTO<AddressDTO>getAll(@RequestParam Integer id) {return addressService.findById(id);}

    //add info in region
    @PostMapping("add")
    public ResponseDTO<AddressDTO> add(@RequestBody AddressDTO addressDTO) {
        return addressService.addNew(addressDTO);
    }
    //update info region
    @PutMapping ("update")
    public ResponseDTO<AddressDTO> update(@RequestBody AddressDTO addressDTO) {
        return addressService.update(addressDTO);}

}
