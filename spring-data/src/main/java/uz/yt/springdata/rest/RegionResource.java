package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DAO.Region;
import uz.yt.springdata.DTO.RegionDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.DTO.UserDTO;
import uz.yt.springdata.service.RegionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("region")
public class RegionResource {
    private final RegionService regionService;
    //sout all info in region
    @GetMapping("get-all")
    public ResponseDTO<List<RegionDTO>> getAll()
    {
        return regionService.getAll();
    }


    //sout region by id
    @GetMapping("get-by-id")
    public ResponseDTO<RegionDTO>getAll(@RequestParam Integer id) {return regionService.getOneById(id);}

    //add info in region
    @PostMapping("add")
    public ResponseDTO<RegionDTO> add(@RequestBody RegionDTO regionDTO)
    {
        return regionService.addRegion(regionDTO);
    }
    //update info region
    @PutMapping ("update")
    public ResponseDTO<RegionDTO> update(@RequestBody RegionDTO regionDTO)
    {
        return regionService.update(regionDTO);
    }


}
