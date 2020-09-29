package solution;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Justin Pretlow
 * @date February 13, 2020
 *
 */

public class Calculator
{
    private JFrame calculatorFrame;
    private JLabel jlResult;
    private JTextField jtfTextField;
    JTextField leftOperand;
    JTextField rightOperand;
    
    /**
     * Getter for calculatorFrame.
     * @return calculatorFrame This is the calculator's frame.
     */
    public JFrame getFrame()
    {
        return calculatorFrame;
    }
    
    /**
     * Getter for result label.
     * @return jlResult This is the result's label.
     */
    public JLabel getJLResult()
    {
        return jlResult;
    }
    
    /**
     * Getter for text field.
     * @return jtfTextField This is the text field.
     */
    public JTextField getJTFTextField()
    {
        return jtfTextField;
    }
    
    /**
     * no-arg constructor.
     */
    public Calculator()
    {
        calculatorFrame = new JFrame();
        calculatorFrame.setLocation(100,100);
        calculatorFrame.setSize(400,400);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setTitle("My Simple Calculator");
               
        initializeComponents();
        
        calculatorFrame.pack();
        calculatorFrame.setVisible(true);        
    }
    
    /**
     * buttonPanel method which declares and initializes all buttons.  
     * addActionListeners for the buttons are here as well.
     */
    public void buttonPanel()
    {
        JPanel buttonPanel = new JPanel();
        
        JButton addButton = new JButton("ADD"); 
        buttonPanel.add(addButton);
        addButton.setName("addButton");
        
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                try
                {
                    double num1 = 0;
                    num1 = Double.parseDouble(leftOperand.getText());
                    double num2 = 0;
                    num2 = Double.parseDouble(rightOperand.getText());
                    
                    if (num1 != 0 && num2 != 0)
                    {
                        double result = num1 + num2;
                        jlResult.setText("Result = " + result);
                    }
                    else if (num1 == 0 && num2 == 0)
                    {
                        jlResult.setText("Result = Error");
                    }
                }
                catch (Exception f)
                {
                    jlResult.setText("Result = Error");
                }
                
            }
        });
        
        JButton subButton = new JButton("SUB");
        buttonPanel.add(subButton);
        subButton.setName("subButton");
        
        subButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                try
                {
                    double num1 = 0;
                    num1 = Double.parseDouble(leftOperand.getText());
                    double num2 = 0;
                    num2 = Double.parseDouble(rightOperand.getText());
                    double result = 0;
                    
                    if (num1 != 0 && num2 != 0)
                    {
                        result = num1 - num2;
                        jlResult.setText("Result = " + result);
                    }
                    else if (num1 == 0 && num2 == 0)
                    {
                        jlResult.setText("Result = Error");
                    }
                }
                catch (Exception f)
                {
                    jlResult.setText("Result = Error");
                    f.printStackTrace();
                }

            }
        });
        
        JButton multButton = new JButton("MULT");
        buttonPanel.add(multButton);
        multButton.setName("multButton");
        
        multButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                try
                {
                    double num1 = 0;
                    num1 = Double.parseDouble(leftOperand.getText());
                    double num2 = 0;
                    num2 = Double.parseDouble(rightOperand.getText());
                    double result = 0;
                    
                    if (num1 != 0 && num2 != 0)
                    {
                        result = num1 * num2;
                        jlResult.setText("Result = " + result);
                    }
                    else if (num1 == 0 && num2 == 0)
                    {
                        jlResult.setText("Result = Error");
                    }
                }
                catch (Exception f)
                {
                    jlResult.setText("Result = Error");
                }
                
            }
        });
        
        JButton divButton = new JButton("DIV");
        buttonPanel.add(divButton);
        divButton.setName("divButton");
        
        divButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                try
                {
                    double num1 = 0;
                    num1 = Double.parseDouble(leftOperand.getText());
                    double num2 = 0;
                    num2 = Double.parseDouble(rightOperand.getText());
                    double result = 0;
                    
                    if (num1 != 0 && num2 != 0)
                    {
                        result = num1 / num2;
                        jlResult.setText("Result = " + result);
                    }
                    else if (num1 == 0 && num2 == 0)
                    {
                        jlResult.setText("Result = Error");
                    }
                }
                catch (Exception f)
                {
                    jlResult.setText("Result = Error");
                }
                
            }
        });
        
        calculatorFrame.add(buttonPanel, BorderLayout.PAGE_END);
    }
    
    /**
     * resultPanel method which declares and initializes the panel and the label itself.
     */
    public void resultPanel()
    {
        JPanel resultPanel = new JPanel();
        
        jlResult = new JLabel("Result:");
        resultPanel.add(jlResult);
        jlResult.setName("resultLabel");
        
        calculatorFrame.add(resultPanel, BorderLayout.WEST);
    }
    
    /**
     * operandPanel method which declares and initializes the left and right operands.
     */
    public void operandPanel()
    {
        JPanel operandPanel = new JPanel();
        
        leftOperand = new JTextField(10);
        operandPanel.add(leftOperand);
        leftOperand.setName("leftOperand");
        
        rightOperand = new JTextField(10);
        operandPanel.add(rightOperand);
        rightOperand.setName("rightOperand");
        
        calculatorFrame.add(operandPanel, BorderLayout.PAGE_START);
    }
    
    /**
     * this method adds each panel to the frame
     */
    public void initializeComponents()
    {
       buttonPanel();
       resultPanel();
       operandPanel();
    }
       
    /**
     * this is the main method
     * @param args
     */
    public static void main(String[] args)
    {
        Calculator calc = new Calculator();
    }
}