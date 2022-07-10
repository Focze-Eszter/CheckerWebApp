package ro.siit.airports.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ro.siit.airports.domain.AuthenticationProvider;
import ro.siit.airports.domain.User;
import ro.siit.airports.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //Spring will inject an instance of this class into the Spring Security
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String clientName = oAuth2User.getClientName();
        String email = oAuth2User.getEmail();
        User user = userService.loadUserByEmail(email);
        String name = oAuth2User.getName();
        if (user == null) {
            userService.createNewUserAfterOAuth2LoginSuccess(email, name, AuthenticationProvider.GOOGLE); //register as a new user
            System.out.println("New user");
        } else {
            userService.updateUserAfterOAuth2LoginSuccess(user, name, AuthenticationProvider.GOOGLE); //update the user
            System.out.println("Already an user");
        }
        System.out.println("OAuth2 Email - " + email);
        System.out.println("OAuth2 Username - " + name);
        System.out.println("Client Name - " + clientName);
        System.out.println();

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
