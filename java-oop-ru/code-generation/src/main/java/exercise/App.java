package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

class App {
    public static void save(Path filePath, Car car) {
        String serializedCar = car.serialize();
        try {
            Files.writeString(filePath, serializedCar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path filePath) {
        try {
            String filePathAsString = Files.readString(filePath);
            return Car.unserialize(filePathAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
