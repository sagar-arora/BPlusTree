import java.util.ArrayList;
import java.util.List;

public abstract class Node<K, V> {
    /*
    Map<K, V> keyVals;
    */


    public abstract Node<K, V> insert(K key, V val);

    /*
    public abstract void delete(K key);

    public abstract Node<K, V> search(K Key);*/

    private List<K> keys = new ArrayList<>();
    public boolean isLeafNode = false;

    public boolean isOverflow() {
        return keys.size() >= BPlusTree.K;
    }

    public List<K> getKeys() {
        return keys;
    }

    public int getSize() {
        return this.keys.size();
    }
}
