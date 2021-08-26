package com.geolocator.geoloc.service;


import com.geolocator.geoloc.model.MyData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FindAddress {
    MyData myData = new MyData();

    public String findAddress(String str){
        StringBuilder content = new StringBuilder();
        try {
            String myUrl = myData.getHttps()+ URLEncoder.encode(str, "UTF-8");
            final URL url = new URL(myUrl);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return checkAddress(content.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String checkAddress(String string) {
        if(string.contains("formatted")){
            String[] words = string.split("><");
            for (String word : words) {
                if (word.contains("formatted>")) {
                    String first = word.split(">")[1];
                    return first.split("<")[0];
                }
            }
        }
        return null;
    }
}
