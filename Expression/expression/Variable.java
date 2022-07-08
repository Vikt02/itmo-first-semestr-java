package expression;

import java.lang.*;

public class Variable implements MyExpression {

    String var;

    public Variable (String str) {
        var = str;
        //System.err.println("Builder " + this.getClass() + " " + this.toString());
    }

    public int evaluate (int x) {
        return x;
    }

    public int evaluate (int x, int y, int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
    }

    public int makeMath(int val1, int val2) {
        return 0;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public String mathSign() {
        return "";
    }

    @Override
    public boolean equals(Object val) {
        //System.err.println(val.toString() + " " + this.toString());
        if (val == null || val.getClass() != this.getClass()){
            return false;
        }

        Variable otherVariable = (Variable) val;
        return var.equals(otherVariable.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }
}