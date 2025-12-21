package hamza.maharmeh;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyList {
    private HashMap<Character,Integer>frequencyMap = new HashMap<>();
    ArrayList<CharacterFrequency> frequencyList;
    private String input;
    public FrequencyList(String input) {
        this.input = input;
        calculateFrequency();
    }
    private void calculateFrequency() {
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int frequency = frequencyMap.getOrDefault(c,0)+1;
            frequencyMap.put(c,frequency);
        }
    }
    public ArrayList<CharacterFrequency> getFrequencyList() {
        frequencyList = new ArrayList<>();
        for(Character cf : frequencyMap.keySet()) {
            frequencyList.add(new CharacterFrequency(cf,frequencyMap.get(cf)));
        }
        frequencyList.sort((t1,t2)->t2.getFrequency()-t1.getFrequency());
        return frequencyList;
    }
    public int frequencySum() {

        int sum = 0;
        for(CharacterFrequency cf : frequencyList) {
            sum += cf.getFrequency();
        }
        return sum;
    }
}
