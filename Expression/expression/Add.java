package expression;

import java.lang.*;

public class Add extends MyParser {

    public Add (MyExpression val1, MyExpression val2) {
        super(val1, val2);
    }

    @Override
    public int makeMath (int val1, int val2) {
        return val1 + val2;
    }

    @Override
    public String mathSign() {
        return " + ";
    }

}