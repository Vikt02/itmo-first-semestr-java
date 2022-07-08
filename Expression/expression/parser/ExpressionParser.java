package expression.parser;

import expression.*;

import java.util.Map;

public class ExpressionParser implements Parser {

    int maxiInd = 0;
    int deep = 0;
    int flag = 0;

    private final Map <Integer, String> col = Map.of(
            3, "*/",
            1, "Maxmin",
            2, "+-",
            0, "()",
            4, "--"
    );

    private ExpressionEnum notSpace (int i, String text) {
        char symbol = text.charAt(i);
        if (Character.isDigit(symbol)) {
            return ExpressionEnum.NUM;
        }

        if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/') {
            return ExpressionEnum.SIGN;
        }

        if ((symbol == 'x' && !(i > 0 && text.charAt(i - 1) == 'a')) || symbol == 'y' || symbol == 'z') {
            return ExpressionEnum.VAR;
        }

        if (symbol == '(') {
            return ExpressionEnum.OPENBRACK;
        }

        if (symbol == ')') {
            return ExpressionEnum.CLOSEBRACK;
        }

        if (symbol == 'm' && i + 1 < text.length()) {
            if (text.charAt(i + 1) == 'i') {
                return ExpressionEnum.MIN;
            } else {
                return ExpressionEnum.MAX;
            }
        }

