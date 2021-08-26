package com.geolocator.geoloc.repo;

import com.geolocator.geoloc.model.MyString;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class GeoRepository {

    private List<MyString> geoDb;

    public GeoRepository() {
        this.geoDb = new CopyOnWriteArrayList<>();
        geoDb.add(MyString.builder()
                .address("Россия,РеспубликаТатарстан,Казань,улицаСибгатаХакима,4")
                .build());
    }

    public void save(MyString myString) {
        geoDb.clear();
        geoDb.add(myString);
    }

}
