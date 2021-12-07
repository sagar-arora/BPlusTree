package code.bplustree;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {
    LeafNode<K, V> next;
    LeafNode<K, V> previous;
    List<KeyValue<K, V>> values = new ArrayList<>();

    LeafNode(List<K> keys, List<KeyValue<K, V>> values) {
        super(keys);
        this.values = values;
    }

    LeafNode(List<K> keys) {
        super(keys);
        List<KeyValue<K, V>> values = new ArrayList<>();
        for (K key : keys) {
            KeyValue<K, V> keyValue = new KeyValue<>(key, null);
            values.add(keyValue);
        }
        this.values = values;
    }

    LeafNode() {
        this(new ArrayList<K>(), new ArrayList<KeyValue<K, V>>());
    }

    @Override
    public List<K> getKeys() {
        return keys;
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
            K newNodeOffset = newNodePair.key;
            LeafNode<K,V> newNode = (LeafNode<K, V>) newNodePair.val;
            // compare with the last key
            if (key.compareTo(newNodeOffset) < 0) {
                this.values.add(keyValue);
                this.keys.add(key);
            } else {
                newNode.values.add(keyValue);
                newNode.keys.add(key);
            }

            return newNodePair;
        } else {
            int loc = Collections.binarySearch(keys, key);
            values.add(loc, keyValue);
            keys.add(loc, key);
            return null;
        }
    }


    public void delete(K key) {

    }


    public Node<K, V> search(K key) {
        return this;
    }



}

