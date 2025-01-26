package com.pyatnashki.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    String secretKey;
    ArrayList<String> order;
    String pairSecretKey;
    int keycode;

    public User(String name) {
        this.name = name;
        secretKey = String.valueOf(UUID.randomUUID());
        order = new ArrayList<>();
        pairSecretKey = "";
    }

    @Override
    public String toString() {
        return "name = " + name + ", keycode = " + keycode + " pair " + pairSecretKey;
    }
}