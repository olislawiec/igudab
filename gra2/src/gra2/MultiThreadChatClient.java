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
import javax.swing.text.JTextComponent;

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
	//Button btnSend, btnClose, btn1, btn2, btn3, btn4, btnP, btnM, btn10P, btn10M, btnB, btnG ;
	public static JTextArea ekranLabel;
	public static JTextArea ekranLabel2;
	public static JButton buttonname,
    handButton,
    betButton,
    checkButton,
    raiseButton,
    foldButton,
    plus1Button,
    plus10Button,
    minus1Button,
    minus10Button,
	card1,
	card2,
	card3,
	card4;
			ImageIcon karta0 = createImageIcon("/image/0.png");
			ImageIcon karta1 = createImageIcon("/image/1.png");
			ImageIcon karta2 = createImageIcon("/image/2.png");
			ImageIcon karta3 = createImageIcon("/image/3.png");
			ImageIcon karta4 = createImageIcon("/image/4.png");
			ImageIcon karta5 = createImageIcon("/image/5.png");
			ImageIcon karta6 = createImageIcon("/image/6.png");
			ImageIcon karta7 = createImageIcon("/image/7.png");
			ImageIcon karta8 = createImageIcon("/image/8.png");
			ImageIcon karta9 = createImageIcon("/image/9.png");
			ImageIcon karta10 = createImageIcon("/image/10.png");
			ImageIcon karta11 = createImageIcon("/image/11.png");
			ImageIcon karta12 = createImageIcon("/image/12.png");
			ImageIcon karta13 = createImageIcon("/image/13.png");
			ImageIcon karta14 = createImageIcon("/image/14.png");
			ImageIcon karta15 = createImageIcon("/image/15.png");
			ImageIcon karta16 = createImageIcon("/image/16.png");
			ImageIcon karta17 = createImageIcon("/image/17.png");
			ImageIcon karta18 = createImageIcon("/image/18.png");
			ImageIcon karta19 = createImageIcon("/image/19.png");
			ImageIcon karta20 = createImageIcon("/image/20.png");
			ImageIcon karta21 = createImageIcon("/image/21.png");
			ImageIcon karta22 = createImageIcon("/image/22.png");
			ImageIcon karta23 = createImageIcon("/image/23.png");
			ImageIcon karta24 = createImageIcon("/image/24.png");
			ImageIcon karta25 = createImageIcon("/image/25.png");
			ImageIcon karta26 = createImageIcon("/image/26.png");
			ImageIcon karta27 = createImageIcon("/image/27.png");
			ImageIcon karta28 = createImageIcon("/image/28.png");
			ImageIcon karta29 = createImageIcon("/image/29.png");
			ImageIcon karta30 = createImageIcon("/image/30.png");
			ImageIcon karta31 = createImageIcon("/image/31.png");
			ImageIcon karta32 = createImageIcon("/image/32.png");
			ImageIcon karta33 = createImageIcon("/image/33.png");
			ImageIcon karta34 = createImageIcon("/image/34.png");
			ImageIcon karta35 = createImageIcon("/image/35.png");
			ImageIcon karta36 = createImageIcon("/image/36.png");
			ImageIcon karta37 = createImageIcon("/image/37.png");
			ImageIcon karta38 = createImageIcon("/image/38.png");
			ImageIcon karta39 = createImageIcon("/image/39.png");
			ImageIcon karta40 = createImageIcon("/image/40.png");
			ImageIcon karta41 = createImageIcon("/image/41.png");
			ImageIcon karta42 = createImageIcon("/image/42.png");
			ImageIcon karta43 = createImageIcon("/image/43.png");
			ImageIcon karta44 = createImageIcon("/image/44.png");
			ImageIcon karta45 = createImageIcon("/image/45.png");
			ImageIcon karta46 = createImageIcon("/image/46.png");
			ImageIcon karta47 = createImageIcon("/image/47.png");
			ImageIcon karta48 = createImageIcon("/image/48.png");
			ImageIcon karta49 = createImageIcon("/image/49.png");
			ImageIcon karta50 = createImageIcon("/image/50.png");
			ImageIcon karta51 = createImageIcon("/image/51.png");
   

	public static class ContentPane extends JPanel {

	    
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
        
        buttonname = new JButton();
        buttonname.setIcon(test);
        buttonname.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("button clicked.");
        		ekranLabel.setText("JA_TESTER");
        	}
        });
        contentPane.add(buttonname);
        
        handButton = new JButton();
        handButton.setIcon(karta0);
        handButton.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("handbutton clicked.");
        		ekranLabel.setText("HAND");
        	}
        });
        contentPane.add(handButton);
        betButton = new JButton("Bet");
        betButton.setIcon(raise);
        contentPane.add(betButton);
        betButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("bet");
        	}
        });
        checkButton = new JButton("Check");
        checkButton.setIcon(check);
        contentPane.add(checkButton);
        checkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("check clicked.");
        		ekranLabel.setText("check");
        	}
        });
        raiseButton = new JButton("Raise");
        raiseButton.setIcon(raise);
        contentPane.add(raiseButton);
        raiseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Raise clicked.");
        		ekranLabel.setText("raise");
        	}
        });
        foldButton = new JButton("Fold");
        foldButton.setIcon(fold);
        contentPane.add(foldButton);
        foldButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Fold clicked.");
        		ekranLabel.setText("fold");
        	}
        });
        contentPane.add(ekranLabel2);
        
        plus1Button = new JButton("$ +1");
        plus1Button.setIcon(p1);
        contentPane.add(plus1Button);
        plus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("+1 clicked.");
        		ekranLabel.setText("+1");
        	}
        });
        minus1Button = new JButton("$ -1");
        contentPane.add(minus1Button);
        minus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("-1 clicked.");
        		ekranLabel.setText("-1");
        	}
        });
        plus10Button = new JButton("$ +10");
        plus10Button.setIcon(p10);
        contentPane.add(plus10Button);
        plus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("+10 clicked.");
        		ekranLabel.setText("+10");
        	}
        });
        minus10Button = new JButton("$ -10");
        contentPane.add(minus10Button);
        minus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("-10 clicked.");
        		ekranLabel.setText("-10");
        	}
        });
        
        card1 = new JButton("card1");
        //card1.setIcon(raise);
        contentPane.add(card1);
        card1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("card1 chosen");
        	}
        });
        contentPane.add(card1);
        
        card2 = new JButton("card2");
        //card2.setIcon(raise);
        contentPane.add(card2);
        card2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("card2 chosen");
        	}
        });
        contentPane.add(card2);
       
        card3 = new JButton("card3");
        //card3.setIcon(raise);
        contentPane.add(card3);
        card3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("card3 chosen");
        	}
        });
        contentPane.add(card3);
       
        card4 = new JButton("card4");
        //card4.setIcon(raise);
        contentPane.add(card4);
        card4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("Bet clicked.");
        		ekranLabel.setText("card4 chosen");
        	}
        });
        contentPane.add(card4);
        contentPane.add(ekranLabel);
        
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ClientG.class.getResource(path);
		//System.out.println(path);
		if (imgURL != null) {
		return new ImageIcon(imgURL);
		}
		else {
			System.out.println("Could not find image.");
			return null;
		}
	}

	private class MyPanel extends JPanel {

       // public JTextArea ekranLabel;
		private BufferedImage image;
        //JPanel myPanel;
        public MyPanel() {
            try {
                image = ImageIO.read(MyPanel.class.getResource("/image/dek.jpg"));
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
  public void ekranLabelSetter(String respoLine) {
	  MultiThreadChatClient.ekranLabel.setText(respoLine);
  }
  public void ekranButtonCardDisplayer() {
	  card1.setIcon(null);
	  card2.setIcon(null);
	  card3.setIcon(null);
	  card4.setIcon(null);
	  
  }
  public int[] splittedToInt(String[] splitted) {
	int[] splittedInt = null;
	splittedInt[0]=Integer.parseInt(splitted[0]);
	splittedInt[1]=Integer.parseInt(splitted[1]);
	splittedInt[2]=Integer.parseInt(splitted[2]);
	splittedInt[3]=Integer.parseInt(splitted[3]);
	return splittedInt;
  }
  public void ekranCardsDealer(String[] respo) {
	  int[] hajduk=splittedToInt(respo);
	  if (respo.length < 5 && respo.length > 3) {
		  card1.setText(respo[0]);
		  card2.setText(respo[1]);
		  card3.setText(respo[2]);
		  card4.setText(respo[3]);
		 // card1.setIcon();
		  
	  }
  }
  public String lineWithoutLetter(String res) {
	  String responseLineWithoutLetter = "";
	  responseLineWithoutLetter = res.substring(1);
	  return responseLineWithoutLetter;
  }
  public String[] splited(String splitMe) {
	  String[] respo;
	  respo = splitMe.split(",");
	  return respo;
  }
  @SuppressWarnings("deprecation")
public void run() {
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
    String responseLine = "";
    
    
    try {
      while ((responseLine = is.readLine()) != null) {
        System.out.println(responseLine);
        if (responseLine.startsWith("H")) {
        ekranCardsDealer(splited(lineWithoutLetter(responseLine))); // ciekawe co na to nasz wykladowca :D
        //System.out.println(responseLineWithoutLetter);
        //ekranLabelSetter(responseLineWithoutLetter);
        }
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