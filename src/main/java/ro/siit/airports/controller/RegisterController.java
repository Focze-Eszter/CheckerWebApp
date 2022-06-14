package ro.siit.airports.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;
import ro.siit.airports.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository repo;

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); //if an user enters no data it don't appears like spaces, but as null values
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

   /* @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
       /* if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "register";
        }
         userService.createUser(user);
        return "login-successfully";
    }*/

    /*@PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "register_successfully";
    }*/

    @PostMapping("process_register/save")
    public String saveUser(@Valid @ModelAttribute(name = "user") User user, RedirectAttributes ra, @RequestParam("fileImage")
            MultipartFile multipartFile, BindingResult bindingResult) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setImage(fileName);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = repo.save(user);
        String uploadDir = "./user-images/" + savedUser.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save the uploaded file: " + fileName);
        }
        ra.addFlashAttribute("message", "You have been registered successfully.");
        if (bindingResult.hasErrors()) {
            return "register";
        }
        return "register_successfully";

    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "show-users";
    }
}

  /*  @PostMapping("/addUser")
    public ModelAndView addUser(@RequestParam("email") String email, User user)
    {
        ModelAndView mv=new ModelAndView("success");
        List<User> list= (List<User>) repo.findUserByEmail(email);

        if(list.size()!=0)
        {
            mv.addObject("message", "Oops!  There is already a user registered with the email provided.");

        }
        else
        {
            repo.save(user);
            mv.addObject("message","User has been successfully registered.");
        }

        return mv;
    }
    @GetMapping("/login-successfully")
    public String success()
    {
        return "login-successfully";
    }

    @PostMapping("/login")
    public String login_user(@RequestParam("username") String username, @RequestParam("password") String password,
                             HttpSession session, ModelMap modelMap)
    {

        User auser= repo.findByUsernameAndPassword(username, password);

        if(auser!=null)
        {
            String uname=auser.getEmail();
            String upass=auser.getPassword();

            if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(upass))
            {
                session.setAttribute("username",username);
                return "login-sucessfully";
            }
            else
            {
                modelMap.put("error", "Invalid Account");
                return "login";
            }
        }
        else
        {
            modelMap.put("error", "Invalid Account");
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session)
    {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/login";
    }*/

