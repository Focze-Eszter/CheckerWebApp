package ro.siit.airports.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Airport;

import java.util.List;



    @Repository
    public interface AirlineRepository extends PagingAndSortingRepository<Airline, Long> {

        @Query("SELECT a FROM Airline a WHERE " + "CONCAT(' ', a.name,' ', a.country,' ', a.icao, ' ')" + "LIKE %?1%")
        public Page<Airline> findAll(String keyword, Pageable pageable);

        List<Airline> findByNameAndCountry(String name, String country);

        //List<Airline> findAllByNameContainingOrCountryOrIcao(String name, String country, String icao, Pageable pageable);

        List<Airline> findByCountry(String country);

        List<Airline> findByIcao(String icao);


    }

