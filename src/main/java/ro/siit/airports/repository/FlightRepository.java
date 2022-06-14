package ro.siit.airports.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Flight;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightNo(String flightNo);


    @Query("select f from Flight f inner join f.departureAirport a " +
            "where f.departure >= :departureDate " +
            "and a.city = :departureCity")
    List<Flight> findFlightsByCustomRules(@Param("departureDate") LocalDateTime departureDate,
                                          @Param("departureCity") String departureCity);

    @Query("select f from Flight f inner join f.departureAirport a " +
            "where f.departure >= current_timestamp " +
            "and a.id = :id")
    Page<Flight> findFlightsByMyRules(@Param("id") Long airportId, Pageable pageable);
}

