package hamza.maharmeh;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FileDecoder {

    private DataInputStream dataInputStream;
    private StringBuilder content;
    private HuffmanTree tree;
    public FileDecoder(InputStream stream)throws Exception{
        dataInputStream = new DataInputStream(stream);
        content =  new StringBuilder();
        String header = getHeader();
        tree = HuffmanTree.buildTree(getSortedQueue(header));
        decode();
    }
    private String getHeader() throws Exception {

        int size = getHeaderSize();
        StringBuilder sb = new StringBuilder();
        sb.append(dataInputStream.readUTF());
        return sb.toString();
    }
    private PriorityQueue<TreeNode> getSortedQueue(String header) {
        Comparator<TreeNode> comparator = Comparator.comparingInt(TreeNode::getWeight)
                                            .thenComparingInt(TreeNode::getCharacter)
                                            .thenComparing(TreeNode::getId);
        PriorityQueue<TreeNode> queue = new PriorityQueue<>(comparator);
        String[]split = header.split("\u001E");
        for(String str : split) {
            String[] keyValuePair = str.split("\u001F");
            queue.add(new TreeNode(keyValuePair[0].charAt(0),Integer.parseInt(keyValuePair[1])));
        }
        queue.add(TreeNode.createEndOfFileNode());
        return queue;
    }
    private void decode() throws Exception {
        TreeNode node = tree.getRoot();
        while (true) {
            byte b = dataInputStream.readByte();
            int mask = 128;
            for(int i = 0; i < 8; i++) {
                if((b & mask) != 0) {
                    node = node.getRight();
                }else {
                    node = node.getLeft();
                }

                if(node.isLeaf()) {
                    content.append(node.getCharacter());
                    node = tree.getRoot();
                }else if(node.isEndOfFile()) return;

                mask = mask >>> 1;
            }
        }
    }
    public Reader getReader() {
        return new StringReader(content.toString());
    }

    private int getHeaderSize() throws Exception {
        return dataInputStream.readInt();
    }

}
