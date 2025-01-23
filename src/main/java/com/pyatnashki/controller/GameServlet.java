package com.pyatnashki.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyatnashki.data.User;
import com.pyatnashki.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    //Map<String, User> users;
    UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        //users = (Map<String, User>) servletContext.getAttribute("users");
        userService = (UserService) servletContext.getAttribute("userService");

        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String ans = "";

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        System.out.println("session " + req.getSession().getId());

        String body = getBody(req);

        System.out.println("body " + body);

        JsonNode rootNode = mapper.readTree(body);

        JsonNode userNode = rootNode.get("user");
        String userJson = mapper.writeValueAsString(userNode);

        JsonNode typeNode = rootNode.get("type");
        String type = mapper.writeValueAsString(typeNode).replaceAll("\"", "");

        User u = mapper.readValue(userJson, User.class);
        // when just send info (user) type = write, when need pair's moves type = getPairCode

        System.out.println(type);
        if (type.equals("write")) {
            System.out.println("write request");
            userService.add(u);
        }
        else if (type.equals("getPairCode")) {
            System.out.println("getPairCode request");
            //System.out.println("service get key " + userService.getPairKeyCode(u));
            ans = String.valueOf(userService.getPairKeyCode(u));
            System.out.println("ans " + ans);
            //ans = "38";
        }
        System.out.println("list of users: " + userService.getUsers());

        mapper.writeValue(response.getWriter(), ans);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
    }

    private static String getBody(HttpServletRequest req) {
        StringBuilder postData = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                postData.append(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return postData.toString();
    }
}