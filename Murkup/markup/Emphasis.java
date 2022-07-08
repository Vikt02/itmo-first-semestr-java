package markup;

import java.util.*;

public class Emphasis extends ModifyElement implements MyText {
    StringBuilder sb;
    
    Emphasis(List<MyText> text) {
        sb = super.modify(text, "emph");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
