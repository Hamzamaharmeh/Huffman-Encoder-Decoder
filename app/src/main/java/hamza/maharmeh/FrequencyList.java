package hamza.maharmeh;

import java.util.*;

public class FrequencyList {

    private HashMap<Character,Integer>frequencyMap = new HashMap<>();
    private String input;
    public FrequencyList(String input) {
        this.input = input;
    }
    public void calculateFrequency() {
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int frequency = frequencyMap.getOrDefault(c,0)+1;
            frequencyMap.put(c,frequency);
        }
    }
    public PriorityQueue<TreeNode> getFrequencyQueue() {
        Comparator<TreeNode> comparator = Comparator.comparingInt(TreeNode::getWeight)
                                            .thenComparingInt(TreeNode::getCharacter)
                                            .thenComparing(TreeNode::getId);
        PriorityQueue<TreeNode> frequencyList = new PriorityQueue<>(comparator);
        for(Character cf : frequencyMap.keySet()) {
            frequencyList.add(new TreeNode(cf,frequencyMap.get(cf)));
        }
        frequencyList.add(TreeNode.createEndOfFileNode());
        return frequencyList;
    }
    @Override
    public String toString() {
        ArrayList<Map.Entry<Character,Integer>> sortedList = new ArrayList<>(frequencyMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        // \u001F and \u001E are invisible ascii seperators that don't appear in normal text
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sortedList.size() -1; i++) {
            sb.append(sortedList.get(i).getKey()).append("\u001F").append(sortedList.get(i).getValue());
            sb.append("\u001E");
        }
        if(!sortedList.isEmpty())
            sb.append(sortedList.getLast().getKey()).append("\u001F").append(sortedList.getLast().getValue());


        return sb.toString();
    }

}
