package coronavirus.tracker.demo.service;


import coronavirus.tracker.demo.entity.CoronavirusDataDeaths;
import coronavirus.tracker.demo.entity.DataByCountry;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DeathCasesService {

    private final RestService restService;

    public DeathCasesService(RestService restService) {
        this.restService = restService;
    }

    public List<CoronavirusDataDeaths> getStates() {
        return this.restService.getCoronavirusDataDeaths();
    }

    public List<DataByCountry> getCountries() {
        return fetchDataByCountry().collect(Collectors.toList());
    }

    public int getTotalReportedDeaths() {
        return this.restService.getCoronavirusDataDeaths()
                .stream()
                .mapToInt(CoronavirusDataDeaths::getLatestDayDeaths)
                .sum();
    }

    public int getTotalNewDeaths() {
        return this.restService
                .getCoronavirusDataDeaths()
                .stream()
                .mapToInt(CoronavirusDataDeaths::getDifference)
                .sum();
    }

    public List<DataByCountry> getTop10Countries() {
        return fetchDataByCountry()
                .sorted(Comparator.comparing(DataByCountry::getLatestDay, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private Stream<DataByCountry> fetchDataByCountry() {
        return this.restService
                .getCoronavirusDataDeaths()
                .stream()
                .collect(Collectors.groupingBy(CoronavirusDataDeaths::getCountry)).entrySet()
                .stream()
                .map(x -> {
                    int sumLatestDayCases = x.getValue().stream().mapToInt(CoronavirusDataDeaths::getLatestDayDeaths).sum();
                    int sumPreviousDayCases = x.getValue().stream().mapToInt(CoronavirusDataDeaths::getPreviousDayDeaths).sum();
                    int sumDiffFromPrevDay = sumLatestDayCases - sumPreviousDayCases;
                    return new DataByCountry(x.getKey(), sumLatestDayCases, sumPreviousDayCases, sumDiffFromPrevDay);
                });
    }
}
