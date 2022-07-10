package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.repository.AirlineRepository;
import ro.siit.airports.service.AirlineService;
import java.util.List;
import java.util.Optional;

@Controller
public class AirlineController {

    @Autowired
    private AirlineRepository  airlineRepository;

    @Autowired
    private AirlineService  airlineService;

    @GetMapping("/airlines/{airlineCode}")
    public String retrieveAirlines(final Model model) {
        final List<Airline> airlines = (List<Airline>) airlineRepository.findAll();
        model.addAttribute("myAirline", airlines);
        return "airlines-search";
    }

    @RequestMapping("/searchAirline")
    public String showAirlines(Model model) {
        String keyword = null;
        return listByPage(model,1,"name", "ascending", keyword);
    }

    @GetMapping("airlines/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
                             @Param("sortedField") String sortedField,
                             @Param("sortedDirection") String sortedDirection,
                             @Param("keyword") String keyword) {

        Page<Airline> page = airlineService.listAll(currentPage, sortedField, sortedDirection, keyword);

        long totalAirlines = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Airline> listAirlines = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalAirlines", totalAirlines);
        model.addAttribute("totalPages" , totalPages);
        model.addAttribute("listAirlines", listAirlines);
        model.addAttribute("sortedField", sortedField);
        model.addAttribute("sortedDirection", sortedDirection);
        model.addAttribute("keyword", keyword);

        return "searchAirline";
    }

    @PostMapping("airlines/page/{pageNumber}")
    public String listByThePage(Model model, @PathVariable("pageNumber") int currentPage,
                             @Param("sortedField") String sortedField,
                             @Param("sortedDirection") String sortedDirection,
                             @Param("keyword") String keyword) {

        Page<Airline> page = airlineService.listAll(currentPage, sortedField, sortedDirection, keyword);

        long totalAirlines = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Airline> listAirlines = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalAirlines", totalAirlines);
        model.addAttribute("totalPages" , totalPages);
        model.addAttribute("listAirlines", listAirlines);
        model.addAttribute("sortedField", sortedField);
        model.addAttribute("sortedDirection", sortedDirection);
        model.addAttribute("keyword", keyword);

        return "searchAirline";
    }

    @RequestMapping(path = {"/edit/airline", "/edit/airline{id}"})
    public String editAirportById(Model model, @PathVariable("id") Optional<Long> id) {

        System.out.println("editAirlineById" + id);
        if (id.isPresent()) {
            Airline airline = airlineService.getAirlineById(id.get());
            model.addAttribute("airline", airline);
        } else {
            model.addAttribute("airline", new Airline());
        }
        return "attach-edited-airline";
    }

    @RequestMapping(path = "/delete/airline{id}")
    public String deleteAirlineById(Model model, @PathVariable("id") Long id) {
        System.out.println("deleteAirlineById" + id);

        airlineService.deleteAirlineById(id);
        return "searchAirline";
    }

    @RequestMapping(path = "/create/airline" /*, method = RequestMethod.POST*/)
    public String createOrUpdateAirline(Airline airline) {
        System.out.println("createOrUpdateAirline ");
        airlineService.createOrUpdateAirline(airline);
        return "redirect:/searchAirline";
    }
}

