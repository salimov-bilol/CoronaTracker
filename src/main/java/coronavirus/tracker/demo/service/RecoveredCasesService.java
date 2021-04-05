package coronavirus.tracker.demo.service;


import coronavirus.tracker.demo.entity.CoronavirusDataRecovered;
import coronavirus.tracker.demo.entity.DataByCountry;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecoveredCasesService {

    private final RestService restService;

    public RecoveredCasesService(RestService restService) {
        this.restService = restService;
    }

    public List<CoronavirusDataRecovered> getStates() {
        return restService.getCoronavirusDataRecoveries();
    }

    public List<DataByCountry> getCountries() {
        return fetchDataByCountry().collect(Collectors.toList());
    }

    public int getTotalReportedRecovered() {
        return restService.getCoronavirusDataRecoveries().stream().mapToInt(CoronavirusDataRecovered::getLatestDayRecovered).sum();
    }

    public int getTotalNewRecovered() {
        return restService.getCoronavirusDataRecoveries().stream().mapToInt(CoronavirusDataRecovered::getDifference).sum();
    }

    public List<DataByCountry> getTop10Countries() {
        return fetchDataByCountry()
                .sorted(Comparator.comparing(DataByCountry::getLatestDay, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private Stream<DataByCountry> fetchDataByCountry() {
        return restService
                .getCoronavirusDataRecoveries()
                .stream()
                .collect(Collectors.groupingBy(CoronavirusDataRecovered::getCountry)).entrySet()
                .stream()
                .map(x -> {
                    int sumLatestDayCases = x.getValue().stream().mapToInt(CoronavirusDataRecovered::getLatestDayRecovered).sum();
                    int sumPreviousDayCases = x.getValue().stream().mapToInt(CoronavirusDataRecovered::getPreviousDayRecovered).sum();
                    int sumDiffFromPrevDay = sumLatestDayCases - sumPreviousDayCases;
                    return new DataByCountry(x.getKey(), sumLatestDayCases, sumPreviousDayCases, sumDiffFromPrevDay);
                });
    }
}
