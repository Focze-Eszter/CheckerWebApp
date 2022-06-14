package ro.siit.airports.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.model.Search;

import java.util.List;

public interface AirportService {

    List<Airport> findFilteredAirports(Search search);


}
