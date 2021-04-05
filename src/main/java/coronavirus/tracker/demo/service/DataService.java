package coronavirus.tracker.demo.service;


import coronavirus.tracker.demo.entity.CoronavirusDataCases;
import coronavirus.tracker.demo.entity.CoronavirusDataDeaths;
import coronavirus.tracker.demo.entity.CoronavirusDataRecovered;
import coronavirus.tracker.demo.repository.CoronavirusDataCasesRepository;
import coronavirus.tracker.demo.repository.CoronavirusDataDeathsRepository;
import coronavirus.tracker.demo.repository.CoronavirusDataRecoveredRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DataService {

    public static final String DATA_URL_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    public static final String DATA_URL_DEATH = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    public static final String DATA_URL_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private final JdbcTemplate jdbcTemplate;
    private final CoronavirusDataCasesRepository coronavirusDataCasesRepository;
    private final CoronavirusDataDeathsRepository coronavirusDataDeathsRepository;
    private final CoronavirusDataRecoveredRepository coronavirusDataRecoveredRepository;

    public DataService(CoronavirusDataCasesRepository coronavirusDataCasesRepository,
                       CoronavirusDataDeathsRepository coronavirusDataDeathsRepository,
                       CoronavirusDataRecoveredRepository coronavirusDataRecoveredRepository,
                       JdbcTemplate jdbcTemplate) {
        this.coronavirusDataCasesRepository = coronavirusDataCasesRepository;
        this.coronavirusDataRecoveredRepository = coronavirusDataRecoveredRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.coronavirusDataDeathsRepository = coronavirusDataDeathsRepository;
    }


    public void updateCases() {
        this.jdbcTemplate.update("truncate table coronavirus_data_cases");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DATA_URL_CASES)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert httpResponse != null;
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert records != null;
        records.forEach(x -> {
            CoronavirusDataCases coronavirusDataCases = new CoronavirusDataCases();
            coronavirusDataCases.setState(x.get("Province/State"));
            coronavirusDataCases.setCountry(x.get("Country/Region"));
            int latestDayCases = Integer.parseInt(x.get(x.size() - 1));
            int previousDayCases = Integer.parseInt(x.get(x.size() - 2));
            coronavirusDataCases.setLatestDayCases(latestDayCases);
            coronavirusDataCases.setPreviousDayCases(previousDayCases);
            coronavirusDataCases.setDifference(latestDayCases - previousDayCases);
            this.coronavirusDataCasesRepository.save(coronavirusDataCases);
        });
    }
    public void updateDeaths() {
        this.jdbcTemplate.update("truncate table coronavirus_data_deaths");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DATA_URL_DEATH)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert httpResponse != null;
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert records != null;
        records.forEach(x -> {
            CoronavirusDataDeaths coronavirusDataDeaths = new CoronavirusDataDeaths();
            coronavirusDataDeaths.setState(x.get("Province/State"));
            coronavirusDataDeaths.setCountry(x.get("Country/Region"));
            int latestDayCases = Integer.parseInt(x.get(x.size() - 1));
            int previousDayCases = Integer.parseInt(x.get(x.size() - 2));
            coronavirusDataDeaths.setLatestDayDeaths(latestDayCases);
            coronavirusDataDeaths.setPreviousDayDeaths(previousDayCases);
            coronavirusDataDeaths.setDifference(latestDayCases - previousDayCases);
            this.coronavirusDataDeathsRepository.save(coronavirusDataDeaths);
        });
    }
    public void updateRecovered() {
        this.jdbcTemplate.update("truncate table coronavirus_data_recovered");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DATA_URL_RECOVERED)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert httpResponse != null;
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert records != null;
        records.forEach(x -> {
            CoronavirusDataRecovered coronavirusDataRecovered = new CoronavirusDataRecovered();
            coronavirusDataRecovered.setState(x.get("Province/State"));
            coronavirusDataRecovered.setCountry(x.get("Country/Region"));
            int latestDayCases = Integer.parseInt(x.get(x.size() - 1));
            int previousDayCases = Integer.parseInt(x.get(x.size() - 2));
            coronavirusDataRecovered.setLatestDayRecovered(latestDayCases);
            coronavirusDataRecovered.setPreviousDayRecovered(previousDayCases);
            coronavirusDataRecovered.setDifference(latestDayCases - previousDayCases);
            this.coronavirusDataRecoveredRepository.save(coronavirusDataRecovered);
        });
    }
}
