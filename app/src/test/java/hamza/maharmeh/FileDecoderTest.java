package hamza.maharmeh;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileDecoderTest {
    private boolean testEquality(String input) throws Exception {
        FrequencyList flist = new FrequencyList(input);
        flist.calculateFrequency();
        EncodedList encodedList = new EncodedList(HuffmanTree.buildTree(flist.getFrequencyQueue()));
        FileEncoder encoder = new FileEncoder(flist,encodedList,input);
        FileDecoder decoder = new FileDecoder(encoder.getStream());
        StringReader reader =(StringReader) decoder.getReader();
        char[] buffer = new char[input.length()];
        reader.read(buffer,0,buffer.length);
        return Arrays.equals(buffer, input.toCharArray());
    }
    @Test
    public void decodeTest()throws Exception{
        String input = "bbannnaaanaaaaa";
        assertTrue(testEquality(input));
    }
    @Test
    public void arabicEncodeTest()throws Exception{
        assertTrue(testEquality("حمزة"));
    }
    @Test
    public void commanEncodeTest()throws Exception{
        assertTrue(testEquality("ham,,ham"));
    }
}
