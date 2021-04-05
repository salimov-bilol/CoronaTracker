package coronavirus.tracker.demo.controller;

import coronavirus.tracker.demo.entity.CoronavirusDataCases;
import coronavirus.tracker.demo.entity.DataByCountry;
import coronavirus.tracker.demo.service.ConfirmedCasesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cases")
public class ConfirmedCasesController {

    private final ConfirmedCasesService confirmedCasesService;

    public ConfirmedCasesController(ConfirmedCasesService confirmedCasesService) {
        this.confirmedCasesService = confirmedCasesService;
    }

    @GetMapping("/state")
    public String getStateData(Model model) {
        List<CoronavirusDataCases> casesByStates = confirmedCasesService.getStates();
        model.addAttribute("casesByStates", casesByStates);
        return "state";
    }

    @GetMapping("/country")
    public String getCountryData(Model model) {
        List<DataByCountry> casesByCountries = confirmedCasesService.getCountries();
        model.addAttribute("casesByCountries", casesByCountries);
        return "country";
    }

    @GetMapping("/video")
    public String getAnimation() {
        return "casesVideo";
    }

    @GetMapping("/ranking")
    public String getRanking(Model model) {
        List<DataByCountry> top10CountriesByCases = confirmedCasesService.getTop10Countries();
        model.addAttribute("top10CountriesByCases", top10CountriesByCases);
        return "ranking";
    }
}
