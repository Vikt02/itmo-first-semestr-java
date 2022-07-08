package markup;

import java.util.*;

public class Strikeout extends ModifyElement implements MyText {
    StringBuilder sb;
    
    Strikeout(List<MyText> text) {
        sb = super.modify(text, "stri");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
