package com.pyatnashki;

import com.pyatnashki.handler.UserRequestHandler;

public class Main {
    public static void main(String[] args) {
        UserRequestHandler ds = new UserRequestHandler();
//        try {
//            ds.onMove(null);
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        UserService userService = new UserService();
//        userService.add(new User("h"));
//        System.out.println(userService.getUsers());
//        userService.add(new User("p"));
//        System.out.println(userService.getUsers());
//        userService.add(new User("t"));
//        System.out.println(userService.getUsers());
//        userService.add(new User("s"));
//        System.out.println(userService.getUsers());

        String body = "{\"name\":\"s\",\"secretKey\":\"2be3e943-8c71-4034-af8a-cee8f0261c4d\",\"schema\":[],\"pairSecretKey\":null,\"keycode\":0}";
        String type = "write";
        System.out.println("{\"user\":" + body + ",\"type\":\"" + type + "\"}");
    }
}
