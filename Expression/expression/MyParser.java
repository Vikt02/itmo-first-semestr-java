package expression;

import java.lang.*;

public abstract class MyParser implements MyExpression {
    private final MyExpression value1;
    private final MyExpression value2;

    protected MyParser(MyExpression val1, MyExpression val2) {
        value1 = val1;
        value2 = val2;
    }

    @Override
    public int evaluate (int x) {
        return (makeMath(value1.evaluate(x), value2.evaluate(x)));
    }

    @Override
    public int evaluate (int x, int y, int z) {
        return (makeMath(value1.evaluate(x, y, z), value2.evaluate(x, y, z)));
    }

    @Override
    public String toString() {
        return "(" + value1.toString() +
                mathSign() +
                value2.toString() +
                ")";
    }

    public boolean equals(Object val) {
        if (val == null || val.getClass() != this.getClass()){
            return false;
        }

        MyParser otherParser = (MyParser) val;
        return value1.equals(otherParser.value1) && value2.equals(otherParser.value2);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int PRIME = 971;
        hash = hash * PRIME + value1.hashCode();
        hash = hash * PRIME + value2.hashCode();
        hash = hash * PRIME + this.getClass().hashCode();
        return hash;
    }

    @Override
    public abstract int makeMath(int val1, int val2);

    @Override
    public abstract String mathSign();
}