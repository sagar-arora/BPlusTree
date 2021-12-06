import java.util.List;

public class KeyValue<K extends Comparable<K>, V> {
    K key;
    V val;
    public KeyValue(K key, V val) {
        this.key = key;
        this.val = val;
    }



}
