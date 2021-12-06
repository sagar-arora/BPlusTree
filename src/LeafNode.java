import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {
    LeafNode<K, V> next;
    LeafNode<K, V> previous;
    List<KeyValue<K, V>> values = new ArrayList<>();
    //List<K> keys = new ArrayList<>();

    LeafNode(List<KeyValue<K, V>> values, List<K> keys) {
        super(keys);
        this.values = values;
    }

    LeafNode() {
        this.values = new ArrayList<>();
    }

    @Override
    public List<K> getKeys() {
        return keys;
    }


    public Node<K, V> split() {
        int m = (int) Math.ceil((double) (BPlusTree.D - 1) / 2);

        Node<K, V> newNode = new LeafNode<>(values.subList(m, values.size()), keys.subList(m, keys.size()));
        values.subList(m, values.size()).clear();
        keys.subList(m, keys.size()).clear();

        return newNode;
    }

    public Node<K, V> insert(K key, V val) {
        KeyValue<K, V> keyValue = new KeyValue<>(key, val);

        if (isOverflow()) {
            LeafNode<K, V> newNode = (LeafNode<K, V>) split();
            // compare with the last key
            if (key.compareTo(this.keys.get(keys.size() - 1)) < 0) {
                this.values.add(keyValue);
                this.keys.add(key);
            } else {
                newNode.values.add(keyValue);
                newNode.keys.add(key);
            }

            return newNode;
        } else {
            int loc = Collections.binarySearch(keys, key);
            values.add(loc, keyValue);
            keys.add(loc, key);
            return null;
        }
    }


    public void delete(K key) {

    }


  /*  public int search(K key) {
        int val = Collections.binarySearch(keys, key);
        return val;
    }*/

    public Node<K, V> search(K key) {
        return this;
    }



}
