package coronavirus.tracker.demo.controller;


import coronavirus.tracker.demo.entity.User;
import coronavirus.tracker.demo.repository.CountryRepository;
import coronavirus.tracker.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;
    private final CountryRepository countryRepository;

    public RegisterController(UserService userService, CountryRepository countryRepository) {
        this.userService = userService;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("countries", this.countryRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        this.userService.save(user);
        return "redirect:/";
    }

}
