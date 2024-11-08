import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.Date;

public class Clock extends JFrame{
    
    private Timer time;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH/mm/ss");
    private ClockPanel clockPanel;
    private JLabel timeLabel;
    private Clock()
    {
        setTitle("Clock");
        setSize(1000 , 800);
        setLocation(300, 50);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Date now = new Date();
              hour = now.getHours();
              min = now.getMinutes();
              sec = now.getSeconds();
              repaint();
            }
        });
        
        time.start();*/
        
        JLabel cre = new JLabel("Cre by Le Ngoc Duc", SwingConstants.CENTER);
        cre.setEnabled(false);
        add(cre, BorderLayout.NORTH);
        
        clockPanel = new ClockPanel();
        add(clockPanel, BorderLayout.CENTER);
        
        timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(timeLabel, BorderLayout.SOUTH); 
        
        time = new Timer(1000, new ActionListener() { //1000 la 1000 milisecond
            @Override
            public void actionPerformed(ActionEvent e) {
                clockPanel.updateTime();
                timeLabel.setText(getTime());
                repaint();
            }
        });
        time.start();
    }
    
    
    private String getTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
            
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Clock clock = new Clock();
            clock.setVisible(true);
        });
    }

    private static class ClockPanel extends JPanel{
        private int hour, min, sec;
        public ClockPanel() {
            updateTime();
            setPreferredSize(new Dimension(800,600));
        }
        public void updateTime()
        {
           Date now = new Date();
            hour = now.getHours();
            min = now.getMinutes();
            sec = now.getSeconds();
              
        }
        
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
             
            int width = getWidth();
            int height = getHeight();
            int x = width/2;
            int y = height/2;
            int radius = Math.min(width, height)/3;
            
            //ve mat dong ho
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x - radius, y - radius , radius*2, radius*2); //diem ben trai cung va diem tren cung do he toa do java khac
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x - radius, y - radius, radius*2, radius*2);
            
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("12", x - 10, y - radius + 30);
            g2d.drawString("3", x + radius - 20, y + 10);
            g2d.drawString("6", x - 10, y + radius - 10);
            g2d.drawString("9", x - radius + 5, y + 10);
            
            
            draw(g2d, (hour % 12 + min / 60.0) * 30, radius * 0.5, Color.BLACK, 6);  // Kim giờ
            draw(g2d, (min + sec / 60.0) * 6, radius * 0.8, Color.BLUE, 4);  // Kim phút
            draw(g2d, sec * 6, radius * 0.9, Color.RED, 2); 
        }
        
        private void draw(Graphics2D g2d, double angle, double length, Color color, int thickness) {
        g2d.setColor(color);  
        g2d.setStroke(new BasicStroke(thickness));  

        double radian = Math.toRadians(angle - 90); 
        //Trong hệ tọa độ của Java, góc 0 độ bắt đầu từ trục Y dương (lên trên), và tăng theo chiều kim đồng hồ.
        //Tuy nhiên, trong một đồng hồ, góc 0 độ là ở phía trên (12 giờ), vì vậy chúng ta cần điều chỉnh góc để phù hợp với cách vẽ của đồng hồ.

        int x = (int) (getWidth() / 2 + length * Math.cos(radian));
        int y = (int) (getHeight() / 2 + length * Math.sin(radian));

        
        g2d.drawLine(getWidth() / 2, getHeight() / 2, x, y); //x1, y1, x2, y2 ,nom na noi 2 diem lai voi nhau
    }
    }
}
