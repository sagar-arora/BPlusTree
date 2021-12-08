package code.bplustree;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class InternalNodeTest {

    @Test
    public void testChooseSubtree1() {
        List<Integer> keys = ImmutableList.of(1, 11, 15, 19);
        List<Node<Integer, String>> childrens = ImmutableList.of(
                new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>());
        InternalNode<Integer, String> internalNode = new InternalNode<>(keys, childrens);
        Node<Integer, String> node = internalNode.chooseSubtree(3);
        Assert.assertEquals(childrens.get(1), node);
    }

    @Test
    public void testChooseSubtree2() {
        List<Integer> keys = ImmutableList.of(1, 11, 15, 19);
        List<Node<Integer, String>> childrens = ImmutableList.of(
                new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>());
        InternalNode<Integer, String> internalNode = new InternalNode<>(keys, childrens);
        Node<Integer, String> node = internalNode.chooseSubtree(21);
        Assert.assertEquals(childrens.get(4), node);
    }

    @Test
    public void testChooseSubtree3() {
        List<Integer> keys = ImmutableList.of(1, 11, 15, 19);
        List<Node<Integer, String>> childrens = ImmutableList.of(
                new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>(), new InternalNode<>());
        InternalNode<Integer, String> internalNode = new InternalNode<>(keys, childrens);
        Node<Integer, String> node = internalNode.chooseSubtree(18);
        Assert.assertEquals(childrens.get(3), node);
    }

    @Test
    public void testInsert1() {
        InternalNode<Integer, String> root = (InternalNode<Integer, String>)BuildBPlusTree.createBPlusTreeOfLevel3();
        root.insert(5, "5");
        InternalNode<Integer, String> internalNode1 = (InternalNode<Integer, String>) root.getChildrens().get(0);
        LeafNode leafNode = (LeafNode<Integer, String>)internalNode1.getChildrens().get(0);

        System.out.println(leafNode);
    }

    @Test
    public void testInsert2() {
        InternalNode<Integer, String> root = (InternalNode<Integer, String>)BuildBPlusTree.createBPlusTreeOfLevel3();
        root.insert(5, "5");
        InternalNode<Integer, String> internalNode1 = (InternalNode<Integer, String>) root.getChildrens().get(0);
        LeafNode<Integer, String> leafNode = (LeafNode<Integer, String>)internalNode1.getChildrens().get(0);

        for (int key: leafNode.keys) {
            System.out.print(key);
        }

        System.out.println();
        root.insert(6, "6");
        for (int key: leafNode.keys) {
            System.out.print(key);
        }
        System.out.println();
        for (int key: internalNode1.keys) {
            System.out.print(key);
        }
        //System.out.println(internalNode1);
    }


}
