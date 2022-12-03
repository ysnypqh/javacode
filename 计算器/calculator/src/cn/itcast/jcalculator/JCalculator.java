package cn.itcast.jcalculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class JCalculator extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6675736133730301893L;

	private class WindowCloser extends WindowAdapter {
       public void windowClosing(WindowEvent we) {
           System.exit(0);
       }
    }
 
    int i;
    // Strings for Digit & Operator buttons.
    private final String[] str = { "7", "8", "9", "/", "4", "5", "6", "*", "1",
           "2", "3", "-", ".", "0", "=", "+" };
    // Build buttons.
    JButton[] buttons = new JButton[str.length];
    // For cancel or reset.
    JButton reset = new JButton("CE");
    // Build the text field to show the result.
    JTextField display = new JTextField("0");
   
    /**
     * Constructor without parameters.
     */
    public JCalculator() {
       super("������");
       // Add a panel.
       JPanel panel1 = new JPanel(new GridLayout(4, 4));
       // panel1.setLayout(new GridLayout(4,4));
       for (i = 0; i < str.length; i++) {
           buttons[i] = new JButton(str[i]);
           panel1.add(buttons[i]);
       }
       JPanel panel2 = new JPanel(new BorderLayout());
       // panel2.setLayout(new BorderLayout());
       panel2.add("Center", display);
       panel2.add("East", reset);
       // JPanel panel3 = new Panel();
       getContentPane().setLayout(new BorderLayout());
       getContentPane().add("North", panel2);
       getContentPane().add("Center", panel1);
       // Add action listener for each digit & operator button.
       for (i = 0; i < str.length; i++)
           buttons[i].addActionListener(this);
       // Add listener for "reset" button.
       reset.addActionListener(this);
       // Add listener for "display" button.
       display.addActionListener(this);
       // The "close" button "X".
       addWindowListener(new WindowCloser());
       // Initialize the window size.
       setSize(800, 800);
       // Show the window.
       // show(); Using show() while JDK version is below 1.5.
       setVisible(true);
       // Fit the certain size.
       pack();
    }  
   
    public void actionPerformed(ActionEvent e) {
       Object target = e.getSource();
       String label = e.getActionCommand();
       if (target == reset)
           handleReset();
       else if ("0123456789.".indexOf(label) > 0)
           handleNumber(label);
       else
           handleOperator(label);
    }
    // Is the first digit pressed?
    boolean isFirstDigit = true;
    /**
     * Number handling.
     * @param key the key of the button.
     */
    public void handleNumber(String key) {
       if (isFirstDigit)
           display.setText(key);
       else if ((key.equals(".")) && (display.getText().indexOf(".") < 0))
           display.setText(display.getText() + ".");
       else if (!key.equals("."))
           display.setText(display.getText() + key);
       isFirstDigit = false;
    }
   
    /**
     * Reset the calculator.
     */
    public void handleReset() {
       display.setText("0");
       isFirstDigit = true;
       operator = "=";
    }
 
    double number = 0.0;
    String operator = "=";
   
    /**
     * Handling the operation.
     * @param key pressed operator's key.
     */
    public void handleOperator(String key) {
       if (operator.equals("+"))
           number += Double.valueOf(display.getText());
       else if (operator.equals("-"))
           number -= Double.valueOf(display.getText());
       else if (operator.equals("*"))
           number *= Double.valueOf(display.getText());
       else if (operator.equals("/"))
           number /= Double.valueOf(display.getText());
       else if (operator.equals("="))
           number = Double.valueOf(display.getText());
       display.setText(String.valueOf(number));
       operator = key;
       isFirstDigit = true;
    }
   
    public static void main(String[] args) {
       new JCalculator();
    }
}