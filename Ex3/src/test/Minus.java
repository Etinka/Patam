package test;

public class Minus extends BinaryExpression {

    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double calculate() {
        double val = left.calculate() - right.calculate();
        return Math.round(val * 100000d) / 100000d;
    }
}
