import java.util.ArrayList;
import java.util.List;

public abstract class Node<K extends Comparable<K>, V> {

    public abstract Pair<K, Node<K, V>> insert(K key, V val);
    public abstract Node<K, V> search(K key);
    public boolean isLeafNode;

    public Node<K, V> chooseSubtree(K key) {
        if (this.isLeafNode) {
            return (LeafNode<K, V>) this;
        } else {
            InternalNode<K, V> internalNode = (InternalNode<K, V>) this;
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

    public List<K> keys;

    public Node() {
        this.keys = new ArrayList<>();
    }

    public Node(List<K> keys) {
        this.keys = keys;
    }

    public boolean isOverflow() {
        return keys.size() >= BPlusTree.D;
    }

    public List<K> getKeys() {
        return keys;
    }

    public int getSize() {
        return this.keys.size();
    }
}
