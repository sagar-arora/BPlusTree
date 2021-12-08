package code.bplustree;

import java.util.*;

public class InternalNode<K extends Comparable<K>, V> extends Node<K, V> {

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

        K pivotKey = keys.get(m);
        keys.remove(m);
        Node<K, V> newNode = new InternalNode<>(new ArrayList<>(keys.subList(m, keys.size())),
                new ArrayList<>(childrens.subList(m + 1, childrens.size())));


        keys.subList(m, keys.size()).clear();
        childrens.subList(m + 1, childrens.size()).clear();
        return new Pair<>(pivotKey, newNode);
    }

    public void insertNode(K newChildPivot, Node<K, V> newChildNode) {
        int loc = Collections.binarySearch(this.keys, newChildPivot);
        loc = loc < 0 ? -1 * (loc + 1) : loc;
        this.keys.add(loc, newChildPivot);
        this.childrens.add(loc + 1, newChildNode);
    }

    public Pair<K, Node<K, V>> insert(K key, V val) {
        Node<K,V> childNode = this.chooseSubtree(key);
        Pair<K, Node<K, V>> newChildNodePair = childNode.insert(key, val);
        if (newChildNodePair != null) {
            K newChildPivot = newChildNodePair.getKey();
            Node<K, V> newChildNode = newChildNodePair.getVal();
            if (isOverflow()) {
                Pair<K, Node<K, V>> newNodePair = this.split();
                K newNodePivot = newNodePair.key;
                InternalNode<K, V> newNode = (InternalNode<K, V>) newNodePair.val;
                // compare with the last key
                if (newChildPivot.compareTo(newNodePivot) < 0) {
                   this.insertNode(newChildPivot, newChildNode);
                } else {
                    newNode.insertNode(newChildPivot, newChildNode);
                }
                return newNodePair;
            } else {
                this.insertNode(newChildPivot, newChildNode);
                return null;
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

/*    public String toString() {
        return "[" + String.join(",", keys.stream().map(x -> x.toString()).toList()) + "]";
    }*/

    public void printTree() {
        String val = this.toString();
        System.out.println(val);
        Queue<Node<K, V>> queue = new LinkedList<>(this.getChildrens());
        int count = 0;
        while (!queue.isEmpty()) {
            System.out.println("level " + (++count));
            int sze = queue.size();
            for (int i = 0; i < sze; i++) {
                Node<K, V> node = queue.poll();
                System.out.print(node.toString());
                System.out.print(" ");
                for (Node<K, V> children : node.childrens) {
                    queue.offer(children);
                }
            }
            System.out.println();
        }

        //count++;
    }
}
