package CST438hw2.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;

import CST438hw2.domain.*;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

    @MockBean
    private CityRepository cityRepository;
    
    @Autowired
    private MockMvc mvc;
    
    private JacksonTester<City> jsonCityAttempt;
    
    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void test1() throws Exception {
        
        Country country = new Country("ABC", "Test1 Country");
        CityInfo city = new CityInfo(1, "Test1", "ABC", "Country Name", "Country District",1,
                1.0);
        List<City> cities = new ArrayList<City>();
        
        given(cityRepository.findByName("Test1")).willReturn(cities);

        MockHttpServletResponse response = mvc.perform(get("/city/TestCity"))
                .andReturn().getResponse();
                
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        
        City cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
        
        CityInfo expectedResult = new CityInfo(1, "Test", "ABC", "Country Name", "Country District",1,
                1.0);
        
        assertThat(city).isEqualTo(expectedResult);
        
    }
    
    
}
