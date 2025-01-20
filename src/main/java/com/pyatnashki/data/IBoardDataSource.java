package com.pyatnashki.data;

import java.util.List;

// board ds - с данными конкретного пользователя

public interface IBoardDataSource {
    List<Integer> getSchema(String sessionId);
}
