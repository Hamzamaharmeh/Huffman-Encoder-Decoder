package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HuffmanTreeTest {
    @Test
    public void testHuffmanTree(){
        PriorityQueue<TreeNode> priorityQueue = new PriorityQueue<>((t1,t2) -> t1.getWeight()-t2.getWeight());
        priorityQueue.add(new TreeNode('a',5));
        priorityQueue.add(new TreeNode('b',10));
        priorityQueue.add(new TreeNode('c',20));
        HuffmanTree tree = HuffmanTree.buildTree(priorityQueue);
        TreeNode node = tree.getRoot();
        assertEquals('c', node.getLeft().getCharacter());
        node = node.getRight();
        assertEquals('a', node.getRight().getCharacter());
        assertEquals('b', node.getLeft().getCharacter());
    }
    @Test
    public void testOnEmpty(){
        PriorityQueue<TreeNode> priorityQueue = new PriorityQueue<>((t1,t2) -> t1.getWeight()-t2.getWeight());
        HuffmanTree tree = HuffmanTree.buildTree(priorityQueue);
        assertNull(tree.getRoot());
    }
}
