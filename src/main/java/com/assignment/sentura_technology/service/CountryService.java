package com.assignment.sentura_technology.service;

import com.assignment.sentura_technology.dto.CountryDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CountryService {

    private List<CountryDTO> cache = new ArrayList<>();
    private long lastFetchTime = 0;

    public List<CountryDTO> getCountries() {

        long currentTime = System.currentTimeMillis();

        if (cache.isEmpty() || currentTime - lastFetchTime > 600000) {

            String url = "https://restcountries.com/v3.1/all?fields=name,capital,region,population,flags";

            RestTemplate restTemplate = new RestTemplate();

            List<Map<String, Object>> response =
                    Arrays.asList(restTemplate.getForObject(url, Map[].class));

            List<CountryDTO> countries = new ArrayList<>();

            for (Map<String, Object> c : response) {

                CountryDTO dto = new CountryDTO();

                Map name = (Map) c.get("name");
                dto.setName(name.get("common").toString());

                List capital = (List) c.get("capital");
                if (capital != null && !capital.isEmpty()) {
                    dto.setCapital(capital.get(0).toString());
                } else {
                    dto.setCapital("N/A");
                }

                dto.setRegion(c.get("region").toString());

                dto.setPopulation(Long.parseLong(c.get("population").toString()));

                Map flags = (Map) c.get("flags");
                dto.setFlag(flags.get("png").toString());

                countries.add(dto);
            }

            cache = countries;
            lastFetchTime = currentTime;
        }

        return cache;
    }

    public List<CountryDTO> search(String keyword) {

        List<CountryDTO> countries = getCountries();

        List<CountryDTO> result = new ArrayList<>();

        for (CountryDTO c : countries) {

            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }

        }

        return result;
    }
}