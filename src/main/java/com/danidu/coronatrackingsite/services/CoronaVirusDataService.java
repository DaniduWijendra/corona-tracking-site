package com.danidu.coronatrackingsite.services;

import com.danidu.coronatrackingsite.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    public List<LocationStats> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<LocationStats> allStats) {
        this.allStats = allStats;
    }

    List<LocationStats> allStats = new ArrayList<>();
    private static String coronaDataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @PostConstruct // when this service is construct should call this method
    @Scheduled(cron = "* * 1 * * *") // create a proxy to call this method in specified frequency

    public void fetchCoronaDetails() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest httpRequest = HttpRequest.newBuilder().
              uri(URI.create(coronaDataUrl)).
              build();
      HttpResponse<String> httpResponse=
      client.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        StringReader stringReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);//use to auto detect headers use strings as objects.
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int previousCases =  Integer.parseInt(record.get(record.size()-2));

            locationStats.setLatestTotalCases(latestCases);
            locationStats.setPreviousCases(previousCases);
            locationStats.setDifferFromPrev(latestCases - previousCases);

            newStats.add(locationStats);
        }
        this.allStats = newStats;


    }
}
