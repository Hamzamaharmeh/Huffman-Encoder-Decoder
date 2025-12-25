package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EncodedListTest {
    @Test
    public void testEncoding() {
        String s = "bananaa";
        FrequencyList flist = new FrequencyList("banannaaa");
        flist.calculateFrequency();
        EncodedList list = new EncodedList(HuffmanTree.buildTree(flist.getFrequencyQueue()));
        assertEquals("0",list.getEncoding('a'));
        assertEquals("10", list.getEncoding('n'));
    }
    @Test
    public void testNotIncludedCharacters() {
        String s = "bananaa";
        FrequencyList flist = new FrequencyList("bananaa");
        flist.calculateFrequency();
        EncodedList list = new EncodedList(HuffmanTree.buildTree(flist.getFrequencyQueue()));
        assertThrows(NoSuchElementException.class,() -> list.getEncoding('h'));
    }
}
