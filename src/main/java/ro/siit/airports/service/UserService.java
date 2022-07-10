package ro.siit.airports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.AuthenticationProvider;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Provider;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(final String username) {

        final Optional<User> optionalUser = userRepository.findByEmail(username);
        final User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(username));
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.isEnabled()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                grantedAuthorities);
    }

    public User loadUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public void createNewUserAfterOAuth2LoginSuccess(String email, String name, AuthenticationProvider provider) {

        User user = new User();

        user.setEmail(email);
        user.setName(name);
        user.setAuthProvider(provider);
        user.setEnabled(true);

        userRepository.save(user);
    }

    public void updateUserAfterOAuth2LoginSuccess(User user, String name, AuthenticationProvider provider) {

        user.setName(name);
        user.setAuthProvider(provider);
        user.setEnabled(true);

        userRepository.save(user);
    }
        /*public void createUser(User user){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setName(user.getName());
            user.setEmail(user.getEmail());
            user.setRole("User");
            userRepository.save(user);
        }*/

    /*public boolean isUserPresent(String email) {
        User u =userRepository.findByEmail(email);
        if(u != null) {
            return true;
        }
        return false;
    }*/

    }


