package gra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
//import java.io.IOException;
//import java.io.DataOutputStream;
//import java.io.DataInputStream;
//import java.net.Socket;

public class MultiThreadChatClient extends Frame implements Runnable {

	private MyPanel contentPane;
	Button btnSend, btnClose, btn1, btn2, btn3, btn4, btnP, btnM, btn10P, btn10M, btnB, btnG ;
	JTextArea ekranLabel, ekranLabel2;

	public class ContentPane extends JPanel {

	    public ContentPane() {

	        setOpaque(false);

	    }

	    @Override
	    protected void paintComponent(Graphics g) {

	        // Allow super to paint
	        super.paintComponent(g);

	        // Apply our own painting effect
	        Graphics2D g2d = (Graphics2D) g.create();
	        // 50% transparent Alpha
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

	        g2d.setColor(getBackground());
	        g2d.fill(getBounds());

	        g2d.dispose();

	    }

	}
	private void displayGUI() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Badugi POKERLIKE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new MyPanel();
        setLocation(0, 0);
        setSize(dim);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));
        
        
        ImageIcon test 		= 	createImageIcon("/image/test.gif");
        ImageIcon las 		= 	createImageIcon("/image/las.jpeg");
        ImageIcon karty 	= 	createImageIcon("/image/karty.gif");
        ImageIcon hand 		= 	createImageIcon("/image/hand.gif");
        ImageIcon change 	= 	createImageIcon("/image/change.gif");
        ImageIcon shuffle 	= 	createImageIcon("/image/shuffle.gif");
        ImageIcon fold 		= 	createImageIcon("/image/fold.gif");
        ImageIcon raise		= 	createImageIcon("/image/raise.gif");
        ImageIcon check 	= 	createImageIcon("/image/check.gif");
        ImageIcon p1		= 	createImageIcon("/image/p1.gif");
        ImageIcon p10 		= 	createImageIcon("/image/p10.gif");

        frame.setContentPane(new ContentPane());
        frame.getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        
      
        contentPane.setBackground(Color.BLACK);
        frame.setContentPane(new ContentPane());
        frame.getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(contentPane);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        pnlButtons.add(new JButton("<<"));
        pnlButtons.add(new JButton("<"));
        pnlButtons.add(new JButton(">"));
        pnlButtons.add(new JButton(">>"));

        frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        setVisible(true);

        ekranLabel=new JTextArea(5, 5);
        ekranLabel2=new JTextArea(1,7);
        ekranLabel.setBounds(3, 3, 300, 200);
        Font font = new Font("Verdana", Font.BOLD, 19);
        Font font2 = new Font("Serif", Font.ITALIC, 24);
        ekranLabel.setFont(font);
        ekranLabel.setForeground(Color.BLACK);
        ekranLabel2.setFont(font2);
        ekranLabel.setForeground(Color.DARK_GRAY);
        
        
        frame.getContentPane().setLayout(new GridLayout(2, 6));
        JButton buttonname;
        buttonname = new JButton();
        buttonname.setIcon(test);
        buttonname.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("button clicked.");
        		ekranLabel.setText("JA_TESTER");
        	}
        });
        contentPane.add(buttonname);
        JButton handButton;
        handButton = new JButton();
        handButton.setIcon(hand);
        handButton.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("button clicked.");
        		ekranLabel.setText("HAND");
        	}
        });
        contentPane.add(handButton);
        JButton betButton = new JButton("Bet");
        betButton.setIcon(raise);
        contentPane.add(betButton);
        betButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("bet");
        	}
        });
        JButton checkButton = new JButton("Check");
        checkButton.setIcon(check);
        contentPane.add(checkButton);
        checkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("check clicked.");
        		ekranLabel.setText("check");
        	}
        });
        JButton raiseButton = new JButton("Raise");
        raiseButton.setIcon(raise);
        contentPane.add(raiseButton);
        raiseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Raise clicked.");
        		ekranLabel.setText("raise");
        	}
        });
        JButton foldButton = new JButton("Fold");
        foldButton.setIcon(fold);
        contentPane.add(foldButton);
        foldButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Fold clicked.");
        		ekranLabel.setText("fold");
        	}
        });
        contentPane.add(ekranLabel2);
        
        JButton plus1Button = new JButton("$ +1");
        plus1Button.setIcon(p1);
        contentPane.add(plus1Button);
        plus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("+1 clicked.");
        		ekranLabel.setText("+1");
        	}
        });
        JButton minus1Button = new JButton("$ -1");
        contentPane.add(minus1Button);
        minus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("-1 clicked.");
        		ekranLabel.setText("-1");
        	}
        });
        JButton plus10Button = new JButton("$ +10");
        plus10Button.setIcon(p10);
        contentPane.add(plus10Button);
        plus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("+10 clicked.");
        		ekranLabel.setText("+10");
        	}
        });
        JButton minus10Button = new JButton("$ -10");
        contentPane.add(minus10Button);
        minus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("-10 clicked.");
        		ekranLabel.setText("-10");
        	}
        });
        
        contentPane.add(ekranLabel);
        
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
	
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ClientG.class.getResource(path);
		System.out.println(path);
		if (imgURL != null) {
		return new ImageIcon(imgURL);
		}
		else {
			System.out.println("Could not find image.");
			return null;
		}
	}

	private class MyPanel extends JPanel {

        private BufferedImage image;
        //JPanel myPanel;
        public MyPanel() {
            /*try {
                image = ImageIO.read(MyPanel.class.getResource("/image/dek.jpg"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }*/
            
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
	
  // The client socket
  private static Socket 			clientSocket 	= null;
  // The output stream
  private static PrintStream		os 				= null;
  // The input stream
  private static DataInputStream 	is 				= null;

  private static BufferedReader 	inputLine 		= null;
  
  private static boolean 			closed 			= false;
  public static void main(String[] args) {

    // The default port.
    int portNumber = 8969;
    // The default host.
    String host = "localhost";

    if (args.length < 2) {
      System.out
          .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
              + "Now using host=" + host + ", portNumber=" + portNumber);
    } else {
      host = args[0];
      portNumber = Integer.valueOf(args[1]).intValue();
    }
    
    try {
		new MultiThreadChatClient().displayGUI();
	} catch (Exception e) {
		
		e.printStackTrace();
		System.exit(-1);
	}
    /*
     * Open a socket on a given host and port. Open input and output streams.
     */
    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + host);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host "
          + host);
    }

    /*
     * If everything has been initialized then we want to write some data to the
     * socket we have opened a connection to on the port portNumber.
     */
    if (clientSocket != null && os != null && is != null) {
      try {

        /* Create a thread to read from the server. */
        new Thread(new MultiThreadChatClient()).start();
        while (!closed) {
          os.println(inputLine.readLine().trim());
        }
        /*
         * Close the output stream, close the input stream, close the socket.
         */
        os.close();
        is.close();
        clientSocket.close();
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }

  /*
   * Create a thread to read from the server.
   */
  public void run() {
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
	  
	  //TU WSTAWIC DO JTEXTAREA/JTEXTFIELD 
	  //t1.append();
	  //
    String responseLine;
    try {
      while ((responseLine = is.readLine()) != null) {
        System.out.println(responseLine);
        if (responseLine.indexOf("Q ") != -1) {
          break;
        }
        if (responseLine.indexOf("Server too busy. Try later.") != (-2)) {
        	closed = false;
        }
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}