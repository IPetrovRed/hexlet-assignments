package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void set() throws Exception {
        KeyValueStorage storage = new FileKV(filepath.toString(), new HashMap<>());
        storage.set("key", "value");
        Map<String, String> expected = Map.of("key", "value");
        assertEquals(expected, storage.toMap());
    }

    @Test
    void unset() throws Exception {
        KeyValueStorage storage = new FileKV(filepath.toString(), new HashMap<>());
        storage.set("key", "value");
        storage.unset("key");
        Map<String, String> expected = Map.of();
        assertEquals(expected, storage.toMap());
    }

    @Test
    void get() throws Exception {
        KeyValueStorage storage = new FileKV(filepath.toString(), new HashMap<>());
        String value = storage.get("key", "default");
        assertEquals("default", value);
    }

    @Test
    void toMap() throws Exception {
        KeyValueStorage storage = new FileKV(filepath.toString(), new HashMap<>());
        Map<String, String> data = storage.toMap();
        assertEquals(Map.of(), data);
    }

    // END
}
