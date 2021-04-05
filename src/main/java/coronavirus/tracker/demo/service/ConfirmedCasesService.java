package coronavirus.tracker.demo.service;


import coronavirus.tracker.demo.entity.CoronavirusDataCases;
import coronavirus.tracker.demo.entity.DataByCountry;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConfirmedCasesService {

    private final RestService restService;

    public ConfirmedCasesService(RestService restService) {
        this.restService = restService;
    }

    public List<CoronavirusDataCases> getStates() {
        return restService.getCoronavirusDataCases();
    }

    public List<DataByCountry> getCountries() {
        return fetchDataByCountry().collect(Collectors.toList());
    }

    public int getTotalReportedCases() {
        return restService.getCoronavirusDataCases().stream().mapToInt(CoronavirusDataCases::getLatestDayCases).sum();
    }

    public int getTotalNewCases() {
        return restService.getCoronavirusDataCases().stream().mapToInt(CoronavirusDataCases::getDifference).sum();
    }

    public List<DataByCountry> getTop10Countries() {
        return fetchDataByCountry()
                .sorted(Comparator.comparing(DataByCountry::getLatestDay, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private Stream<DataByCountry> fetchDataByCountry() {
        return restService.
                getCoronavirusDataCases()
                .stream()
                .collect(Collectors.groupingBy(CoronavirusDataCases::getCountry)).entrySet()
                .stream()
                .map(x -> {
                    int sumLatestDayCases = x.getValue().stream().mapToInt(CoronavirusDataCases::getLatestDayCases).sum();
                    int sumPreviousDayCases = x.getValue().stream().mapToInt(CoronavirusDataCases::getPreviousDayCases).sum();
                    int sumDiffFromPrevDay = sumLatestDayCases - sumPreviousDayCases;
                    return new DataByCountry(x.getKey(), sumLatestDayCases, sumPreviousDayCases, sumDiffFromPrevDay);
                });
    }
}
