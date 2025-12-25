package hamza.maharmeh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class App {
    public static void main(String[] args)throws IOException {
        if(args.length != 2){
            System.err.println("Incorrect number of arguments");
            System.exit(1);
        }
        String flag = args[0];
        File  file = new File(args[1]);
        Path path = file.toPath();
        if(!file.exists()){
            System.err.println("File does not exist");
            System.exit(2);
        }

        if(flag.equals("-e")) {
            String input = fileToString(file);
            FrequencyList frequencyList = new FrequencyList(input);
            frequencyList.calculateFrequency();

            HuffmanTree tree = HuffmanTree.buildTree(frequencyList.getFrequencyQueue());

            EncodedList encodedList = new EncodedList(tree);

            try {
                FileEncoder encoder = new FileEncoder(frequencyList,encodedList,input);
                InputStream stream = encoder.getStream();
                Files.copy(stream,path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                System.err.println("Error encoding file");
                System.exit(3);
            }
        }else if(flag.equals("-d")) {
            try {
                FileDecoder decoder = new FileDecoder(new FileInputStream(file));
                Reader reader =  decoder.getReader();
                File tempFile = new File(file.getParent(),"temp.txt");
                Writer fileWriter = new FileWriter(tempFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                reader.transferTo(bufferedWriter);
                reader.close();
                bufferedWriter.close();
                Files.move(tempFile.toPath(),file.toPath(),StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                System.err.println("Error decoding file");
                System.exit(4);
            }
        }else {
            System.err.println("Unknown or no flag, use -d for decoding and -e for encoding");
            System.exit(5);
        }
    }
    private static String fileToString(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                sb.append(reader.readLine());
                sb.append('\n');
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
