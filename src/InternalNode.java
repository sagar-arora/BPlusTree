import java.util.ArrayList;
import java.util.List;

public class InternalNode<K, V> extends Node<K, V> {
    public List<Node<K, V>> childrens = new ArrayList<>();


    public List<Node<K, V>> getChildrens() {
        return childrens;
    }

    public Node<K, V> insert(K key, V val) {
        return null;
    }

    public void delete(K key) {

    }

    public Node<K, V> search(K Key) {
        return null;
    }
}
