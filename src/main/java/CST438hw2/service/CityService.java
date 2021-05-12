package CST438hw2.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CST438hw2.domain.*;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private WeatherService weatherService;
    
    public CityInfo getCityInfo(String cityName) { 
        List<City> cities = cityRepository.findByName(cityName);
        if ( cities.size()==0) {
            return null;
            
        } else {
            City city= cities.get(0);
            System.out.println("**********************");
            System.out.println(city.getCountry().getCode());
            System.out.println("**********************");
            // get current weather
            TimeAndTemp cityWeather = weatherService.getTimeAndTemp(cityName);
            Country country = countryRepository.findByCode(city.getCountry().getCode());

            // convert temp from degrees Kelvin to degrees Fahrenheit
            double tempF = Math.round((cityWeather.temp - 273.15) * 9.0/5.0 + 32.0);
            
            System.out.println("**********************");
            System.out.println(city.getCountry());
            System.out.println("**********************");
            return new CityInfo(city.getID(), city.getName(), city.getDistrict(), country.getName(), country.getCode(),city.getPopulation(), tempF);
        }
    }
    

}