package expression;

public class Maximum extends MyParser {

    public Maximum (MyExpression value1, MyExpression value2) {
        super(value1, value2);
    }

    @Override
    public int makeMath(int val1, int val2) {
        return (Math.max(val1, val2));
    }

    @Override
    public String mathSign() {
        return " max ";
    }
}
