package com.geolocator.geoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Setter
public class GeocodingService {

    private final FindAddress findAdd = new FindAddress();
    private final FindGeo findGeo = new FindGeo();
    private String findAddress = "";

    public GeocodingService(String findAddress) {
        this.findAddress = findAddress;
    }

    public void saveAddress(String myString) {
        findAddress = myString;
    }

    public String getAddress() {
        return findAddress;
    }

    public String httpGetGeo(String geo) {
        return findAdd.findAddress(geo);
    }

    public String httpGetAddress(String address) {
        return findGeo.findGeo(address);
    }

}
