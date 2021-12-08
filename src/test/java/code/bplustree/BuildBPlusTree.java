package code.bplustree;

import com.google.common.collect.ImmutableList;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;

public class BuildBPlusTree {

    public static LeafNode<Integer, String> createLeafNode(List<Integer> list) {
        List<KeyValue<Integer, String>> keyValueList = new ArrayList<>();

        for (Integer key : list) {
            keyValueList.add(new KeyValue<>(key, "" + key));
        }

        return new LeafNode<>(new ArrayList<>(list), keyValueList);
    }

    public static LeafNode<Integer, String> createBTreeWithOneNodeOnlyWithFourKeys() {
       // List<Integer> list = new ArrayList<>(ImmutableList.of(10, 15, 20, 25));

        return createLeafNode(ImmutableList.of(10, 15, 20, 25));
    }

    public static LeafNode<Integer, String> createBTreeWithOneNodeOnlyWithThreeKeys() {
        List<Integer> list = new ArrayList<>(ImmutableList.of(10, 15, 20));
        return new LeafNode<>(list);
    }

    public static InternalNode<Integer, String> createInternalNode() {
        InternalNode<Integer, String> internalNode1 = new InternalNode<>();
        internalNode1.keys = new ArrayList<>(ImmutableList.of(9, 11));
        return internalNode1;
    }

    public static Node<Integer, String> createBPlusTreeOfLevel3() {
        InternalNode<Integer, String> internalNode = new InternalNode<>();
        internalNode.keys = new ArrayList<>(ImmutableList.of(13));
        InternalNode<Integer, String> internalNode1 = new InternalNode<>();
        internalNode1.keys = new ArrayList<>(ImmutableList.of(9, 11));
        InternalNode<Integer, String> internalNode2 = new InternalNode<>();
        internalNode2.keys = new ArrayList<>(ImmutableList.of(16));
        //InternalNode<Integer, String> internalNode3 = new InternalNode<>();
        //internalNode1.keys = new ArrayList<>(ImmutableList.of(180, 200, 220));

        internalNode.childrens.add(internalNode1);
        internalNode.childrens.add(internalNode2);
        //internalNode.childrens.add(internalNode3);

        LeafNode<Integer, String> leafNode1 = createLeafNode(ImmutableList.of(1, 4));
        LeafNode<Integer, String> leafNode2 = createLeafNode(ImmutableList.of(9, 10));
        LeafNode<Integer, String> leafNode3 = createLeafNode(ImmutableList.of(11, 12));

        internalNode1.childrens.add(leafNode1);
        internalNode1.childrens.add(leafNode2);
        internalNode1.childrens.add(leafNode3);

        LeafNode<Integer, String> leafNode4 = createLeafNode(ImmutableList.of(13, 15));
        LeafNode<Integer, String> leafNode5 = createLeafNode(ImmutableList.of(16, 20, 25));

        internalNode2.childrens.add(leafNode4);
        internalNode2.childrens.add(leafNode5);
        //LeafNode<Integer, String> leafNode6 = createLeafNode(ImmutableList.of(51, 55, 69));

        /*
        LeafNode<Integer, String> leafNode7 = createLeafNode(ImmutableList.of(1, 10, 20));
        LeafNode<Integer, String> leafNode8 = createLeafNode(ImmutableList.of(31, 35, 49));
        LeafNode<Integer, String> leafNode9 = createLeafNode(ImmutableList.of(51, 55, 69));
        */
        return internalNode;
    }

}
