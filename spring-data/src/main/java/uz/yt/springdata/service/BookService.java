package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.yt.springdata.DAO.Author;
import uz.yt.springdata.DAO.Book;
import uz.yt.springdata.DAO.Publisher;
import uz.yt.springdata.DTO.*;
import uz.yt.springdata.Repository.*;
import uz.yt.springdata.mapping.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping("book")
public class BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public ResponseDTO<Page<BookDTO>> getAllBooks(Integer size, Integer page)
    {
        PageRequest pageRequest = PageRequest.of(size,page);
        Page<Book> books = bookRepository.findAll(pageRequest);
        if(!books.isEmpty())
        {
            List<BookDTO> bookDTOList = new ArrayList<>();
            for (Book book : books) {

                PublisherDTO publisherDTO = PublisherMapping.toDTO(publisherRepository.getById(book.getPublisher_id()));

                AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisherDTO.getAdres_id()));
                addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));
                DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
                districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
                addressDTO.setDistrictDTO(districtDTO);
                publisherDTO.setAddressDTO(addressDTO);

                AuthorDTO authorDTO = AuthorMapping.toDTO(authorRepository.getById(book.getAuthor_id()));


                BookDTO bookDTO = BookMapping.toDTO(bookRepository.getById(book.getId()));
                bookDTO.setAuthorDTO(authorDTO);
                bookDTO.setPublisherDTO(publisherDTO);

                bookDTOList.add(bookDTO);
            }
            Page<BookDTO> result = new PageImpl<>(bookDTOList,books.getPageable(),books.getTotalElements());
            return new ResponseDTO<>(true,0,"ACCESS",result);
        }
        return new ResponseDTO<>(false,-1,"NOT FOUND",null);
    }

    public ResponseDTO<BookDTO> addnew(BookDTO bookDTO)
    {
        try {
            Book book = BookMapping.toEntity(bookDTO);
            bookRepository.save(book);

            PublisherDTO publisherDTO = PublisherMapping.toDTO(publisherRepository.getById(book.getPublisher_id()));

            AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisherDTO.getAdres_id()));
            addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));
            DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
            addressDTO.setDistrictDTO(districtDTO);
            publisherDTO.setAddressDTO(addressDTO);

            AuthorDTO authorDTO = AuthorMapping.toDTO(authorRepository.getById(book.getAuthor_id()));


            bookDTO = BookMapping.toDTO(bookRepository.getById(book.getId()));
            bookDTO.setAuthorDTO(authorDTO);
            bookDTO.setPublisherDTO(publisherDTO);
            return new ResponseDTO<>(true, 0, "OK", bookDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR",null);
        }

    }


    public ResponseDTO<BookDTO> update(BookDTO bookDTO)
    {
        try {
            if (bookDTO.getId() == null) return new ResponseDTO<>(false, -1, "ID is null", bookDTO);
            Optional<Book> _book = bookRepository.findById(bookDTO.getId());
            if (!_book.isPresent()) return new ResponseDTO<>(false, -2, "Id mos table da yoq ekan", bookDTO);
            Book book = _book.get();
            BookMapping.setEntity(book, bookDTO);
            bookRepository.save(book);
            PublisherDTO publisherDTO = PublisherMapping.toDTO(publisherRepository.getById(book.getPublisher_id()));

            AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisherDTO.getAdres_id()));
            addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));
            DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
            districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
            addressDTO.setDistrictDTO(districtDTO);
            publisherDTO.setAddressDTO(addressDTO);

            AuthorDTO authorDTO = AuthorMapping.toDTO(authorRepository.getById(book.getAuthor_id()));


            bookDTO = BookMapping.toDTO(bookRepository.getById(book.getId()));
            bookDTO.setAuthorDTO(authorDTO);
            bookDTO.setPublisherDTO(publisherDTO);
            return new ResponseDTO<>(true, 0, "OK", bookDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseDTO<>(false ,-3,"Not change doesn save ",null);
        }
    }

    public ResponseDTO<BookDTO> delete (Integer id)
    {
        Optional<Book> _book = bookRepository.findById(id);
        if(!_book.isPresent()) return new ResponseDTO<>(false, -4, "Id not found", null);
        Book book = _book.get();
        book.setIsactive(1);
        bookRepository.save(book);
        PublisherDTO publisherDTO = PublisherMapping.toDTO(publisherRepository.getById(book.getPublisher_id()));

        AddressDTO addressDTO = AddressMapping.toDTO(addressRepository.getById(publisherDTO.getAdres_id()));
        addressDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(addressDTO.getRegion_id())));
        DistrictDTO districtDTO=DistrictMapping.toDTO(districtRepository.getById(addressDTO.getDistrict_id()));
        districtDTO.setRegionDTO(RegionMapping.toDTO(regionRepository.getById(districtDTO.getRegion_id())));
        addressDTO.setDistrictDTO(districtDTO);
        publisherDTO.setAddressDTO(addressDTO);

        AuthorDTO authorDTO = AuthorMapping.toDTO(authorRepository.getById(book.getAuthor_id()));


        BookDTO bookDTO = BookMapping.toDTO(bookRepository.getById(id));
        bookDTO.setAuthorDTO(authorDTO);
        bookDTO.setPublisherDTO(publisherDTO);
        return new ResponseDTO<>(true, 0, "ACCESS",bookDTO );

    }
}
