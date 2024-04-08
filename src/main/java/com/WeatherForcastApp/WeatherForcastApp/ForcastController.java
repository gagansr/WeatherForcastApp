package com.WeatherForcastApp.WeatherForcastApp;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class ForcastController {

    private final RapidApiClient rapidApiClient;

    public ForcastController(RapidApiClient rapidApiClient){
        this.rapidApiClient = rapidApiClient;
    }

    @GetMapping("/summary/{city}")
    public JsonNode getWeatherSummaryByCity(@PathVariable("city") String city) throws IOException, InterruptedException{
        ObjectMapper mapper = new ObjectMapper();
        String resultString = rapidApiClient.getForecastSummaryByCity(city);
        return mapper.readTree(resultString);
    }
    
    @GetMapping("/hourly/{city}")
    public JsonNode getWeatherHourlyByCity(@PathVariable("city") String city) throws IOException, InterruptedException{
        ObjectMapper mapper = new ObjectMapper();
        String resultString = rapidApiClient.getForecastHourlyByCity(city);
        return mapper.readTree(resultString);
    }
}
