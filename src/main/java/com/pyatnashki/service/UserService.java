package com.pyatnashki.service;

import com.pyatnashki.data.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class UserService {
    Map<String, User> users;

    public UserService() {
         users = new HashMap<>();
    }

    public void add(User user) {
        if (!users.containsKey(user.getSecretKey())) {
            System.out.println("ADDDDDD");
            if (!users.isEmpty()) {
                System.out.println("find pair");
                findFree(user);
            }
            users.put(user.getSecretKey(), user);
        }
        else {
            users.get(user.getSecretKey()).setKeycode(user.getKeycode());
        }
    }

    public int getPairKeyCode(User user) {
        //System.out.println("SERVICE " + user.getPairSecretKey().isEmpty());
        //System.out.println(user.getName() + " " + user.getPairSecretKey());
        String pairSecretKey = getPairSecretKey(user);
        if (!pairSecretKey.isEmpty()) {
            System.out.println("got pair");
            return users.get(pairSecretKey).getKeycode();
        }
        System.out.println("no pair");
        return 0;
    }

    private void findFree(User young) {
        users.forEach( (secretKey,u) -> {
                    if (u.getPairSecretKey().isEmpty()) {
                        System.out.println("empty");
                        makePair(u, young);
                    }
                }
        );
    }

    private void makePair(User young, User old) {
        System.out.println("set new pair key " + young + " " + old);
        young.setPairSecretKey(old.getSecretKey());
        old.setPairSecretKey(young.getSecretKey());
    }

    private String getPairSecretKey(User user) {
        return users.get(user.getSecretKey()).getPairSecretKey();
    }
}
