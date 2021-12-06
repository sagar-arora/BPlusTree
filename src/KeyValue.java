import java.util.List;

public class KeyValue<K extends Comparable<K>, V> {
    K key;
    List<V> vals;
    public KeyValue(K key, List<V> vals) {
        this.key = key;
        this.vals = vals;
    }



}
