package com.WeatherForcastApp.WeatherForcastApp;

import java.io.IOException;

import com.WeatherForcastApp.WeatherForcastApp.exceptionHandling.UnauthorizedAccessException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
public class ForcastController {

    private final RapidApiClient rapidApiClient;

    public ForcastController(RapidApiClient rapidApiClient){
        this.rapidApiClient = rapidApiClient;
    }

    @GetMapping("/summary/{city}")
    public JsonNode getWeatherSummaryByCity(@RequestHeader("clientId") String clientId,
                                            @RequestHeader("clientSecret") String clientSecret,
                                            @PathVariable("city") String city) throws IOException, InterruptedException, UnauthorizedAccessException {
        isAuthenticated(clientId,clientSecret);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = rapidApiClient.getForecastSummaryByCity(city);
        return mapper.readTree(resultString);
    }
    
    @GetMapping("/hourly/{city}")
    public JsonNode getWeatherHourlyByCity(@RequestHeader("clientId") String clientId,
                                           @RequestHeader("clientSecret") String clientSecret,
                                           @PathVariable("city") String city) throws IOException, InterruptedException, UnauthorizedAccessException {
        isAuthenticated(clientId,clientSecret);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = rapidApiClient.getForecastHourlyByCity(city);
        return mapper.readTree(resultString);
    }
    public void isAuthenticated(String clientId, String clientSecret) throws UnauthorizedAccessException {
        if(clientId.isBlank() || clientSecret.isBlank()){
            throw new UnauthorizedAccessException("Please provide valid Client Id or Client Secret",401,"Unauthorized_Access");
        }
    }
}
