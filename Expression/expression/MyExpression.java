package expression;

import java.lang.*;

public interface MyExpression extends TripleExpression, Expression {
    int evaluate(int x);
    int evaluate(int x, int y, int z);

    //String toString(MyExpression val);

    int makeMath(int val1, int val2);
    @Override
    String toString();
    String mathSign();
    //boolean equals(MyExpression val);
    //int hashCode();
}
