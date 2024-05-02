package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    Car(int id, String brand, String model, String color, User owner) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.owner = owner;
    }

    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    // END
}
