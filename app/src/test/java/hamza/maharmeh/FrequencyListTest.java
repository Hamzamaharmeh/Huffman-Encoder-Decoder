package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrequencyListTest {

    @Test
    public void testAcendingOrdering() {
        FrequencyList list = new FrequencyList("bananaa");
        PriorityQueue<TreeNode> frequencyList = list.getFrequencyQueue();
        int min = 0;
        for(TreeNode node : frequencyList){
            TreeNode n = frequencyList.poll();
            assertTrue(n.getWeight() >= min);
            min = n.getWeight();
        }
    }
    @Test
    public void testFrequencies() {
        FrequencyList list = new FrequencyList("bananaa");
        PriorityQueue<TreeNode> frequencyList = list.getFrequencyQueue();
        assertEquals(1,frequencyList.poll().getWeight());
        assertEquals(2,frequencyList.poll().getWeight());
        assertEquals(4,frequencyList.poll().getWeight());
    }
    @Test
    public void testOnEmptyList() {
        FrequencyList list = new FrequencyList("");
        assertTrue(list.getFrequencyQueue().isEmpty());
    }
}
