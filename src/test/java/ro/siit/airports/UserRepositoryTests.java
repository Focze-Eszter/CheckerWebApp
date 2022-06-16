package ro.siit.airports;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
            user.setEmail("AnaZana@yahoo.com");
            user.setPassword("parolaLuPeste");
            user.setName("Raviola");

            User savedUser = repo.save(user);
            User existUser = entityManager.find(User.class, savedUser.getId());

            assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }
}
