package ro.siit.airports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.repository.AirlineRepository;
import java.util.Optional;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public Page<Airline> listAll(int pageNumber, String sortedField, String sortedDirection, String keyword) {
        Sort sort = Sort.by(sortedField);
        sort = sortedDirection.equals("ascending") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 14, sort);
        if (keyword != null) {
            return airlineRepository.findAll(keyword, pageable);
        }
        return airlineRepository.findAll(pageable);
    }

    public void save(Airline airline) {
        airlineRepository.save(airline);
    }

    public Airline getAirlineById(Long id) {
        System.out.println("getAirlineById");
        Optional<Airline> airline = airlineRepository.findById(id);

        if (airline.isPresent()) {
            return airline.get();
        } else {
            System.out.println("No airline record exist for given id");
        }
        return null;
    }

    public Airline createOrUpdateAirline(Airline airlineEntity) {
        System.out.println("createOrUpdateAirport");

        if (airlineEntity.getId() == 0) {
            airlineEntity = airlineRepository.save(airlineEntity);

            return airlineEntity;
        } else {
            // update existing entry
            Optional<Airline> airline = airlineRepository.findById(airlineEntity.getId());

            if (airline.isPresent()) {
                Airline newEntity = airline.get();
                newEntity.setName(airlineEntity.getName());
                newEntity.setCountry(airlineEntity.getCountry());
                newEntity.setIcao(airlineEntity.getIcao());
                newEntity.setIata(airlineEntity.getIata());
                newEntity = airlineRepository.save(newEntity);

                return newEntity;
            } else {
                airlineEntity = airlineRepository.save(airlineEntity);

                return airlineEntity;
            }
        }
    }

    public void deleteAirlineById(Long id) {

        System.out.println("deleteAirlineById");

        Optional<Airline> airline = airlineRepository.findById(id);

        if(airline.isPresent())
        {
            airlineRepository.deleteById(id);
        } else {
            System.err.println("No airline record exist for given id");
        }
    }
}
