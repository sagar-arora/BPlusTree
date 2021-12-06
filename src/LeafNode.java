import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {
    LeafNode<K, V> next;
    LeafNode<K, V> previous;
    List<KeyValue<K, V>> values = new ArrayList<>();
    List<K> keys = new ArrayList<>();

    public Node<K, V> insert(K key, V val) {
        return null;
    }


    public void delete(K key) {

    }


    public int search(K key) {
        int val = Collections.binarySearch(keys, key);
        return val;
    }



}
