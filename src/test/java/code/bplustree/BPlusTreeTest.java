package code.bplustree;

import org.junit.Test;

public class BPlusTreeTest {

    @Test
    public void testSearch() {
        BPlusTree<Integer, String> bPlusTree = new BPlusTree(3, BuildBPlusTree.createBPlusTreeOfLevel3());
        String val = bPlusTree.search(10);
        System.out.println(val);
    }
}
