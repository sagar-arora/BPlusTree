import java.util.List;

public class BPlusTree<K extends Comparable<K>, V> {

    Node<K, V> root;
    public static int K = 3;

    public BPlusTree(int size) {
        K = size;
        root = new LeafNode<K, V>();
    }

    public void insert(K key, V val) {
        Node<K,V> node = search(this.root, key);
        if (node.isLeafNode) {
            if (node.getSize() >= 2*K) {

            }
        } else {
            if (node.getSize() >= 2*K) {
                Node<K,V> newNode = null;
            } else {

            }
        }

        if (node != null) {
            root = node;
        }
    }

    public List<V> search(K key) {
        Node<K, V> node = search(root, key);
        if (node == null) {
            return null;
        } else {
            assert node.isLeafNode;
            LeafNode<K,V> leafNode = (LeafNode<K, V>) node;
            int index = leafNode.search(key);
            //leafNode
        }
        return null;
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

    public static void main(String[] args) {

    }
}
