package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> data = storage.toMap();
        Set<Entry<String, String>> entries = data.entrySet();
        for (Entry<String, String> entry : entries) {
            storage.unset(entry.getKey());
            storage.unset(entry.getValue());
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
