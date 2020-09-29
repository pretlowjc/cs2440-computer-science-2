package solution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Justin Pretlow
 * @version April 15, 2020
 */

public class Calculator2
{
    private final static int DIMENSION = 400;

    JFrame calculatorFrame;
    
    JTextField infixExpression;
    
    JLabel equals;
    JLabel resultLabel;
    
    JButton calculateButton;
    JButton clearButton;
    
    JPanel inputPanel;
    JPanel buttonPanel;

    /**
     * This is the constructor.
     */
    public Calculator2()
    {
        calculatorFrame = new JFrame();
        calculatorFrame.setVisible(true);
        calculatorFrame.setLocation(100, 100);
        calculatorFrame.setSize(DIMENSION, DIMENSION);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setTitle("Calculator");
        initializeComponents();
        calculatorFrame.pack();
    }

    /**
     * This will initialize all the components of the calculator.
     */
    public void initializeComponents()
    {
        equals = new JLabel("=");
        equals.setName("equals");
        
        infixExpression = new JTextField(10);
        infixExpression.setName("infixExpression");
        
        resultLabel = new JLabel("");
        resultLabel.setName("resultLabel");
        
        calculateButton = new JButton("Calculate");
        calculateButton.setName("calculateButton");
        
        clearButton = new JButton("Clear");
        clearButton.setName("clearButton");

        addPanel();
        addActionListeners();
    }

    /**
     * This adds everything to the panel.
     */
    public void addPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        inputPanel = new JPanel();
        inputPanel.add(infixExpression);
        inputPanel.add(equals);
        inputPanel.add(resultLabel);
        
        calculatorFrame.add(inputPanel, BorderLayout.PAGE_START);
        calculatorFrame.add(buttonPanel, BorderLayout.PAGE_END);

    }
    /**
     * This will handle all action events.
     */
    public void addActionListeners()
    {
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {

                double result;
                String answer;

                ExpressionEvaluator calc = new ExpressionEvaluator();

                String postFix;
                postFix = calc.toPostfix(infixExpression.getText());

                if (!postFix.equals("error"))
                {
                    result = calc.evaluate(postFix);
                    answer = String.valueOf(result);
                    resultLabel.setText(answer);
                }
                else
                {
                    resultLabel.setText("error!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                infixExpression.setText("");
            }
        });
    }

    /**
     * This is the getter method for frame.
     * @return calculatorFrame This is the calculator's frame.
     */
    public JFrame getFrame()
    {
        return calculatorFrame;
    }
    
    /**
     * This is the main method.
     * @param 
     */
    public static void main(String[] args)
    {
        Calculator2 test = new Calculator2();
    }
}
