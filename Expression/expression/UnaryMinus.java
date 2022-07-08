package expression;

import java.lang.*;

public class UnaryMinus implements MyExpression {

    MyExpression value;

    public UnaryMinus (MyExpression val) {
        value = val;
    }

    @Override
    public int evaluate(int x) {
        return -value.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -value.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        //System.out.println(value.toString());
        return "-(" + value.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnaryMinus that = (UnaryMinus) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return -value.hashCode();
    }

    @Override
    public int makeMath(int val1, int val2) {
        return 0;
    }

    @Override
    public String mathSign() {
        return null;
    }
}
