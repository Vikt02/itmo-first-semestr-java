package markup;

import java.util.*;

public abstract class ModifyElement {

    protected StringBuilder modify(List<MyText> entText, String simb) {
        StringBuilder sb = new StringBuilder("$");
        StringBuilder end = new StringBuilder("#");
        sb.append(simb);
        end.append(simb);
        for (var i : entText) {
            sb.append(i);
        }
        sb.append(end);
        
        return sb;
    }
}