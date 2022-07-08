package expression;

import java.lang.*;

public class Divide extends MyParser {

    public Divide (MyExpression val1, MyExpression val2) {
        super(val1, val2);
        //System.err.println(this.toString());
        //System.err.println("Builder " + this.getClass() + " " + this.toString());
    }

    @Override
    public int makeMath (int val1, int val2) {
        return val1 / val2;
    }

    @Override
    public String mathSign() {
        return " / ";
    }
}