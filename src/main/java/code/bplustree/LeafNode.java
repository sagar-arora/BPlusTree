package code.bplustree;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {
    LeafNode<K, V> next;
    LeafNode<K, V> previous;
    List<KeyValue<K, V>> values = new ArrayList<>();

    public LeafNode(List<K> keys, List<KeyValue<K, V>> values) {
        super(keys);
        this.values = values;
    }

    public LeafNode(List<K> keys) {
        super(keys);
        List<KeyValue<K, V>> values = new ArrayList<>();
        for (K key : keys) {
            KeyValue<K, V> keyValue = new KeyValue<>(key, null);
            values.add(keyValue);
        }
        this.values = values;
    }

    public LeafNode() {
        this(new ArrayList<K>(), new ArrayList<KeyValue<K, V>>());
    }

    @Override
    public List<K> getKeys() {
        return keys;
    }

    void insertEntry(K key, V val) {
        int loc = Collections.binarySearch(this.keys, key);
        // duplicate entry, update value.
        if (loc > 0) {
            this.values.remove(loc);
            this.keys.add(key);
            this.values.add(loc, new KeyValue<>(key, val));
        } else {
            loc = -1 * (loc + 1);
            this.keys.add(loc, key);
            this.values.add(loc, new KeyValue<>(key, val));
        }
    }

    public Pair<K, Node<K, V>> split() {
        int m = (int) Math.ceil((double) (BPlusTree.D - 1) / 2);

        LeafNode<K, V> newNode = new LeafNode<>(new ArrayList<>(keys.subList(m, keys.size())),
                new ArrayList<>(values.subList(m, values.size())));
        values.subList(m, values.size()).clear();
        keys.subList(m, keys.size()).clear();

        LeafNode<K,V> nextNode = this.next;

        newNode.next = nextNode;
        newNode.previous = this;
        if (nextNode != null)
            nextNode.previous = newNode;
        this.next = newNode;

        return new Pair<>(newNode.keys.get(0), newNode);
    }


    public Pair<K, Node<K, V>> insert(K key, V val) {
        KeyValue<K, V> keyValue = new KeyValue<>(key, val);

        if (isOverflow()) {
            Pair<K, Node<K,V>> newNodePair = this.split();
            K newNodeOffset = newNodePair.getKey();
            LeafNode<K,V> newNode = (LeafNode<K, V>) newNodePair.getVal();
            // compare with the last key
            if (key.compareTo(newNodeOffset) < 0) {
                this.insertEntry(key, val);
            } else {
                newNode.insertEntry(key, val);
            }
            return newNodePair;
        } else {
            this.insertEntry(key, val);
            return null;
        }
    }


    public void delete(K key) {

    }


    public Node<K, V> search(K key) {
        return this;
    }



}

