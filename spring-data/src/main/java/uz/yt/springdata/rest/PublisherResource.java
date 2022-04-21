package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DTO.PublisherDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.service.PublisherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("publisher")
public class PublisherResource {
    private final PublisherService publisherServic;
    @GetMapping("getall")
    public ResponseDTO<List<PublisherDTO>> getall()
    {
        return publisherServic.getall();
    }
    @GetMapping("getbyid")
    public ResponseDTO<PublisherDTO> getall(@RequestParam Integer id)
    {
        return publisherServic.getoneByid(id);
    }
    @PostMapping("add")
    public ResponseDTO<PublisherDTO> add(@RequestBody PublisherDTO publisherDTO)
    {
        return publisherServic.addpublisher(publisherDTO);
    }
        @PutMapping("update")
    public ResponseDTO<PublisherDTO> update (@RequestBody PublisherDTO publisherDTO)
    {
        return publisherServic.update(publisherDTO);
    }
}
