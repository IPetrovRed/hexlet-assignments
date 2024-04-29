package exercise;

// BEGIN
import java.util.Map;
import java.util.Map.Entry;
import java.util.Map;

public class FileKV implements KeyValueStorage {

    private final String filePath;
    private Map<String, String> db;

    public FileKV(String filePath, Map<String, String> db) {
        this.filePath = filePath;
        this.db = db;
        load();
    }

    private void load() {
        String data = Utils.readFile(filePath);
        if (!data.isEmpty()) {
            db = Utils.unserialize(data);
        }
    }

    private void save() {
        Utils.writeFile(filePath, Utils.serialize(db));
    }

    @Override
    public void set(String key, String value) {
        db.put(key, value);
        save();
    }

    @Override
    public void unset(String key) {
        db.remove(key);
        save();
    }

    @Override
    public String get(String key, String defaultValue) {
        return db.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Map.copyOf(db);
    }
}
// END
