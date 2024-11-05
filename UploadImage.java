import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author adminbk
 */
public class UploadImage extends JFrame{
    private JFrame frame = new JFrame("Upload Image");
    private JPanel panel = new JPanel();
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private ArrayList<String> imagesPath = new ArrayList<>();
    JFileChooser upload = new JFileChooser();
    
    public UploadImage()
    {
        setTitle("Upload Image");
        setSize(1000 , 800);
        setLocation(300, 50);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        upload = new JFileChooser();
        upload.setMultiSelectionEnabled(true);
        
        JTextField cre = new JTextField();
        cre.setEditable(false);
        cre.setText("Cre by Le Ngoc Duc");
        cre.setFont(new Font("Arial", Font.HANGING_BASELINE, 16));
        add(cre, BorderLayout.SOUTH);
        
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                showImage(g);
            }
        };
        panel.setLayout(null);
        panel.setAutoscrolls(true);
        
        images = new ArrayList<>();
        imagesPath = new ArrayList<>();
        
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setFont(new Font("Arial", Font.BOLD, 16));
        JMenuItem openItem = new JMenuItem("Open Image");
        openItem.setFont(new Font("Arial", Font.BOLD, 16));
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openImage();
                } catch (IOException ex) {
                    Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menu.add(openItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        
        add(panel, BorderLayout.CENTER);
        /*JScrollPane scrollPane = new JScrollPane(panel, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar Bar = scrollPane.getVerticalScrollBar();
        scrollPane.add(Bar);
        scrollPane.setPreferredSize(new Dimension(1000, 700));
        add(scrollPane, BorderLayout.CENTER);*/
        //add(upload, BorderLayout.SOUTH);
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadImage uploadImage = new UploadImage();
            uploadImage.setVisible(true);
        });
    }
     private void showImage(Graphics g) {
        int x = 5, y = 5;
        int padding = 10;
        for(BufferedImage img : images)
        {
            int width = img.getWidth();
            int height = img.getHeight();
            int max_height = panel.getHeight()/3;
            int max_width = panel.getWidth()/3;
            if(width > max_width)
            {
                double scale = (double) max_width/width;
                width = max_width;
                height = (int) (height*scale);
            }
            if(height > max_height)
            {
                double scale = (double) max_height/height;
                height = max_height;
                width = (int) (width*scale);
            }
            g.drawImage(img, x, y, width, height, this);
            x += width + padding; // dich chuyen x
            if(x + width > panel.getWidth())
            {
                x = 5; // reset
                y += (height + padding);
            }
        }
    }
    private void openImage() throws IOException
    {
        int returnValue = upload.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File[] files = upload.getSelectedFiles();
            for(File file:files)
            {
                try{
                    BufferedImage img = ImageIO.read(file);
                    if(img != null){
                        images.add(img);
                        imagesPath.add(file.getAbsolutePath());
                    }
                }
                catch(IOException e)
                {
                    
                }
            }
            panel.repaint();
        }
    }
}
