package test;

public class Q2 {

    public static double calc() {
        Minus minus = new Minus(new Number(3), new Number(4));
        Mul mul = new Mul(new Number(2), new Number(minus.calculate()));
        Div div = new Div(new Number(10), new Number(2));
        Plus plus = new Plus(new Number(div.calculate()), new Number(mul.calculate()));
        return plus.calculate();
    }
}
