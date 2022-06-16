package ro.siit.airports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.model.Search;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.service.AirportService;
import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;


    @Override
    public List<Airport> findFilteredAirports(final Search search) {
        List<Airport> list;
        if (search.hasCountry() && search.hasCity()) {
            list = airportRepository.findByCountryAndCity(search.getCountry(), search.getCity());
        } else if (search.hasCountry()) {
            list = airportRepository.findByCountry(search.getCountry());
        } else if (search.hasCity()) {
            list = airportRepository.findByCity(search.getCity());
        } else {
            list = (List<Airport>) airportRepository.findAll();

        }

        return list;
    }

    public Page<Airport> listAll(int pageNumber, String sortedField, String sortedDirection, String keyword) {
        Sort sort = Sort.by(sortedField);
        sort = sortedDirection.equals("ascending") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 14, sort);
        if (keyword != null) {
            return airportRepository.findAll(keyword, pageable);
        }
        return airportRepository.findAll(pageable);
    }

    public void save(Airport airport) {
        airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {
        System.out.println("getAirportById");
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            return airport.get();
        } else {
            System.out.println("No airport record exist for given id");
        }
        return null;
    }

    public Airport createOrUpdateAirport(Airport airportEntity) {
        System.out.println("createOrUpdateAirport");

        if (airportEntity.getId() == 0) {
            airportEntity = airportRepository.save(airportEntity);

            return airportEntity;
        } else {
            // update existing entry
            Optional<Airport> airport = airportRepository.findById(airportEntity.getId());

            if (airport.isPresent()) {
                Airport newEntity = airport.get();
                newEntity.setName(airportEntity.getName());
                newEntity.setCountry(airportEntity.getCountry());
                newEntity.setCity(airportEntity.getCity());
                newEntity.setIata(airportEntity.getIata());

                newEntity = airportRepository.save(newEntity);

                return newEntity;
            } else {
                airportEntity = airportRepository.save(airportEntity);

                return airportEntity;
            }
        }
    }

    public void deleteAirportById(Long id) {
        System.out.println("deleteAirportById");

        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            airportRepository.deleteById(id);
        } else {
            System.err.println("No airport record exist for given id");
        }
    }
}




