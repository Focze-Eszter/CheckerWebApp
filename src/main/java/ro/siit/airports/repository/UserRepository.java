package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndPassword(String name, String password);

    @Query("select u from User u where u.email =?1")
    User findUserByEmail(String email);


   //User findByEmail(String email);

    Optional<User> findByEmail(String email);

   //List<User> findByNameLike(String name);
}

