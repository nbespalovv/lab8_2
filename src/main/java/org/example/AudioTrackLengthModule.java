package org.example;


import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class AudioTrackLengthModule implements Module {
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("mp3");
        return list;
    }

    @Override
    public String getDescription() {
        return "Возвращает длительность mp3-тегов";
    }

    @Override
    public void invoke(String fileName) {
        try {
            Process process = Runtime.getRuntime().exec(String.format("ffprobe -v quiet -show_format -print_format json %s", fileName));
            Scanner scanner = new Scanner(process.getInputStream());
            StringJoiner joiner = new StringJoiner("\n");
            while (scanner.hasNextLine()) {
                joiner.add(scanner.nextLine());
            }
            String json = joiner.toString();
            Gson gson = new Gson();
            Map map = gson.fromJson(json, Map.class);
            Map format = (Map) map.get("format");
            String duration = (String) format.get("duration");
            double doubleDuration = Double.parseDouble(duration);
            int durationInMinutes = (int) (doubleDuration / 60);
            int durationInSeconds = (int) (doubleDuration % 60);
            System.out.printf("Продолжительность трека %s: %d минут, %d секунд", fileName, durationInMinutes, durationInSeconds);
            process.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
