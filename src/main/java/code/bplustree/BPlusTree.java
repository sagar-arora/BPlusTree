package code.bplustree;

import java.util.ArrayList;
import java.util.List;

public class BPlusTree<K extends Comparable<K>, V> {

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

/*    public List<V> search(K key) {
        Node<K, V> node = root.search(key);
        if (node == null) {
            return null;
        } else {
            assert node.isLeafNode;
            LeafNode<K,V> leafNode = (LeafNode<K, V>) node;

        }
        return null;
    }*/

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

    /*public List<V> search(K key) {
        Node<K, V> node = (Node<K, V>) search(root, key);
        if (node == null) {
            return null;
        } else {
            assert node.isLeafNode;
            LeafNode<K,V> leafNode = (LeafNode<K, V>) node;
            //int index = leafNode.search(key);
            //leafNode
        }
        return null;
    }*/

    public Node<K, V> chooseSubtree(Node<K, V> node, K key) {
        if (node.isLeafNode) {
            return node;
        } else {
            InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
            List<K> keys = internalNode.getKeys();
            K firstKey = keys.get(0);
            K lastKey = keys.get(keys.size() - 1);
            Node<K, V> ans = null;
            if (key.compareTo(firstKey) < 0) {
                ans = internalNode.getChildrens().get(0);
            } else if (key.compareTo(lastKey) > 0) {
                ans = internalNode.getChildrens().get(internalNode.getChildrens().size() - 1);
            } else {
                for (int i = 1; i < internalNode.getKeys().size(); i++) {
                    if (key.compareTo(internalNode.getKeys().get(i - 1)) > 0
                            && key.compareTo(internalNode.getKeys().get(i)) < 0) {
                        ans = internalNode.getChildrens().get(i);
                    }
                }
            }
            return ans;
        }
    }

    public Node<K, V> search(Node<K, V> node, K key) {
        if (node.isLeafNode) {
            return node;
        } else {
            InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
            List<K> keys = internalNode.getKeys();
            K firstKey = keys.get(0);
            K lastKey = keys.get(keys.size() - 1);

            if (key.compareTo(firstKey) < 0) {
                return search(internalNode.getChildrens().get(0), key);
            } else if (key.compareTo(lastKey) > 0) {
                return search(internalNode.getChildrens().get(internalNode.getChildrens().size() - 1), key);
            } else {
                for (int i = 1; i < internalNode.getKeys().size(); i++) {
                    if (key.compareTo(internalNode.getKeys().get(i - 1)) > 0
                            && key.compareTo(internalNode.getKeys().get(i)) < 0) {
                        return search(internalNode.getChildrens().get(i), key);
                    }
                }
            }
            return null;
        }
    }
}
