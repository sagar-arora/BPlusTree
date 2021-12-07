package code.bplustree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InternalNode<K extends Comparable<K>, V> extends Node<K, V> {

    public List<Node<K, V>> childrens = new ArrayList<>();

    public InternalNode(List<K> keys, List<Node<K, V>> childrens) {
        super(keys);
        this.childrens = childrens;
    }

    public InternalNode(List<K> keys) {
        super(keys);
        this.childrens = new ArrayList<>();
    }

    public InternalNode() {
        super(new ArrayList<>());
        this.childrens = new ArrayList<>();
    }

    public List<Node<K, V>> getChildrens() {
        return childrens;
    }

    public Pair<K, Node<K, V>> split() {
        int m = (int) Math.ceil((double) (BPlusTree.D - 1) / 2);

        Node<K, V> newNode = new InternalNode<>(keys.subList(m, keys.size()), childrens.subList(m, childrens.size()));
        keys.subList(m, keys.size()).clear();
        childrens.subList(m, keys.size()).clear();

        return new Pair<>(keys.get(0), newNode);
    }

    public Pair<K, Node<K, V>> insert(K key, V val) {
        Node<K,V> childNode = this.chooseSubtree(key);
        Pair<K, Node<K, V>> newChildNodePair = childNode.insert(key, val);
        K newChildPivot = newChildNodePair.getKey();
        Node<K, V> newChildNode = newChildNodePair.getVal();
        if (newChildNode != null) {
            if (isOverflow()) {
                Pair<K, Node<K, V>> newNodePair = this.split();
                K newNodePivot = newNodePair.key;
                InternalNode<K, V> newNode = (InternalNode<K, V>) newNodePair.val;
                // compare with the last key
                if (newChildPivot.compareTo(newNodePivot) < 0) {
                    int loc = Collections.binarySearch(keys, key);
                    this.keys.add(loc, newChildPivot);
                    this.childrens.add(loc, newChildNode);
                } else {
                    int loc = Collections.binarySearch(keys, key);
                    newNode.keys.add(loc, newChildPivot);
                    newNode.childrens.add(loc, newChildNode);
                }
                return newNodePair;
            } else {
                int loc = Collections.binarySearch(keys, key);
                this.keys.add(loc, newChildPivot);
                this.childrens.add(loc, newChildNode);
            }
        }
        return null;
    }

    public void delete(K key) {

    }

    public Node<K, V> search(K key) {
        Node<K, V> node = chooseSubtree(key);
        return node.search(key);
    }
}
