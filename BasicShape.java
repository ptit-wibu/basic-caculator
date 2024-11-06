import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class BasicShape extends JFrame{
    private JFrame frame;
    private JPanel panel;
    private JComboBox<String> shape;
    private JComboBox<String> filled;
    private String selectedShape = "Rectangle";
    private String selectedStyle = "Filled";
    private Color selectedColor = Color.BLACK;
    private JButton colorButton;
    private JTextField widthField, heightField, radiusField, sidesField;
    private JButton changeButton;
    public BasicShape()
    {
        setTitle("Drawing Basic Shape");
        setSize(800,600);
        setLocation(500, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //padding
        
        
        String []shapeChoice = {"Square", "Rectangle", "Circle", "Polygon"};
        shape = new JComboBox<>(shapeChoice);
        shape.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectedShape = (String) shape.getSelectedItem();
                updateFieldShape();
            } 
        });
        
        
        String[] fillChoice = {"Hollow", "Filled"};
        filled = new JComboBox<>(fillChoice);
        filled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStyle = (String) filled.getSelectedItem();
            }
        });
        
        
        colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(null, "Choose Shape Color", selectedColor);
                if(selectedColor == null)
                {
                    selectedColor = Color.BLACK;
                }
            }
        });
        
        //input size
        widthField = new JTextField(5); //size cua field
        heightField = new JTextField(5);
        radiusField = new JTextField(5);
        sidesField = new JTextField(5);
        
        //default
        widthField.setText("100");
        heightField.setText("100");
        radiusField.setText("50");
        sidesField.setText("6");
        
        changeButton = new JButton("Change");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Choose Shape:"), gbc);
        
        gbc.gridx = 1;
        controlPanel.add(shape, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Choose Style:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(filled, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(colorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        controlPanel.add(new JLabel("Width:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(widthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        controlPanel.add(new JLabel("Height:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(heightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        controlPanel.add(new JLabel("Radius:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(radiusField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        controlPanel.add(new JLabel("Sides (Polygon):"), gbc);

        gbc.gridx = 1;
        controlPanel.add(sidesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        controlPanel.add(changeButton, gbc);
        
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(selectedColor);
                
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int radius = Integer.parseInt(radiusField.getText());
                int sides = Integer.parseInt(sidesField.getText());
                
                int y = getHeight()/2 - height/2;
                int x = getWidth()/2 - width/2;
                
                if(selectedShape == "Square")
                {
                    drawSquare(g,x,y,width);
                }
                else if(selectedShape == "Rectangle")
                {
                    drawRectangle(g,x,y,width,height);
                }
                else if(selectedShape == "Circle")
                {
                    drawCircle(g,x,y,radius);
                }
                else drawPolygon(g,x,y,width,height,sides);
            }
        };
        
        JTextField cre = new JTextField("Cre by Le Ngoc Duc");
        cre.setEditable(false);
        
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.WEST);  
        add(panel, BorderLayout.CENTER);
        add(cre, BorderLayout.NORTH);
    }
    
    private void drawSquare(Graphics g, int x, int y, int width)
    {
        if(selectedStyle.equals("Filled")) g.fillRect(x, y, width, width);
        else g.drawRect(x, y, width, width);
    }
    
    private void drawRectangle(Graphics g, int x, int y, int width, int height)
    {
        if(selectedStyle.equals("Filled")) g.fillRect(x, y, width, height);
        else g.drawRect(x, y, width, height);
    }
    
    private void drawCircle(Graphics g, int x, int y, int radius)
    {
        if(selectedStyle.equals("Filled")) g.fillOval(x, y, radius*2, radius*2); //no ve theo duong kinh
        else g.drawOval(x, y, radius*2, radius*2);
    }
    
    private void drawPolygon(Graphics g, int x, int y, int width, int height, int sides)
    {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        for (int i = 0; i < sides; i++) {
            xPoints[i] = (int) (x + width * Math.cos(2 * Math.PI * i / sides)); //tinh toa do cua diem
            yPoints[i] = (int) (y + height * Math.sin(2 * Math.PI * i / sides));//tinh toa do cua diem
        }
        if(selectedStyle.equals("Filled")) g.fillPolygon(xPoints, yPoints, sides);
        else g.drawPolygon(xPoints, yPoints, sides);
    }
    
    
    private void updateFieldShape() {
        if(selectedShape.equals("Rectangle"))
        {
            widthField.setEnabled(true);
            heightField.setEnabled(true);
            radiusField.setEnabled(false);
            sidesField.setEnabled(false);
        }
        else if(selectedShape.equals("Square"))
        {
            widthField.setEnabled(true);
            heightField.setEnabled(false);
            radiusField.setEnabled(false);
            sidesField.setEnabled(false);
        }
        else if(selectedShape.equals("Circle"))
        {
            widthField.setEnabled(false);
            heightField.setEnabled(false);
            radiusField.setEnabled(true);
            sidesField.setEnabled(false);
        }
        else
        {
             widthField.setEnabled(true);
            heightField.setEnabled(true);
            radiusField.setEnabled(false);
            sidesField.setEnabled(true);
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            BasicShape basicShape = new BasicShape();
            basicShape.setVisible(true);
        });
    }
}
