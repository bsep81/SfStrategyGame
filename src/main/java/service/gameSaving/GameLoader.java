package service.gameSaving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mapper.GameMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameLoader {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GameMapper gameMapper = new GameMapper();


    public void loadGame() {

        String gameJson = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("save.dat"))) {
            gameJson = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder(gameJson);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, (char) (sb.charAt(i) - 111));
        }

        try {
            gameMapper.mapPOJOToGame(objectMapper.readValue(sb.toString(), GamePOJO.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
