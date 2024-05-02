package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    public static void save(Path path, Car car) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonCar = objectMapper.writeValueAsString(car);
            Files.write(path, jsonCar.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCar = Files.readString(path);
        return objectMapper.readValue(jsonCar, Car.class);
    }
}
// END
