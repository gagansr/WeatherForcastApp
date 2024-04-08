package com.WeatherForcastApp.WeatherForcastApp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;
@Component
public class RapidApiClient {

    private static final String API_URL = "https://forecast9.p.rapidapi.com/rapidapi/forecast/";
    private static final String API_KEY = "17cf2f6074msh17ee1574fc46833p162b81jsn1d98c2080867";
    private static final String API_HOST = "forecast9.p.rapidapi.com";

    public String getForecastSummaryByCity(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(API_URL + city + "/summary/"))
		.header("X-RapidAPI-Key", API_KEY)
		.header("X-RapidAPI-Host", API_HOST)
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
    public String getForecastHourlyByCity(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(API_URL+city+"/hourly/"))
		.header("X-RapidAPI-Key", API_KEY)
		.header("X-RapidAPI-Host", API_HOST)
		.method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
