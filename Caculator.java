import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class Caculator extends JFrame implements ActionListener{
    static String num1, num2, operator;
    Caculator()
    {
        num1 = operator = num2 = "";
    }
    static JTextField result = new JTextField("0");
    public static void main(String[] args) {
        Caculator c = new Caculator();
        
        JFrame frame = new JFrame("Caculator");
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        result.setEditable(false);
        result.setSize(500,100);
        frame.add(result, "North");
            

        Panel panel = new Panel(new GridLayout(5,4));
        String[] arr = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+", "C", "^", "sqrt"};
        for (int i = 0; i < arr.length; i++) {
            JButton button = new JButton(arr[i]);
            panel.add(button);
            button.addActionListener(c);
        }
        panel.setSize(300, 300);
        panel.setBackground(Color.BLUE);
        frame.add(panel, "Center");
        frame.show();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(Character.isDigit(s.charAt(0)) || s.charAt(0) == '.')
        {
            if(!operator.equals(""))
            {
                num2 = num2 + s;
            }
            else num1 = num1 + s;
            result.setText(num1 + operator + num2);
        }
        else if(s.charAt(0) == 'C')
        {
            num1 = operator = num2 = "";
            result.setText(num1 + operator + num2);
        }
        else if(s.charAt(0) == '=')
        {
            double ans;
            if(operator.equals("+"))
            {
                ans = Double.parseDouble(num1) + Double.parseDouble(num2);
            }
            else if(operator.equals("-"))
            {
                ans = Double.parseDouble(num1) - Double.parseDouble(num2);
            }
            else if(operator.equals("*"))
            {
                ans = Double.parseDouble(num1) * Double.parseDouble(num2);
            }
            else if(operator.equals("/"))
            {
                ans = Double.parseDouble(num1) / Double.parseDouble(num2);
            }
            else //(operator.equals("^"))
            {
                ans = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
            }
            result.setText(num1 + operator + num2 + "=" + ans);
            num1 = String.valueOf(ans);
            operator = num2 = "";
        }
        else if(s.charAt(0) != 's')
        {
            if(operator.isEmpty() || num2.isEmpty())
            {
                operator = s;
            }
            else
            {
                double ans;
                if(operator.equals("+"))
                {
                    ans = Double.parseDouble(num1) + Double.parseDouble(num2);
                }
                else if(operator.equals("-"))
                {
                    ans = Double.parseDouble(num1) - Double.parseDouble(num2);
                }
                else if(operator.equals("*"))
                {
                    ans = Double.parseDouble(num1) * Double.parseDouble(num2);
                }
                else if(operator.equals("/"))
                {
                    ans = Double.parseDouble(num1) / Double.parseDouble(num2);
                }
                else //(operator.equals("^"))
                {
                    ans = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
                }
                num1 = String.valueOf(ans);
                operator = s;
                num2 = "";
            }
            result.setText(num1 + operator + num2);
        }
        else
        {
            if(!operator.isEmpty() || !num2.isEmpty())
            {
                double ans;
                 if(operator.equals("+"))
                {
                    ans = Double.parseDouble(num1) + Double.parseDouble(num2);
                }
                else if(operator.equals("-"))
                {
                    ans = Double.parseDouble(num1) - Double.parseDouble(num2);
                }
                else if(operator.equals("*"))
                {
                    ans = Double.parseDouble(num1) * Double.parseDouble(num2);
                }
                else if(operator.equals("/"))
                {
                    ans = Double.parseDouble(num1) / Double.parseDouble(num2);
                }
                else //(operator.equals("^"))
                {
                    ans = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
                }
                num1 = String.valueOf(ans);
                double res = Math.sqrt(Double.parseDouble(num1));
                result.setText(String.valueOf(res));
                operator = num2 = "";
            }
            else
            {
                double ans;
                ans = Math.sqrt(Double.parseDouble(num1));
                result.setText(String.valueOf(ans));
                num1 = String.valueOf(ans);
                operator = num2 = "";
            }
            
        }
    }
}
