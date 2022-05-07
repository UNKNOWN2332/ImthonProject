package uz.yt.springdata.Validation;

import lombok.RequiredArgsConstructor;
import uz.yt.springdata.DAO.District;
import uz.yt.springdata.DAO.Users;
import uz.yt.springdata.DTO.*;
import uz.yt.springdata.Repository.RegionRepository;
import uz.yt.springdata.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class Valid {
    private static UserRepository userRepository;
    private static RegionRepository regionRepository;

    public static List<String> checkAllUsers(UserDTO userDTO) {
        ArrayList<String> errors = new ArrayList<>();
        Optional<Users> userid = userRepository.findById(userDTO.getId());
        Optional<Users> userusername = userRepository.findByUsername(userDTO.getGmail());
        Optional<Users> userphonenumber = userRepository.findByPhonenumber(userDTO.getPhonenumber());
        if(!userid.isPresent()) errors.add("Id is have");
        if(!userusername.isPresent()) errors.add("Gmail is have");
        if(!userphonenumber.isPresent()) errors.add("Phone numbers is have");
        if(userDTO.getName()==null) errors.add("Name is null");
        if(userDTO.getSurname()==null) errors.add("Surname is null");
        if(userDTO.getPassword()==null) errors.add("Password is null");
        if(userDTO.getPhonenumber()==null) errors.add("Phone number is null");
        if(userDTO.getGmail()==null) errors.add("Gmail is null");
        Pattern pattern = Pattern.compile("\\+998[[3][7-9]][0-9]-[0-9]{3}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(userDTO.getPhonenumber());
        Pattern pattern2 = Pattern.compile("\\+998[[3][7-9]][0-9][0-9]{3}[0-9]{2}[0-9]{2}");
        Matcher matcher2 = pattern2.matcher(userDTO.getPhonenumber());
        Pattern pattern3 = Pattern.compile("\\+998[[3][7-9]][0-9] [0-9]{3} [0-9]{2} [0-9]{2}");
        Matcher matcher3 = pattern3.matcher(userDTO.getPhonenumber());
        if(!matcher.find() || !matcher2.find() || !matcher3.find())
            errors.add("Number format error");
        return errors;
    }
    public static List<String> checkRegionAll(RegionDTO regionDTO)
    {
        ArrayList<String> errors = new ArrayList<>();

        if (regionDTO.getNameuz()==null) errors.add("Name Uzbek Language is null");
        if (regionDTO.getNameru()==null) errors.add("Name Russian Language is null");
        if (regionDTO.getNameeng()==null) errors.add("Name English Language is null");
        return errors;
    }
    public static List<String> chechDistrictAll(DistrictDTO districtDTO)
    {
        List<String> errors = new ArrayList<>();
        if(districtDTO.getName_uz()==null) errors.add("Name Uzbek Language is null");
        if (districtDTO.getName_ru()==null) errors.add("Name Russian Language is null");
        if (districtDTO.getName_eng()==null) errors.add("Name English Language is null");
        return errors;
    }
    public static List<String> chechAddressAll(AddressDTO addressDTO)
    {
        List<String> errors = new ArrayList<>();
        if(addressDTO.getStreet()==null) errors.add("Street is null");
        if (addressDTO.getHomenumber()==null) errors.add("Home number is null");
        if (addressDTO.getOrient()==null) errors.add("Orient is null");
        return errors;
    }
    public static List<String> chechPublisherAll(PublisherDTO publisherDTO)
    {
        List<String> errors = new ArrayList<>();
        if(publisherDTO.getName()==null) errors.add("Name is null");
        return errors;
    }

    public static List<String> chechAuthorAll(AuthorDTO authorDTO)
    {
        List<String> errors = new ArrayList<>();
        if(authorDTO.getName()==null) errors.add("Name is null");
        if(authorDTO.getSurname()==null) errors.add("surname is null");
        if(authorDTO.getBirthdate()==null) errors.add("birth date is null");
        return errors;
    }

    public static List<String> chechBookAll(BookDTO bookDTO)
    {
        List<String> errors = new ArrayList<>();
        if(bookDTO.getNameuz()==null) errors.add("Name Uzbek Language is null");
        if(bookDTO.getNameru()==null) errors.add("Name Russian Language is null");
        if(bookDTO.getPublished_date()==null) errors.add("published date is null");
        if(bookDTO.getPage_count()==null) errors.add("page count is null");
        if(bookDTO.getGenre()==null) errors.add("genre  is null");
        return errors;
    }
}
