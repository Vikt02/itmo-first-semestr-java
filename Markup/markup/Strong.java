package markup;

import java.util.*;

public class Strong extends ModifyElement implements MyText {
    StringBuilder sb;
    
    Strong(List<MyText> text) {
        sb = super.modify(text, "Stro");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
