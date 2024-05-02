package exercise;

// BEGIN
import java.util.HashMap;
import java.util.Map;

class FileKV implements KeyValueStorage {

    private String path;
    private Map<String, String> data;

    public <V, K> FileKV(String s, HashMap<K, V> kvHashMap) {
    }

    private FileKV(String path, Map<String, String> initialData) {
        this.path = path;
        this.data = initialData;
        load();
    }

    private void load() {
        String json = Utils.readFile(path);
        data.putAll(Utils.unserialize(json));
    }

    void save() {
        String json = Utils.serialize(data);
        Utils.writeFile(path, json);
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
        save();
    }

    @Override
    public void unset(String key) {
        data.remove(key);
        save();
    }

    @Override
    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return data;
    }
}
// END
