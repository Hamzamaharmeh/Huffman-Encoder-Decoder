package hamza.maharmeh;

import java.io.*;

public class FileReader {

    private DataInputStream dis;
    private EncodedList encodedList;
    private int charCount;
    private StringBuilder content;
    public FileReader(File file)throws Exception{
        dis = new DataInputStream(new FileInputStream(file));
        encodedList = new EncodedList(getHeader());
        content =  new StringBuilder();
        decode();
        FileWriter fw = new FileWriter(file);
        fw.write(content.toString());
    }
    private String getHeader() throws Exception {
        int size = getHeaderSize();
        charCount = dis.readInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(dis.readChar());
        }
        return sb.toString();
    }
    private void decode() throws Exception {
        StringBuilder currentCode = new StringBuilder();
        while (true) {
            byte b = dis.readByte();
            int mask = 128;
            for(int i = 0; i < 8; i++) {
                if((b & mask) != 0) {
                    currentCode.append('1');
                }else {
                    currentCode.append('0');
                    content.append(encodedList.getCharacter(currentCode.toString()));
                    currentCode.delete(0, currentCode.length());
                    if(charCount == 0) return;
                }
                mask = mask >>> 1;
            }
        }
    }
    private int getHeaderSize() throws Exception {
        return dis.readInt();
    }

}
