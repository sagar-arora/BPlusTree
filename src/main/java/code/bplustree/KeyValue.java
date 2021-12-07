package code.bplustree;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KeyValue<K extends Comparable<K>, V> {
    K key;
    V val;
    public KeyValue(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