        return ExpressionEnum.NAN;
    }

    private MyExpression doMath(boolean unaryMinus, boolean associativity, StackNum stackOfNum,
                                StackStr stackOfSign, String sign, MyExpression value1, MyExpression value2) {
        if (unaryMinus) {
            while (stackOfSign.getSize() > 0 && stackOfSign.get().equals("--")) {
                MyExpression value = (MyExpression) stackOfNum.get();
                stackOfNum.pop();
                stackOfSign.pop();
                stackOfNum.push(new UnaryMinus(value));
            }
            flag = 1;
            return (MyExpression) stackOfNum.get();
        }
        if (associativity) {
            MyExpression val = value1;
            value1 = value2;
            value2 = val;
        }
        //System.err.println(stackOfSign.get());
        switch (sign) {
            case "+" -> stackOfNum.push(new Add(value1, value2));
            case "-" -> stackOfNum.push(new Subtract(value1, value2));
            case "/" -> stackOfNum.push(new Divide(value1, value2));
            case "*" -> stackOfNum.push(new Multiply(value1, value2));
            case "Max" -> stackOfNum.push(new Maximum(value1, value2));
            case "min" -> stackOfNum.push(new Minimum(value1, value2));
            default -> {
                return (MyExpression) stackOfNum.get();
            }
        }
        flag = 2;
        //stackOfSign.pop();
        MyExpression val = (MyExpression) stackOfNum.get();
        stackOfNum.pop();
        return val;
    }

    private boolean lastCheck(boolean unaryMinus, boolean lastEntered, StringBuilder number, StackNum stackOfNum, StackStr stackOfSign) {

        if (unaryMinus && stackOfNum.getSize() > 0 && !lastEntered) {
            doMath(unaryMinus, false, stackOfNum, stackOfSign, "--",
                    (MyExpression) stackOfNum.get(), (MyExpression) stackOfNum.get());
            unaryMinus = false;
        }

        return unaryMinus;
    }

    private boolean checkNum(StringBuilder number, char ch, boolean unaryMinus, StackNum stackOfNum, StackStr stackOfSign) {
        if (!Character.isDigit(ch) && number.length() != 0) {
            //System.err.println(number);
            if (unaryMinus) {
                number = new StringBuilder("-" + number);
                stackOfSign.pop();
                stackOfNum.push(new Const(Integer.parseInt(number.toString())));
            } else {
                stackOfNum.push(new Const(Integer.parseInt(number.toString())));
            }
            while (stackOfSign.getSize() > 0 && stackOfSign.get().equals("--")) {
                MyExpression timeNum = (MyExpression) stackOfNum.get();
                stackOfNum.pop();
                stackOfNum.push(new UnaryMinus(timeNum));
                stackOfSign.pop();
            }
           // if(stackOfNum.getSize()>0)
            //System.err.println(stackOfNum.get());
            return true;
        }
        return false;
    }

    @Override
    public MyExpression parse (String text) {
        boolean unaryMinus = false;
        boolean lastEntered = true;
        StackNum stackOfNum = new StackNum();
        StackStr stackOfSign = new StackStr();

        //stackOfSign.push("(");
        //System.err.println(maxiInd);
        if(deep == 0) {
           //System.err.println(text + "///");
            maxiInd = 0;
        }
        int startIndex = maxiInd;

        StringBuilder number = new StringBuilder();
        for (int i = startIndex; i < text.length(); i++) {
            if(checkNum(number, text.charAt(i), unaryMinus, stackOfNum, stackOfSign)) {
                lastEntered = false;
                unaryMinus = false;
                number = new StringBuilder();
            }
            switch (notSpace(i, text)) {
                case MAX -> {
                    stackOfSign.push("Max");
                    lastEntered = true;
                }
                case MIN -> {
                    stackOfSign.push("min");
                    lastEntered = true;
                }
                case OPENBRACK -> {
                    deep++;
                    maxiInd = i + 1;
                    stackOfNum.push(this.parse(text));

                    i = maxiInd;
                    lastEntered = false;

                }
                case CLOSEBRACK -> {
                    deep--;
                    maxiInd = i;
                    i = text.length();
                }
                case NUM -> {
                    number.append(text.charAt(i));
                }
                case SIGN -> {
                    if (lastEntered){
                        unaryMinus = true;
                        stackOfSign.push("--");
                        break;
                    } else {
                        stackOfSign.push(String.valueOf(text.charAt(i)));
                    }
                    lastEntered = true;
                }
                case VAR -> {
                    stackOfNum.push(new Variable(String.valueOf(text.charAt(i))));
                    lastEntered = false;
                }
                case NAN -> {}
                default -> {
                }
            }

            unaryMinus = lastCheck(unaryMinus, lastEntered, number, stackOfNum, stackOfSign);
        }

        if(checkNum(number, ')', unaryMinus, stackOfNum, stackOfSign)) {
            lastEntered = false;
            unaryMinus = false;
            number = new StringBuilder();
        }

        unaryMinus = lastCheck(unaryMinus, lastEntered, number, stackOfNum, stackOfSign);

        //stackOfSign.reverse();
        //stackOfNum.reverse();
        //System.err.println(stackOfNum.getSize());
        while (stackOfNum.getSize() > 1) {
            //System.err.println(stackOfNum.getSize() + " " + unaryMinus);
            for (int j = 3; j >= 1; j--) {
                for (int i = 0; i < stackOfSign.getSize(); i++) {
                    if (col.get(j).contains(stackOfSign.get(i))) {
                        MyExpression valid = doMath(unaryMinus, false, stackOfNum, stackOfSign,
                                stackOfSign.get(i), (MyExpression) stackOfNum.get(i), (MyExpression) stackOfNum.get(i + 1));
                        //System.out.println(stackOfNum.getSize());
                        stackOfNum.erase(i + 1);
                        stackOfNum.into(i, valid);
                        //System.out.println(stackOfNum.getSize());
                        //System.out.println(valid);
                        stackOfSign.erase(i);
                        i--;
                    }
                }
            }
            //System.err.println(stackOfNum.getSize());
           /* if (flag == 2) {
                stackOfNum.pop();
            }
            //System.err.println(stackOfNum.getSize());
            stackOfNum.pop();
            stackOfSign.pop();
            stackOfNum.push(valid);*/
        }
        //System.err.println(stackOfNum.get().toString());
        return (MyExpression) stackOfNum.get();
       
    }

}
