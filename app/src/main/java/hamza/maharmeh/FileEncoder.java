package hamza.maharmeh;

import java.io.*;
import java.nio.ByteBuffer;

public class FileEncoder {

    private EncodedList encodedList;
    private byte buffer;
    private int bitsTyped;
    private ByteArrayOutputStream byteArrayOutputStream;
    private FrequencyList frequencyList;
    private DataOutputStream dataOutputStream;
    private final String original;

    public FileEncoder(FrequencyList frequencyList, EncodedList encodedList, String original)throws Exception {

        this.frequencyList = frequencyList;
        this.encodedList = encodedList;
        this.original = original;
        buffer = 0;
        bitsTyped = 0;
        byteArrayOutputStream = new ByteArrayOutputStream();
        dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        writeHeader();
        writeEncodedData();

        if(bitsTyped != 0){
            buffer = (byte)(buffer << 8-bitsTyped);
            flushBuffer();
        }
    }
    private void writeHeader()throws Exception {
        String header = frequencyList.toString();
        dataOutputStream.writeInt(header.length());
        dataOutputStream.writeUTF(header);
    }

    private void writeEncodedData() throws Exception{
        for (int j = 0; j < original.length(); j++) {
            char current = original.charAt(j);
            String encoding = encodedList.getEncoding(current);
            for(int i = 0; i < encoding.length(); i++){

                buffer = (byte)(buffer << 1);
                buffer += (byte) (encoding.charAt(i) - '0');
                bitsTyped++;
                if(bitsTyped == 8){
                    flushBuffer();
                }
            }

        }
        // Encode the end of file
        String encoding = encodedList.getEncoding(null);
        for(int i = 0; i < encoding.length(); i++){
            buffer = (byte)(buffer << 1);
            buffer += (byte) (encoding.charAt(i) - '0');
            bitsTyped++;
            if(bitsTyped == 8){
                flushBuffer();
            }
        }
    }
    private void flushBuffer() throws Exception {
        dataOutputStream.write(buffer);
        buffer = 0;
        bitsTyped = 0;
    }
    public InputStream getStream() {
        byte[] array = byteArrayOutputStream.toByteArray();
        return new ByteArrayInputStream(array);
    }
}
