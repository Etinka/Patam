package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Q3 {
    public static double calc(String expression) {
        Queue<Object> queue = getQueue(expression);
        System.out.println("test: " + queue.peek() );
        double result = 0;
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
        return result;
    }

    private static Queue<Object> getQueue(String expression) {
        Queue<Object> queue = new LinkedBlockingQueue<>();
        Stack<Character> stack = new Stack<>();
        while (expression != null && expression.length() > 0) {
            if (!isOperatorOrBrackets(expression.charAt(0))) {
                int index = 0;
                while (expression.length() > index && !isOperatorOrBrackets(expression.charAt(index))) {
                    index++;
                }
                double num = Double.valueOf(expression.substring(0, index));
                queue.add(num);
                expression = expression.substring(index);
            } else {
                char c = expression.charAt(0);
                if (isOperator(c)) {
                    if (!stack.isEmpty() && isGreaterOperator(c, stack.peek())) {
                        queue.add(stack.pop());
                    } else {
                        stack.push(c);
                        expression = expression.substring(1);
                    }
                } else if (isOpenBracket(c)) {
                    stack.push(c);
                    expression = expression.substring(1);
                } else if (isCloseBracket(c)) {
                    if (!stack.isEmpty() && !isOpenBracket(stack.peek())) {
                        queue.add(stack.pop());
                    } else {
                        expression = expression.substring(1);
                        stack.pop();
                    }
                }

            }
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        return queue;
    }

    private static List<Object> getStringAsList(String expression) {
        List<Object> list = new ArrayList<>();
        while (expression != null && expression.length() > 0) {
            if (isOperatorOrBrackets(expression.charAt(0))) {
                list.add(expression.charAt(0));
                expression = expression.substring(1);
            } else {
                int index = 0;
                while (expression.length() > index && !isOperatorOrBrackets(expression.charAt(index))) {
                    index++;
                }
                double num = Double.valueOf(expression.substring(0, index));
                list.add(num);
                expression = expression.substring(index);
            }
        }
        return list;
    }

    private static boolean isGreaterOperator(char origin, char check) {
        return origin != '*' && origin != '/' && ((origin == '+' || origin == '-') && (check == '*' || check == '/'));
    }

    private static boolean isOperatorOrBrackets(char c) {
        return isOperator(c) || isCloseBracket(c) || isOpenBracket(c);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isOpenBracket(char c) {
        return c == '(';
    }

    private static boolean isCloseBracket(char c) {
        return c == ')';
    }
}
