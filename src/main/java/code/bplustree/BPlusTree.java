package code.bplustree;

import java.util.ArrayList;
import java.util.List;

public final class BPlusTree<K extends Comparable<K>, V> {

    Node<K, V> root;
    public static int D = 3;

    public BPlusTree(int size) {
        D = size;
        root = new LeafNode<>();
    }

    public BPlusTree(int size, Node<K, V> node) {
        D = size;
        root = node;
    }

    public void insert(K key, V val) {
        Pair<K, Node<K, V>> childNodePair = root.insert(key, val);

        if (childNodePair != null) {
            List<K> keys = new ArrayList<>();
            keys.add(childNodePair.getKey());
            List<Node<K, V>> childrens = new ArrayList<>();
            childrens.add(root);
            childrens.add(childNodePair.getVal());
            root = new InternalNode<>(keys, childrens);
        }
    }

    public V search (K key) {
        LeafNode<K, V> leafNode = (LeafNode<K, V>) root.search(key);

        List<KeyValue<K, V>> list = leafNode.values;

        for (KeyValue<K, V> keyValue : list) {
            K k = keyValue.key;
            if (key.equals(k)) {
                return keyValue.val;
            }
        }

        return null;
    }
}
