package service.gameSaving;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mapper.GameMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {

    private ObjectMapper objectMapper = new ObjectMapper();
    private GameMapper gameMapper = new GameMapper();

    public void saveGame() {
        String gameJson = "";
        try {
            gameJson = objectMapper.writeValueAsString(gameMapper.mapGameToPOJO());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder(gameJson);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, (char) (sb.charAt(i) + 111));
        }

        File saveGameFile = new File("save.dat");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveGameFile))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
