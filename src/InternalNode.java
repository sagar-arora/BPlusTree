import java.util.ArrayList;
import java.util.List;

public class InternalNode<K extends Comparable<K>, V> extends Node<K, V> {
    public List<Node<K, V>> childrens = new ArrayList<>();
    public List<Node<K, V>> getChildrens() {
        return childrens;
    }

    public Node<K, V> insert(K key, V val) {
        Node<K,V> childNode = this.chooseSubtree(key);
        Node<K, V> newNode = childNode.insert(key, val);
        newNode.keys.get(0);
        if (newNode != null) {


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
