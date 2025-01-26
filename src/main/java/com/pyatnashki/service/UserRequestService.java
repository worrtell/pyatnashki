package com.pyatnashki.service;

import com.pyatnashki.model.User;
import com.pyatnashki.handler.UserRequestHandler;

public class UserRequestService {
    UserRequestHandler requestHandler;

    public UserRequestService() {
        requestHandler = new UserRequestHandler();
    }

    public void onMove(User u){
        requestHandler.onMove(u);
    }

    public int getPairMove(User u) {
        return requestHandler.getPairMove(u);
    }
}
