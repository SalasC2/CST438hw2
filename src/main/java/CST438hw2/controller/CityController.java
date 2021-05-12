package CST438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import CST438hw2.service.CityService;
import CST438hw2.domain.CityInfo;

@Controller
public class CityController {
    
    @Autowired
    private CityService cityService;
    
    @GetMapping("/cities/{city}")
    public String getCityInfo(@PathVariable("city") String cityName, Model model) { 
        // City Service class returns a city info object. 
        CityInfo cityInfo = cityService.getCityInfo(cityName);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(cityName);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        model.addAttribute("cityInfo", cityInfo);
        return "city_info";
  
    }
}
