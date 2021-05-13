package CST438hw2.service;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import CST438hw2.domain.City;
import CST438hw2.domain.CityRepository;
import CST438hw2.domain.CountryRepository;
import CST438hw2.domain.CityInfo;
import CST438hw2.domain.Country;
import CST438hw2.domain.TimeAndTemp;
import CST438hw2.service.WeatherService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CityServiceTest {
    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private WeatherService weatherService;
    
    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private CityService cityService;

    @BeforeEach
    public void setUpEach() {
       MockitoAnnotations.initMocks(this);
    }
    
  //Tests when city is found
    @Test
    public void test1() throws Exception {

        Country country = new Country("TST", "Test Country");
        CityInfo city = new CityInfo(1, "Test", "ABC", "Country Name", "Country District",1,
                1.0);

        TimeAndTemp temp = new TimeAndTemp(100.222, 14678392, 2400);

        List<City> cityList = new ArrayList<City>();

        given(cityRepository.findByName("Test1 City")).willReturn(cityList);


        given(countryRepository.findByCode("ABC")).willReturn(country);

        given(weatherService.getTimeAndTemp("Test1 City")).willReturn(temp);

        CityInfo testResult = cityService.getCityInfo("Test1 City");

        CityInfo expectedResult = new CityInfo(1, "Test1", "ABC", "Country Name", "Country District",1,
                1.0);

        assertThat(testResult).isEqualTo(expectedResult);
    }
    
    //  Test when city is not found
    @Test
    public void test2() {

        CityInfo city = cityService.getCityInfo("City Test2");

        CityInfo expectedResult = null;

        assertThat(city).isEqualTo(expectedResult);
    }
    
    
    
    
    
    
    
}
