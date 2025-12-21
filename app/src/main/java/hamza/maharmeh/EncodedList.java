package hamza.maharmeh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EncodedList {
    private ArrayList<CharacterFrequency> characters;
    private HashMap<Character,String> charToCode = new HashMap<>();
    private HashMap<String,Character> codeToChar = new HashMap<>();
    private ArrayList<CharacterCode> codesList = new ArrayList<>();
    private StringBuilder code = new StringBuilder("0");
    public EncodedList(ArrayList<CharacterFrequency> characters) {
        this.characters = characters;
        encode();
    }
    public EncodedList(String header) {
        encode(header);
    }
    public void encode() {
        for(int i = 0; i < characters.size(); i++){
            char c =  characters.get(i).getCharacter();
            charToCode.put(c,code.toString());
            codeToChar.put(code.toString(),c);
            codesList.add(new CharacterCode(c,code.toString()));
            code.deleteCharAt(code.length()-1);
            code.append("10");
        }
    }
    public void encode(String s) {
        String[] pairs = s.split(",");
        for(int i = 0; i < pairs.length; i++){
            String[] charCode = pairs[i].split(":");
            codeToChar.put(charCode[1],charCode[0].toCharArray()[0]);
        }
    }
    public String getEncoding(char c) {
        if(!charToCode.containsKey(c)){
            throw new NoSuchElementException("No encoded character for "+c);
        }
        return charToCode.get(c);
    }
    public char  getCharacter(String code){
        if(!codeToChar.containsKey(code)){
            throw new NoSuchElementException("No encoded character for "+code);
        }
        return codeToChar.get(code);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < codesList.size() - 1; i++){
            sb.append(codesList.get(i).toString());
            sb.append(",");
        }
        sb.append(codesList.getLast().toString());
        return sb.toString();
    }
}
