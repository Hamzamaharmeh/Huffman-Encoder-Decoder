package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EncodedListTest {
    @Test
    public void testEncoding() {
        String s = "bananaa";
        EncodedList list = new EncodedList(new FrequencyList(s).getFrequencyList());
        assertEquals("0",list.getEncoding('a'));
        assertEquals("10", list.getEncoding('n'));
        assertEquals("110",list.getEncoding('b'));
    }
    @Test
    public void testNotIncludedCharacters() {
        String s = "bananaa";
        EncodedList list = new EncodedList(new FrequencyList(s).getFrequencyList());
        assertThrows(NoSuchElementException.class,() -> list.getEncoding('h'));
    }
}
