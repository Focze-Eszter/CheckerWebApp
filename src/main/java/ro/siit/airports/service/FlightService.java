package ro.siit.airports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Page<Flight> listAll(int pageNumber, String sortedField, String sortedDirection) {
        Sort sort = Sort.by(sortedField);
        sort = sortedDirection.equals("ascending") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return flightRepository.findAll(pageable);
    }

    public Flight getFlightById(Long id) {
        System.out.println("getFlightById");
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            return flight.get();
        } else {
            System.out.println("No flight record exist for given id");
        }
        return null;
    }

    public Flight createOrUpdateFlight(Flight flightEntity) {
        System.out.println("createOrUpdateFlight");

        if (flightEntity.getId() == 0) {
            flightEntity = flightRepository.save(flightEntity);

            return flightEntity;
        } else {

            Optional<Flight> flight =flightRepository.findById(flightEntity.getId());

            if (flight.isPresent()) {
                Flight newEntity = flight.get();
                newEntity.setFlightNo(flightEntity.getFlightNo());
                newEntity.setDeparture(flightEntity.getDeparture());
                newEntity.setDepartureAirport(flightEntity.getDepartureAirport());
                newEntity.setArrival(flightEntity.getArrival());
                newEntity.setArrivalAirport(flightEntity.getArrivalAirport());
                newEntity.setAirline(flightEntity.getAirline());
                newEntity =flightRepository.save(newEntity);

                return newEntity;
            } else {
                flightEntity = flightRepository.save(flightEntity);

                return flightEntity;
            }
        }
    }

    public void deleteFlightById(Long id) {
        System.out.println("deleteFlightById");

        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            flightRepository.deleteById(id);
        } else {
            System.err.println("No flight record exist for given id");
        }
    }


    public Page<Flight> getFlightsById(int pageNumber, String sortedField, String sortedDirection, Long airportId) {
        Sort sort = Sort.by(sortedField);
        sort = sortedDirection.equals("ascending") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
        return flightRepository.findFlightsByMyRules(airportId, pageable);
    }

    public Flight getNext20FlightsBasedOnTheLocation() {

        OffsetDateTime now = OffsetDateTime.now();
        System.out.println(now);
        ZonedDateTime zonedDateTime = now.atZoneSameInstant(ZoneId.systemDefault());
        //atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        return null;
    }
}
