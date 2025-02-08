package com._csc.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class WeatherController {

    private static final String API_KEY = "eb5948d3bcf5213532f631fe3947f592";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    @GetMapping("/weather")
    public ResponseEntity<Weather> getWeather(@RequestParam String city) {
        String url = BASE_URL + city + "&APPID=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            String cityName = root.path("name").asText();
            double temp = root.path("main").path("temp").asDouble();
            String condition = root.path("weather").get(0).path("description").asText();

            Weather weather = new Weather(cityName, temp, condition);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
