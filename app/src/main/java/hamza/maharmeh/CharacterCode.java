package hamza.maharmeh;

public class CharacterCode {
    private char character;
    private String code;
    public CharacterCode(char character, String code) {
        this.character = character;
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return character + ":" + code;
    }
}
