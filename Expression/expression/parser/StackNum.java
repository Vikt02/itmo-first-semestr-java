package expression.parser;

import expression.MyExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackNum {

    public List <MyExpression> stack;

    StackNum() {
        stack = new ArrayList<>();
    }

    public void push(MyExpression val) {
        stack.add(val);
    }

    public void pop() {
        stack.remove(stack.size() - 1);
    }

    public Object get() {
        return stack.get(stack.size() - 1);
    }

    public Object get(int i) {
        return stack.get(i);
    }

    public int getSize() {
        return stack.size();
    }

    public void reverse() {
        Collections.reverse(stack);
    }

    public void erase(int i) {
        stack.remove(i);
    }

    public void into(int i, MyExpression value) {
        stack.set(i, value);
    }
}
