package com.geolocator.geoloc.service;


import com.geolocator.geoloc.model.MyData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FindGeo {

    MyData myData = new MyData();
    public String findGeo(String str){
        final StringBuilder content = new StringBuilder();

        try {
            String myUrl = myData.getHttps()+ URLEncoder.encode(str, "UTF-8");
            final URL url = new URL(myUrl);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setUseCaches(false);

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(content);
                return checkPos(content.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String checkPos(String string){
        if(string.contains("pos")){
            String[] words = string.split("><");
            for (String word : words) {
                if (word.contains("pos>")) {
                    String first = word.split(">")[1];
                     return first.split("<")[0];
                }
            }
        }
        return null;
    }
}
