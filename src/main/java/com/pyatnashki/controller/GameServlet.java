package com.pyatnashki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyatnashki.data.User;
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
import java.util.Map;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    Map<String, User> users;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        users = (Map<String, User>) servletContext.getAttribute("users");

        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        System.out.println("session " + req.getSession().getId());

        String body = getBody(req);

        System.out.println("body " + body);

        User u = mapper.readValue(body, User.class);
        System.out.println("user " + u.toString());

        if (!users.containsKey(u.getSecretKey())) {
            users.put(u.getSecretKey(), u);
        }

        System.out.println(users);

        mapper.writeValue(response.getWriter(), "answer");
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