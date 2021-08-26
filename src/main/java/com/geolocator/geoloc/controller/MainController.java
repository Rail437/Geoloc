package com.geolocator.geoloc.controller;

import lombok.RequiredArgsConstructor;
import com.geolocator.geoloc.model.MyString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.geolocator.geoloc.service.GeocodingService;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final GeocodingService geocodingService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("mystrings", geocodingService.getAddress());
        return "main";
    }

    @RequestMapping(params = "find-address", method = RequestMethod.POST)
    public String findAddress(@ModelAttribute MyString myString){
        String myAdd = geocodingService.httpGetGeo(myString.getAddress());
        geocodingService.saveAddress(myAdd);
        return "redirect:/";
    }

   @RequestMapping(params = "find-geo", method = RequestMethod.POST)
    public String findGeocode(@ModelAttribute MyString myNewString){
        String myGeo = geocodingService.httpGetAddress(myNewString.getAddress());
        geocodingService.saveAddress(myGeo.replace(" ", ","));
        return "redirect:/";
    }
}
