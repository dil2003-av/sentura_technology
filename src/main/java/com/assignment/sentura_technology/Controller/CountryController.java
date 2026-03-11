package com.assignment.sentura_technology.Controller;


import com.assignment.sentura_technology.dto.CountryDTO;
import com.assignment.sentura_technology.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/search")
    public List<CountryDTO> search(@RequestParam String name) {
        return countryService.search(name);
    }

}