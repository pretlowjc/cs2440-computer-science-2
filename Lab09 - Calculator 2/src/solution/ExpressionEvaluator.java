package solution;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Stack;

/**
 * 
 * @author Justin Pretlow
 * @version April 15, 2020
 * 
 */
public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
            Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
    
    public Stack<Character> post = new Stack<Character>();
    public Stack<Double> answerStack = new Stack<Double>();
    
    
    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public String toPostfix(String expression)
    {
        // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        String postfixExpression = "";
        int numCount = 0;
        int charCount = 0;

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                postfixExpression += next + " ";
                numCount++;
            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);

                // TODO: do what you want to with a symbol
                // such as (, ), *, /, +, -
                if (symbol == '(')
                {
                	post.push(symbol);
                }
                else if (symbol != '(' && symbol != ')')
                {
                	while (!post.empty() && post.peek()
                			!= '(' && pemdas(symbol, post.peek()))
                	{
                		postfixExpression += post.pop() + " ";
                	}
                	post.push(symbol);
                	charCount++;
                }
                else if (symbol == ')')
                {
                	while (!post.empty() && post.peek() != ')')
                	{
                		postfixExpression += post.pop() + " ";
                	}
                	post.pop();
                }
            }
        }
        while (!post.empty())
        {
        	postfixExpression += post.pop() + " ";
        }
        postfixExpression = postfixExpression.trim();
        input.close();
        if (charCount != numCount - 1)
        {
        	postfixExpression = "error";
        }
        return postfixExpression;
    }

    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public double evaluate(String postfixExpression)
    {
        // other local variables you may need
        @SuppressWarnings("resource")
		Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        double answer = Double.NaN;
        double rightOp;
        double leftOp;
        double result;

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                answerStack.push(Double.parseDouble(next));
            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);

                // TODO: do what you want to with an operator
                // such as *, /, +, -
                if (operator == '+')
                {
                    rightOp = answerStack.pop();
                    leftOp = answerStack.pop();
                    result = leftOp + rightOp;
                    answerStack.push(result);
                }
                else if (operator == '-')
                {
                    rightOp = answerStack.pop();
                    leftOp = answerStack.pop();
                    result = leftOp - rightOp;
                    answerStack.push(result);
                }
                else if (operator == '*')
                {
                    rightOp = answerStack.pop();
                    leftOp = answerStack.pop();
                    result = leftOp * rightOp;
                    answerStack.push(result);
                }
                else if (operator == '/')
                {
                    rightOp = answerStack.pop();
                    leftOp = answerStack.pop();
                    result = leftOp / rightOp;
                    answerStack.push(result);
                }
            }
        }
        answer = answerStack.pop();
        return answer;
    }
    
    /**
     * This is the pemdas method.
     * @param symbol
     * @param stackSymbol
     * @return boolean
     */
    public boolean pemdas(char symbol, char stackSymbol)
    {
        if (stackSymbol == '*' || stackSymbol == '/')
        {
            return true;
        }
        else if ((stackSymbol == '+' 
            || stackSymbol == '-') && (symbol == '+' 
            || symbol == '-'))
        {
            return true;
        }
        return false;
    }
}
