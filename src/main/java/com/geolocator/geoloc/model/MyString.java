package com.geolocator.geoloc.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
public class MyString {
    private String address;

    public MyString(String myAdd) {
        this.address = myAdd;
    }
}
