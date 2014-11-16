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
import java.io.StringReader;
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
	public static String s;
	public static JButton buttonname,
    handButton,
    betButton,
    checkButton,
    raiseButton,
    foldButton,
    drowButton,
    plus1Button,
    plus10Button,
    minus1Button,
    minus10Button,
	card1,
	card2,
	card3,
	card4;
	// The client socket
	  private static Socket 			clientSocket 	= null;
	  // The output stream
	  private static PrintStream		os 				= null;
	  // The input stream
	  private static DataInputStream 	is 				= null;
	  private static BufferedReader 	inputLine 		= null;
	  private static boolean 			closed 			= false;


	public static class ContentPane extends JPanel {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

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

      /*  JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        pnlButtons.add(new JButton("<<"));
        pnlButtons.add(new JButton("<"));
        pnlButtons.add(new JButton(">"));
        pnlButtons.add(new JButton(">>"));
        frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
        setVisible(true);
*/
        ekranLabel=new JTextArea(1, 7);
        ekranLabel2=new JTextArea(1,9);
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
        		send(ekranLabel.getText());
        		ekranLabel.setText("wyminilem KARTY");
        	}
        });
        contentPane.add(buttonname);

        drowButton = new JButton();
        drowButton.setIcon(test);
        drowButton.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		ekranLabel.setText("D");
        		//ekranLabel.setText("wymieniam Karty");
        	}
        });
        contentPane.add(drowButton);
        handButton = new JButton();
        handButton.setIcon(hand);
        handButton.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		//System.out.println("handbutton clicked.");
        		ekranLabel.setText("HAND");
        	}
        });
        contentPane.add(handButton);

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
        betButton = new JButton("Bet");
        betButton.setIcon(null);
        contentPane.add(betButton);
        betButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		
        		System.out.println("Bet clicked.");
        		ekranLabel.setText("bet");
        		
        	}
        });
        card1 = new JButton("card1");
        //card1.setIcon(raise);
        contentPane.add(card1);
        card1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		ekranLabel.append("1,");
        		//System.out.println("Bet clicked.");
        		//ekranLabel.setText("card1 chosen");
        	}
        });
        contentPane.add(card1);

        card2 = new JButton("card2");
        //card2.setIcon(raise);
        contentPane.add(card2);
        card2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		ekranLabel.append("2,");
        		//System.out.println("Bet clicked.");
        		//ekranLabel.setText("card2 chosen");
        	}
        });
        contentPane.add(card2);

        card3 = new JButton("card3");
        //card3.setIcon(raise);
        contentPane.add(card3);
        card3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		ekranLabel.append("3,");
        		//System.out.println("Bet clicked.");
        		//ekranLabel.setText("card3 chosen");
        	}
        });
        contentPane.add(card3);

        card4 = new JButton("card4");
        //card4.setIcon(raise);
        contentPane.add(card4);
        card4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		ekranLabel.append("4,");
        		//System.out.println("Bet clicked.");
        		//ekranLabel.setText("card4 chosen");
        	}
        });
        contentPane.add(card4);
        contentPane.add(ekranLabel);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

	public BufferedReader send (String msgFromClientToServer) {
		
		try {
			StringReader sr=new StringReader(msgFromClientToServer);
			BufferedReader br=new BufferedReader(sr);
			os.println(br.readLine());
			System.out.println(br.readLine()+"_syso5_sender");
			return br;
		} catch (Exception e) {
			System.out.println("send(String msgFromClientToServer): "+ e);
			e.printStackTrace();
		}
		return null;
	}
	private static ImageIcon createImageIcon(String path) {
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
      os = new PrintStream(clientSocket.getOutputStream()); //to: klient->serwer
      is = new DataInputStream(clientSocket.getInputStream());// to: serwer->klient
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
          //System.out.println(inputLine.readLine().trim()+"_syso1");
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
  public void ekranButtonCardDisplayer(int[] splittedInt) {
	  card1.setIcon(null);
	  card2.setIcon(null);
	  card3.setIcon(null);
	  card4.setIcon(null);
  }
  public int[] splittedToInt(String[] splitted) {
	int[] splittedInt = null;
	try {
	splittedInt[0]=Integer.parseInt(splitted[0]);
	splittedInt[1]=Integer.parseInt(splitted[1]);
	splittedInt[2]=Integer.parseInt(splitted[2]);
	splittedInt[3]=Integer.parseInt(splitted[3]);
	return splittedInt;
	} catch(Exception e) {
		System.out.println("splittedToInt(String[] splitted): "+e);
	}
	return splittedInt;
  }
  public String toStr(int x) {
	  return Integer.toString(x);
  }
  public void ekranCardsDealer(String[] respo) {
	  if (respo.length < 5 && respo.length > 3) {
		 // int[] respoINT = splittedToInt(respo);
		  /*int a,b,c,d;
		  //for(int i = 0; i<=3; i++)
		  int test = Integer.parseInt(respo[0]);
			  a=test%13;
			  test = Integer.parseInt(respo[1]);
			  b=test%13;
			  test = Integer.parseInt(respo[2]);
			  c=test%13;
			  test = Integer.parseInt(respo[3]);
			  d=test%13;
			  card1.setText(toStr(a));
			  card2.setText(toStr(b));
			  card3.setText(toStr(c));
			  card4.setText(toStr(d)); */
			card1.setText(respo[0]);
			card2.setText(respo[1]);
			card3.setText(respo[2]);
			card4.setText(respo[3]);
			//card1.setIcon();
	  }
  }
  public String lineWithoutLetter(String res) {
	  String responseLineWithoutLetter = "";
	  responseLineWithoutLetter = res.substring(1);
	  return responseLineWithoutLetter;
  }
  public String withoutRegx(String splitMe) {
	  String[] regX;
	  regX = splitMe.split(";");
	  return regX[0];
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
        System.out.println(responseLine+"_syso3");
        if (responseLine.startsWith("H") && responseLine.length()>4) {
        ekranCardsDealer(splited(withoutRegx(lineWithoutLetter(responseLine)))); // ciekawe co na to nasz wykladowca 
        System.out.println(responseLine+"_syso2");
        //ekranLabelSetter(responseLineWithoutLetter);
        }
        if (responseLine.indexOf("Q ") != -1) {
          break;
        }
        if (responseLine.indexOf("Server too busy. Try later.") != (-2)) {
        	closed = false;
        }
        if (responseLine.startsWith("BA")) {
        	System.out.println("_syso4_widacBA?");
        }
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}