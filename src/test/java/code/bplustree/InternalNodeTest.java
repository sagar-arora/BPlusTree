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


}
