package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testLoad() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        assertEquals(0, storage.toMap().size());
        storage.set("key", "value");
        storage.save();
        storage = new FileKV("src/test/resources/file", new HashMap<>());
        Map<String, String> data = storage.toMap();
        assertEquals(1, data.size());
        assertEquals("value", data.get("key"));
    }

    @Test
    void testSave() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        storage.set("key", "value");
        storage.save();
        String content = new String(Files.readAllBytes(filepath));
        assertTrue(content.contains("\"key\":\"value\""));
    }

    @Test
    void testSet() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        storage.set("key", "value");
        assertEquals("value", storage.get("key", "default"));
        storage.save();
        storage = new FileKV("src/test/resources/file", new HashMap<>());
        assertEquals("value", storage.get("key", "default"));
    }

    @Test
    void testUnset() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        storage.set("key", "value");
        storage.unset("key");
        assertEquals("default", storage.get("key", "default"));
        storage.save();
        storage = new FileKV("src/test/resources/file", new HashMap<>());
        assertEquals("default", storage.get("key", "default"));
    }

    @Test
    void testGet() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        storage.set("key", "value");
        assertEquals("value", storage.get("key", "default"));
        storage.save();
        storage = new FileKV("src/test/resources/file", new HashMap<>());
        assertEquals("value", storage.get("key", "default"));
    }

    @Test
    void testToMap() throws Exception {
        FileKV storage = new FileKV("src/test/resources/file", new HashMap<>());
        storage.set("key", "value");
        Map<String, String> data = storage.toMap();
        assertEquals(1, data.size());
        assertEquals("value", data.get("key"));
    }

    // END
}
