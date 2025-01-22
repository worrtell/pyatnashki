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

    public List<String> onMove(User u) throws URISyntaxException, IOException, InterruptedException {
        request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("http://localhost:8081/pyatnashki/game"))
                .POST(ofForm(u))
                .build();

        System.out.println("Req " + request.toString());

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Body: " + response.body());
        
        return null;
    }

    public static HttpRequest.BodyPublisher ofForm(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(user));
            return HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
