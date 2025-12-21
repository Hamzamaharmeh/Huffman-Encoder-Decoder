package hamza.maharmeh;

public class CharacterFrequency implements Comparable<CharacterFrequency> {
    private char character;
    private int frequency;
    public CharacterFrequency(char character,int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
    public void increaseFrequency() {
        frequency++;
    }
    public int getFrequency() {
        return frequency;
    }
    public char getCharacter() {
        return character;
    }
    @Override
    public int hashCode() {
        return character;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if(o == this) return true;
        if(!(o instanceof CharacterFrequency)) return false;
        CharacterFrequency c = (CharacterFrequency)o;
        if(c.character == this.character) return true;
        else return false;
    }
    @Override
    public int compareTo(CharacterFrequency o) {
        return frequency - o.frequency;
    }
    @Override
    public String toString() {
        return character + ":" + frequency;
    }
}
