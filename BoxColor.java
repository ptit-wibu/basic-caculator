import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author adminbk
 */

public class BoxColor extends JFrame implements ActionListener{
    static JFrame frame = new JFrame("Box Color");
    static JPanel panel = new JPanel();
    static JButton submit = new JButton("submit");
    static JComboBox<String> color ;
        public BoxColor() {
        frame.setTitle("Box Color");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocation(500,250);

        String[] data = {"red", "blue", "black", "gray", "green", "cyan","default"};
        color = new JComboBox<>(data);
        //color.addActionListener(this); 

        JButton submit = new JButton("Submit");
        submit.addActionListener(this); 

        JSplitPane div = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, submit, color);
        frame.add(div, "South");
        frame.add(panel, "Center");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoxColor boxColor = new BoxColor();
            boxColor.setVisible(true);
            frame.show();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            String selected = (String) color.getSelectedItem();
            if (selected.equals("blue")) {
                panel.setBackground(Color.BLUE);
            } else if (selected.equals("red")) {
                panel.setBackground(Color.RED); 
            }
            else if (selected.equals("black")) {
                panel.setBackground(Color.BLACK); 
            }
            else if (selected.equals("cyan")) {
                panel.setBackground(Color.CYAN); 
            }
            else if (selected.equals("gray")) {
                panel.setBackground(Color.GRAY); 
            }
            else if (selected.equals("green")) {
                panel.setBackground(Color.GREEN); 
            }
            else
            {
                panel.setBackground(getBackground());
            }
        }
    }
}
