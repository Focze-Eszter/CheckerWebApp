package ro.siit.airports.domain;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
    name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Not a valid email")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Size(min=6, max=20, message = "Password must contain 6 or more characters")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Password Two cannot be blank")
    @Column(name = "re_entered_password")
    private String re_entered_password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "image", nullable  = true)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authProvider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_entered_password() {
        return re_entered_password;
    }

    public void setRe_entered_password(String re_entered_password) {
        this.re_entered_password = re_entered_password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) {
            return null;
        }
        return "/user-images/" + id + "/" + image;
    }

    @Override
    public String toString() {
        return "User {" +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "enabled" + enabled + '\'' +
        '}';
    }
}
//UPDATE users SET email = '' WHERE id = '';
