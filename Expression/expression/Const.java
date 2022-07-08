package expression;

import java.lang.*;

public class Const implements MyExpression {

    private final int constant;

    public Const (int val) {
        constant = val;
    }

    public int evaluate (int x) {
        return constant;
    }

    public int evaluate (int x, int y, int z) {
        return constant;
    }

    public int makeMath(int val1, int val2) {
        return 0;
    }

    @Override
    public String toString() {
        return Integer.toString(constant);
    }

    @Override
    public String mathSign() {
        return null;
    }

    public boolean equals(Object val) {
        if(val == null || val.getClass() != this.getClass()) {
            return false;
        }
        Const otherConst = (Const) val;
        return constant == otherConst.constant;
    }

    @Override
    public int hashCode() {
        return constant;
    }

}