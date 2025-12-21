package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrequencyListTest {

    @Test
    public void testDescendingOrdering() {
        FrequencyList list = new FrequencyList("bananaa");
        ArrayList<CharacterFrequency> frequencyList = list.getFrequencyList();
        for( int i = 0; i < frequencyList.size()-1; i++ ) {
            assertTrue(frequencyList.get(i).getFrequency() >= frequencyList.get(i+1).getFrequency());
        }
    }
    @Test
    public void testFrequencies() {
        FrequencyList list = new FrequencyList("bananaa");
        ArrayList<CharacterFrequency> frequencyList = list.getFrequencyList();
        assertEquals(4,frequencyList.get(0).getFrequency());
        assertEquals(2,frequencyList.get(1).getFrequency());
        assertEquals(1,frequencyList.get(2).getFrequency());
    }
    @Test
    public void testOnEmptyList() {
        FrequencyList list = new FrequencyList("");
        assertTrue(list.getFrequencyList().isEmpty());
    }
}
