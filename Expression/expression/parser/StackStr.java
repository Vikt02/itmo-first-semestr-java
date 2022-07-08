package expression.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackStr {

    public List <String> stack;

    StackStr() {
        stack = new ArrayList<>();
    }

    public void push(String val) {
        stack.add(val);
    }

    public void pop() {
        stack.remove(stack.size() - 1);
    }

    public String get() {
        return stack.get(stack.size() - 1);
    }

    public String get(int i) {
        return stack.get(i);
    }

    public int getSize() {
        return stack.size();
    }

    public void erase(int i) {
        stack.remove(i);
    }

    public void reverse() {
        Collections.reverse(stack);
    }
}
