package exercise;

// BEGIN
import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String filePath;
    private Map<String, String> database;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        this.database = initialData;
        Utils.writeFile(filePath, Utils.serialize(initialData));
    }

    @Override
    public void set(String key, String value) {
        database.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(database));
    }

    @Override
    public void unset(String key) {
        database.remove(key);
        Utils.writeFile(filePath, Utils.serialize(database));
    }

    @Override
    public String get(String key, String defaultValue) {
        String serializedData = Utils.readFile(filePath);
        Map<String, String> data = Utils.unserialize(serializedData);

        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String serializedData = Utils.readFile(filePath);
        return Utils.unserialize(serializedData);
    }
}
// END
