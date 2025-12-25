package hamza.maharmeh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EncodedList {

    private HuffmanTree tree;
    private HashMap<Character,String> charToCode = new HashMap<>();
    public EncodedList(HuffmanTree tree) {
        this.tree = tree;
        encode(tree.getRoot(),"");
    }

    // NEED TO CHANGE THE STRING ARGUMENT
    private void encode(TreeNode node,String code) {
        if (node == null) return;
        if (node.isLeaf() || node.isEndOfFile()) {
            charToCode.put(node.getCharacter(), code);
        }
        encode(node.getLeft(), code + '0');
        encode(node.getRight(), code + '1');

    }
    public String getEncoding(Character c) {
        if(!charToCode.containsKey(c)){
            throw new NoSuchElementException("No encoded character for "+c);
        }
        return charToCode.get(c);
    }
    public char  getCharacter(String code){
        TreeNode node = tree.getRoot();
        for(int i = 0; i < code.length(); i++){
            char c = code.charAt(i);
            if(c == '0'){
                node = node.getLeft();
            }else {
                node = node.getRight();
            }
        }
        if(node != null) return node.getCharacter();
        else throw new NoSuchElementException("FUCK YOU NO CHARACTER HERE");
    }
}
