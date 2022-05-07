package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.DTO.BookDTO;
import uz.yt.springdata.DTO.ResponseDTO;
import uz.yt.springdata.service.BookService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookResource {
    private final BookService bookService;

    @GetMapping("/get-all")
    public ResponseDTO<Page<BookDTO>> getAll(@RequestParam Integer size, @RequestParam Integer page)
    {
        return bookService.getAllBooks(size,page);
    }
    @PostMapping("/add")
    public ResponseDTO<BookDTO> add(@RequestBody BookDTO bookDTO)
    {
        return bookService.addNew(bookDTO);
    }
    @PutMapping("/update")
    public ResponseDTO<BookDTO> update_book(@RequestBody BookDTO bookDTO)
    {
        return bookService.update(bookDTO);
    }
    @DeleteMapping("/delete")
    public ResponseDTO<BookDTO> delete(@RequestParam Integer id)
    {
        return bookService.delete(id);
    }

    @GetMapping("get-by-cost")
    public ResponseDTO<Page<BookDTO>> getByCost(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5    ") Integer size, @RequestParam Double cost)
    {
        return bookService.getByCost(page,size,cost);
    }
}
