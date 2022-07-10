package ro.siit.airports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.model.Search;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView displayHomePage(Model model) {
        final ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView displayHomeScreen(Model model) {
        final ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping("/administration")
    public ModelAndView displayAdministrationPage(Model model) {
        final ModelAndView modelAndView = new ModelAndView("administration");
        return modelAndView;
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
