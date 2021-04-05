package coronavirus.tracker.demo.service;


import coronavirus.tracker.demo.entity.CoronavirusDataCases;
import coronavirus.tracker.demo.entity.CoronavirusDataDeaths;
import coronavirus.tracker.demo.entity.CoronavirusDataRecovered;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {
    public static final String DATA_URL_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    public static final String DATA_URL_DEATH = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    public static final String DATA_URL_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private final List<CoronavirusDataCases> coronavirusDataCases = new ArrayList<>();
    private final List<CoronavirusDataDeaths> coronavirusDataDeaths = new ArrayList<>();
    private final List<CoronavirusDataRecovered> coronavirusDataRecoveries = new ArrayList<>();

    public RestService(){
        updateCases();
        updateDeaths();
        updateRecovered();
    }

    public void updateCases() {
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
            this.coronavirusDataCases.add(coronavirusDataCases);
        });
    }
    public void updateDeaths() {
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
            this.coronavirusDataDeaths.add(coronavirusDataDeaths);
        });
    }
    public void updateRecovered() {
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
            this.coronavirusDataRecoveries.add(coronavirusDataRecovered);
        });
    }

    public List<CoronavirusDataCases> getCoronavirusDataCases() {
        return coronavirusDataCases;
    }

    public List<CoronavirusDataDeaths> getCoronavirusDataDeaths() {
        return coronavirusDataDeaths;
    }

    public List<CoronavirusDataRecovered> getCoronavirusDataRecoveries() {
        return coronavirusDataRecoveries;
    }
}
