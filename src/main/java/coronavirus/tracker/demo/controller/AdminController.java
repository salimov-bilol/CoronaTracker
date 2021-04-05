package coronavirus.tracker.demo.controller;

import coronavirus.tracker.demo.service.DataService;
import coronavirus.tracker.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;
    private final DataService dataService;

    public AdminController(DataService dataService, UserService userService) {
        this.userService = userService;
        this.dataService = dataService;
    }

    @GetMapping("/user")
    public String getUsers(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "user";
    }

    @GetMapping("/updateCases")
    public String updateCases() {
        this.dataService.updateCases();
        return "redirect:/";
    }
    @GetMapping("/updateDeaths")
    public String updateDeaths() {
        this.dataService.updateDeaths();
        return "redirect:/";
    }
    @GetMapping("/updateRecovered")
    public String updateRecovered() {
        this.dataService.updateRecovered();
        return "redirect:/";
    }
}
