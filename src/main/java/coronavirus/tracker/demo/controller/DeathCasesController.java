package coronavirus.tracker.demo.controller;

import coronavirus.tracker.demo.entity.CoronavirusDataDeaths;
import coronavirus.tracker.demo.entity.DataByCountry;
import coronavirus.tracker.demo.service.DeathCasesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/death")
public class DeathCasesController {
    private final DeathCasesService deathCasesService;

    public DeathCasesController(DeathCasesService deathCasesService) {
        this.deathCasesService = deathCasesService;
    }

    @GetMapping("/state")
    public String getStateData(Model model) {
        List<CoronavirusDataDeaths> deathsByStates = deathCasesService.getStates();
        model.addAttribute("deathsByStates", deathsByStates);
        return "deathState";
    }

    @GetMapping("/country")
    public String getCountryData(Model model) {
        List<DataByCountry> deathsByCountries = deathCasesService.getCountries();
        model.addAttribute("deathsByCountries", deathsByCountries);
        return "deathCountry";
    }

    @GetMapping("/video")
    public String getAnimation() {
        return "deathsVideo";
    }

    @GetMapping("/ranking")
    public String getRanking(Model model) {
        List<DataByCountry> top10CountriesByDeaths = deathCasesService.getTop10Countries();
        model.addAttribute("top10CountriesByDeaths", top10CountriesByDeaths);
        return "deathRanking";
    }
}
