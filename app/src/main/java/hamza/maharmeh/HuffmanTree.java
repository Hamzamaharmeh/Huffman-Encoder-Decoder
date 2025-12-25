package hamza.maharmeh;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HuffmanTree {
    private TreeNode root;
    private HuffmanTree(TreeNode node) {
        root = node;
    }
    private HuffmanTree() {
        root = null;
    }
    public static HuffmanTree buildTree(PriorityQueue<TreeNode> nodesList) {
        if (nodesList.isEmpty()) return new  HuffmanTree();
        while(nodesList.size()>=2){
            TreeNode right = nodesList.poll();
            TreeNode left = nodesList.poll();
            TreeNode parent = new TreeNode(right,left);
            nodesList.add(parent);
        }
        return new HuffmanTree(nodesList.peek());
    }
    public TreeNode getRoot() {
        return root;
    }
}
