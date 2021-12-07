package code.bplustree;

import org.junit.Test;

public class LeafNodeTest {


    @Test
    public void testLeafNodeSplit1() {
        LeafNode<Integer, String> leafNode = BuildBPlusTree.createBTreeWithOneNodeOnlyWithFourKeys();
        BPlusTree.D = 4;
        Node<Integer, String> node = leafNode.split().getVal();

        for (Integer i : node.keys) {
            System.out.println(i);
        }
    }

    @Test
    public void testLeafNodeSplit2() {
        LeafNode<Integer, String> leafNode = BuildBPlusTree.createBTreeWithOneNodeOnlyWithThreeKeys();
        BPlusTree.D = 3;
        Node<Integer, String> node = leafNode.split().getVal();

        for (Integer i : node.keys) {
            System.out.println(i);
        }
    }
}
