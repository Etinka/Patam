package test;

public class MainTrain {

	public static void main(String[] args) {
		if(!Expression.class.isAssignableFrom(Number.class))
			System.out.println("test.Number is not an test.Expression (-5)"); // -5
		if(!Expression.class.isAssignableFrom(BinaryExpression.class))
			System.out.println("test.BinaryExpression is not an test.Expression (-5)"); // -5
		
		if(!Expression.class.isAssignableFrom(Plus.class))
			System.out.println("test.Plus is not an test.Expression (-5)"); // -5
		if(!BinaryExpression.class.isAssignableFrom(Plus.class))
			System.out.println("test.Plus is not a test.BinaryExpression (-5)"); // -5
		
		try {
			BinaryExpression.class.getDeclaredField("left");
			BinaryExpression.class.getDeclaredField("right");
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println("test.BinaryExpression does not have correct fields (-10)"); // -10
		}
		
		System.out.println("done");
	}

}
