package hamza.maharmeh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) {
        String flag = args[0];
        if(flag.equals("-e")) {
            File file = new File(args[1]);
            if(file.exists()) {
                String content = fileToString(file);
                FrequencyList frequency = new FrequencyList(content);
                EncodedList encoding = new EncodedList(frequency.getFrequencyList());
                try {
                    FilePrinter printer = new FilePrinter(frequency.frequencySum(),content,file,encoding);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.exit(1);
            }
        }else if(flag.equals("-d")) {
            File file = new  File(args[1]);

        }
    }
    private static String fileToString(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
