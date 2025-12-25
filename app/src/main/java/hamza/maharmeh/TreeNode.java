package hamza.maharmeh;

public class TreeNode {

    private static int counter = 0;
    private final int id = counter++;
    private TreeNode right;
    private TreeNode left;
    private int weight;
    private Character character;
    private NodeType nodeType;
    public TreeNode (TreeNode right, TreeNode left) {
        this.right = right;
        this.left = left;
        this.weight = right.getWeight() + left.getWeight();
        character = '.';
        nodeType = NodeType.INTERNAL;
    }
    public TreeNode(char character, int weight) {
        this.character = character;
        this.weight = weight;
        right = null;
        left = null;
        nodeType = NodeType.LEAF;
    }
    private TreeNode() {
        character =null;
        weight = 0;
        right = null;
        left = null;
        nodeType = NodeType.END_OF_FILE;
    }
    public boolean isLeaf() {
        if(nodeType == NodeType.LEAF)return true;
        return false;
    }
    public boolean isEndOfFile() {
        if(nodeType == NodeType.END_OF_FILE)return true;
        return false;
    }
    public TreeNode getRight() {
        return right;
    }
    public TreeNode getLeft() {
        return left;
    }
    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public Character getCharacter() {
        return character;
    }
    public static TreeNode createEndOfFileNode() {
        return new TreeNode();
    }
}
