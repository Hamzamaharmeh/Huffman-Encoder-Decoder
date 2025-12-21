package hamza.maharmeh;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FilePrinter {
    private File file;
    private EncodedList encodedList;
    private int buffer = 0;
    private int bitsTyped = 0;
    DataOutputStream dos;
    private final String original;
    private int charCount;
    public FilePrinter(int charCount, String original,File file,EncodedList list)throws Exception {

        this.charCount = charCount;
        this.file = file;
        this.original = original;
        this.encodedList = list;
        dos = new DataOutputStream(new FileOutputStream(file));
        writeHeader();
        writeEncodedData();
        if(bitsTyped != 0){
            buffer = buffer << 8-bitsTyped;
            flushBuffer();
        }
    }
    private void writeHeader()throws Exception {
        String header = encodedList.toString();
        dos.writeInt(header.length());
        dos.writeInt(charCount);
        dos.writeChars(header);
    }
    private void writeEncodedData() throws Exception{
        for (int j = 0; j < original.length(); j++) {
            char current = original.charAt(j);
            String encoding = encodedList.getEncoding(current);
            for(int i = 0; i < encoding.length(); i++){
                buffer = buffer << 1;
                buffer += encoding.charAt(i) - '0';
                bitsTyped++;
                if(bitsTyped == 8){
                    flushBuffer();
                }
            }
        }
    }
    private void flushBuffer() throws Exception {
        dos.write(buffer);
        buffer = 0;
        bitsTyped = 0;
    }
}
