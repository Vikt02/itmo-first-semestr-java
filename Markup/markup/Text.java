package markup;

public class Text implements MyText {
    StringBuilder sb;
    
    Text(String str) {
        sb = new StringBuilder(str);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}