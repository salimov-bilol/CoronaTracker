package coronavirus.tracker.demo.controller;


import coronavirus.tracker.demo.entity.CoronavirusDataRecovered;
import coronavirus.tracker.demo.entity.DataByCountry;
import coronavirus.tracker.demo.service.RecoveredCasesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recovered")
public class RecoveredCasesController {
    private final RecoveredCasesService recoveredCasesService;

    public RecoveredCasesController(RecoveredCasesService recoveredCasesService) {
        this.recoveredCasesService = recoveredCasesService;
    }

    @GetMapping("/state")
    public String getStateData(Model model) {
        List<CoronavirusDataRecovered> casesByStates = recoveredCasesService.getStates();
        model.addAttribute("recoveredByStates", casesByStates);
        return "recoveredState";
    }

    @GetMapping("/video")
    public String getAnimation() {
        return "recoveredVideo";
    }

    @GetMapping("/country")
    public String getCountryData(Model model) {
        List<DataByCountry> casesByCountries = recoveredCasesService.getCountries();
        model.addAttribute("recoveredByCountries", casesByCountries);
        return "recoveredCountry";
    }

    @GetMapping("/ranking")
    public String getRanking(Model model) {
        List<DataByCountry> top10CountriesByCases = recoveredCasesService.getTop10Countries();
        model.addAttribute("top10CountriesByRecovered", top10CountriesByCases);
        return "recoveredRanking";
    }
}
