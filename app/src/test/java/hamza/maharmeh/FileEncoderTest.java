package hamza.maharmeh;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileEncoderTest {
   @Test
    public void testEncode() throws Exception {
         String input = "bbannnaaanaaaaa";
         FrequencyList flist = new FrequencyList(input);
         flist.calculateFrequency();
         EncodedList elist = new EncodedList(HuffmanTree.buildTree(flist.getFrequencyQueue()));
         FileEncoder encoder = new FileEncoder(flist,elist,input);
         byte[] expected = {(byte)0xD9,(byte)0x50,(byte)0x81,(byte)0xC0};
         byte[] stream = encoder.getStream().readAllBytes();
         byte[] result = new byte[4];
         for(int i = 0; i < 4; i++) {
            result[i] = stream[stream.length - (4-i) ];
         }
         assertArrayEquals(expected,result);
      }
}
