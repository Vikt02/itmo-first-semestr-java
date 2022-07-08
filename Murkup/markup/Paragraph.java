package markup;

import java.util.*;

public class Paragraph implements MyText {
    StringBuilder sb;

    Paragraph(List<MyText> text) {
        sb = new StringBuilder();
        for (var i : text) {
            sb.append(i);
        }
    }

    private StringBuilder getMarkdown(StringBuilder qest, int i) {
        //System.out.println(sb.charAt(i + 1));
        if (sb.charAt(i + 1) == ('e')) {
            qest.append("*");
        } else if (sb.charAt(i + 1) == ('S')) {
            qest.append("__");
        } else if (sb.charAt(i + 1) == ('s')) {
            qest.append("~");
        }
        return qest;
    }

    private StringBuilder getHtmlOpen(StringBuilder qest, int i) {
        if (sb.charAt(i + 1) == ('e')) {
            qest.append("<em>");
        } else if (sb.charAt(i + 1) == ('S')) {
            qest.append("<strong>");
        } else if (sb.charAt(i + 1) == ('s')) {
            qest.append("<s>");
        }
        return qest;
    }

    private StringBuilder getHtmlClose(StringBuilder qest, int i) {

        if (sb.charAt(i + 1) == ('e')) {
            qest.append("</em>");
        } else if (sb.charAt(i + 1) == ('S')) {
            qest.append("</strong>");
        } else if (sb.charAt(i + 1) == ('s')) {
            qest.append("</s>");
        }
        return qest;
    }

    public void toMarkdown(StringBuilder qest) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ('$') || sb.charAt(i) == ('#')) {
                qest = getMarkdown(qest, i);
                i += 4;
            } else {
                qest.append(sb.charAt(i));
            }

        }
    }

    public void toHtml(StringBuilder qest) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ('$')) {
                qest = getHtmlOpen(qest, i);
                i += 4;
            } else if (sb.charAt(i) == ('#')) {
                qest = getHtmlClose(qest, i);
                i+= 4;
            } else {
                qest.append(sb.charAt(i));
            }
        }
    }
}