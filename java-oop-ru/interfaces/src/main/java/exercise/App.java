package exercise;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream()
                .sorted((a, b) -> (int) (a.getArea() - b.getArea())) // Сортировать по площади
                .limit(n)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
