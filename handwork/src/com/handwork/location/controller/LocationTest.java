package com.handwork.location.controller;


import com.handwork.location.service.LocationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet("/LocationTest")
public class LocationTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        getGeoCode("경기 안양시 동안구 임곡로 16");
    }

    private String getGeoCode(String flocation) {
        StringBuffer result = new StringBuffer();
        JSONObject obj = null;
        try {
            String address= URLEncoder.encode("경기 안양시 동안구 임곡로 16", "UTF-8");
            String apiKey= URLEncoder.encode("AIzaSyAY--fPA18-UuvufmlYYCPygAJYmQNXZVo", "UTF-8");
            String urlStr = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",address,apiKey);
            System.out.println(urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            int rspCode = conn.getResponseCode();
            if (rspCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String nextLine = br.readLine();
                while ((nextLine = br.readLine()) != null) {
                    result.append(nextLine);
                }
            }
            conn.disconnect();

            String resultStr = result.toString();
            resultStr = resultStr.substring(resultStr.indexOf("\"location\" : {"));
            resultStr = resultStr.substring(resultStr.indexOf("{"),resultStr.indexOf("}")+1);

            JSONParser jsonParse = new JSONParser();
            obj =  (JSONObject)jsonParse.parse(resultStr);

        } catch (IOException e) {
            System.out.println("RestCall Fail : " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Json error");
        }

        return String.valueOf(obj);
    }
}