package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    protected Map<String, String> db;

    public InMemoryKV() {
        this(new HashMap<>());
    }

    public InMemoryKV(Map<String, String> initialData) {
        this.db = initialData;
    }

    @Override
    public String get(String key, String defaultValue) {
        return db.getOrDefault(key, defaultValue);
    }

    @Override
    public void set(String key, String value) {
        db.put(key, value);
    }

    @Override
    public void unset(String key) {
        db.remove(key);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(db);
    }

    public void swapKeyValue(String key, String value) {
        if (!db.containsKey(key) || !db.containsKey(value)) {
            return;
        }

        String temp = db.get(key);
        db.put(key, db.get(value));
        db.put(value, temp);
    }
}
// END
