package com.handwork.location.controller;


import com.handwork.location.service.LocationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/location")
public class LocationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-json; charset=UTF-8");
        String type = (request.getParameter("type"));
        if (type == null || type.equals("")) {
            request.getRequestDispatcher("/WEB-INF/view/location/location.jsp").forward(request, response);
        }else if (type.equals("init")) {
            JSONArray json = initMarker(request, response);
            response.getWriter().print(json);
        } else if (type.equals("click")) {
            JSONArray json = clickMarker(request, response);
            response.getWriter().print(json);
        }

    }

    private JSONArray initMarker(HttpServletRequest request, HttpServletResponse response) {
        LocationService service = new LocationService();

        return service.getData("init","SELECT id,latlng FROM handwork.market");
    }

    private JSONArray clickMarker(HttpServletRequest request, HttpServletResponse response) {
        LocationService service = new LocationService();
        String boardNum = request.getParameter("board-num");
        System.out.println("SELECT * FROM handwork.market WHERE id = "+boardNum);
        return service.getData("click","SELECT * FROM handwork.market WHERE id = "+boardNum);
    }

}