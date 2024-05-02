package exercise;

// BEGIN
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
