package com.pyatnashki.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDataSource {
    HttpClient client;
    HttpRequest request;

    public BoardDataSource() {
        System.out.println("new client");
        client = HttpClient.newHttpClient();
    }

    public void onMove(User u){
        HttpResponse response = sendUser(u, "write");
        System.out.println("Body: " + response.body());
    }

    public int getPairMove(User u) {
        HttpResponse response = sendUser(u, "getPairCode");
        System.out.println("Body !!!!!!!: " + response.body());
        String strResp = (String) response.body();

        System.out.println("len " + strResp.length());

        if (strResp.length() == 2) {
            System.out.println("respB is empty");
            return 0;
        }
        strResp = strResp.replaceAll("\"", "");
        return Integer.parseInt(strResp);
    }

    private static HttpRequest.BodyPublisher ofForm(User user, String type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return HttpRequest.BodyPublishers.ofString(formBody(mapper.writeValueAsString(user), type));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse sendUser(User u, String type) {
        request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("http://localhost:8081/pyatnashki/game"))
                .POST(ofForm(u, type))
                .build();

        System.out.println("Req " + request.toString());

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static String formBody(String body, String type) {
        return "{\"user\":" + body + ",\"type\":\"" + type + "\"}";
    }
}
