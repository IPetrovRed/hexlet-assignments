package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.IOException;

@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() {
        ObjectMapper car = new ObjectMapper();
        try {
            return car.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car unserialize(String json) {
        ObjectMapper car = new ObjectMapper();
        try {
            return car.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
