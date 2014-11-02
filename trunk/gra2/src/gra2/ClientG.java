package gra2;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**Beautiful GUI for our GAME experience! */
public class ClientG {

    private MyPanel contentPane;

    private void displayGUI() {
        JFrame frame = new JFrame("Image Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new MyPanel();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private class MyPanel extends JPanel {

        private BufferedImage image;

        public MyPanel() {
            try {
                image = ImageIO.read(MyPanel.class.getResource("/image/pokerstars.jpg"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return image == null ? new Dimension(400, 300): new Dimension(image.getWidth(), image.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new ClientG().displayGUI();
            }
        };
        EventQueue.invokeLater(runnable);
    }
}