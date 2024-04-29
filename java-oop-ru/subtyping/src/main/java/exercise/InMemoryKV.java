package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> db;

    public InMemoryKV(Map<String, String> db) {
        this.db = db;
    }
    public void swapKeyValue(String key, String value) {
        if (!db.containsKey(key) || !db.containsKey(value)) {
            return;
        }

        String temp = db.get(key);
        db.put(key, db.get(value));
        db.put(value, temp);
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
    public String get(String key, String defaultValue) {
        return db.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Map.copyOf(db);
    }
}
// END
