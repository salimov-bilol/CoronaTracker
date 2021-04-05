package coronavirus.tracker.demo.controller;


import coronavirus.tracker.demo.service.ConfirmedCasesService;
import coronavirus.tracker.demo.service.DeathCasesService;
import coronavirus.tracker.demo.service.RecoveredCasesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ConfirmedCasesService confirmedCasesService;
    private final DeathCasesService deathCasesService;
    private final RecoveredCasesService recoveredCasesService;

    public HomeController(ConfirmedCasesService confirmedCasesService,
                          DeathCasesService deathCasesService,
                          RecoveredCasesService recoveredCasesService) {
        this.confirmedCasesService = confirmedCasesService;
        this.deathCasesService = deathCasesService;
        this.recoveredCasesService = recoveredCasesService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        int totalReportedCases = confirmedCasesService.getTotalReportedCases();
        int totalNewCases = confirmedCasesService.getTotalNewCases();
        int totalReportedDeaths = deathCasesService.getTotalReportedDeaths();
        int totalNewDeaths = deathCasesService.getTotalNewDeaths();
        int totalReportedRecovered = recoveredCasesService.getTotalReportedRecovered();
        int totalNewRecovered = recoveredCasesService.getTotalNewRecovered();
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("totalReportedDeaths", totalReportedDeaths);
        model.addAttribute("totalNewDeaths", totalNewDeaths);
        model.addAttribute("totalReportedRecovered", totalReportedRecovered);
        model.addAttribute("totalNewRecovered", totalNewRecovered);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
