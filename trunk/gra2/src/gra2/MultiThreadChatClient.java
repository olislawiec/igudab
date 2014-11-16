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
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.UnknownHostException;

/**
 *
 *
 */
public class MultiThreadChatClient extends Frame implements Runnable {

	private MyPanel contentPane; // /GUI panel container
	// Button btnSend, btnClose, btn1, btn2, btn3, btn4, btnP, btnM, btn10P,
	// btn10M, btnB, btnG ;
	public static JTextArea ekranLabel; // /screen for displaying players moves
	public static JTextArea ekranLabel2; // /screen for displaying players moves
	public static JLabel ekranKasa; // /screen for displaying amount of cash for
									// this.player
	public static JLabel ekranPula; // /not imple yet
	public static String texter = ""; // /field to sending msgs
	public static int intTexter = 0; // /sum for texter param
	public static JButton buttonname, handButton, betButton, checkButton,
			allInButton, callButton, raiseButton, foldButton, drowButton,
			plus1Button, plus10Button, resetButton, minus1Button,
			minus10Button, card1, card2, card3, card4;// /buttons in GUI
	public static String[] kolory = new String[4];
	public static String[] karty = new String[13];
	// The client socket
	private static Socket clientSocket = null; // /client's socket
	// The output stream
	private static PrintStream os = null;// /output stream
	// The input stream
	private static DataInputStream is = null;// /input stream
	private static BufferedReader inputLine = null;// /inputline from
													// bufferedreader
	private static boolean closed = false;// /bool for closing thread

	public static class ContentPane extends JPanel {

		/**
         *
         */
		private static final long serialVersionUID = 1L;

		public ContentPane() {
			setOpaque(false);
		}

		/**
		 * 
		 * @param g
		 *            graphic object for painting component
		 */
		@Override
		protected void paintComponent(Graphics g) {
			// Allow super to paint
			super.paintComponent(g);
			// Apply our own painting effect
			Graphics2D g2d = (Graphics2D) g.create();
			// 50% transparent Alpha
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.5f));
			g2d.setColor(getBackground());
			g2d.fill(getBounds());
			g2d.dispose();

		}

	}

	/**
	 * DisplayGUI() is method for displaying and setting gui in MyPanel(); with
	 * icons and buttons
	 */
	private void displayGUI() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Badugi POKERLIKE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new MyPanel();
		setLocation(0, 0);
		setSize(dim);
		setUndecorated(true);
		setBackground(new Color(0, 255, 0, 0));

		ImageIcon test = createImageIcon("/image/test.gif");
		ImageIcon las = createImageIcon("/image/las.jpeg");
		ImageIcon karty = createImageIcon("/image/karty.gif");
		ImageIcon hand = createImageIcon("/image/hand.gif");
		ImageIcon change = createImageIcon("/image/change.gif");
		ImageIcon shuffle = createImageIcon("/image/shuffle.gif");
		ImageIcon fold = createImageIcon("/image/fold.gif");
		ImageIcon raise = createImageIcon("/image/raise.gif");
		ImageIcon check = createImageIcon("/image/check.png");
		ImageIcon p1 = createImageIcon("/image/p1.png");
		ImageIcon p10 = createImageIcon("/image/p10.png");
		ImageIcon m10 = createImageIcon("/image/m10.png");
		ImageIcon m1 = createImageIcon("/image/m1.png");
		ImageIcon allin = createImageIcon("/image/allin.png");
		ImageIcon reset = createImageIcon("/image/reset.png");
		ImageIcon call = createImageIcon("/image/call.png");
		ImageIcon drow = createImageIcon("/image/drow.png");
		ImageIcon bet = createImageIcon("/image/bet.png");
		frame.setContentPane(new ContentPane());
		frame.getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		contentPane.setBackground(Color.BLACK);
		frame.setContentPane(new ContentPane());
		frame.getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(contentPane);

		/*
		 * JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 * pnlButtons.setOpaque(false); pnlButtons.add(new JButton("<<"));
		 * pnlButtons.add(new JButton("<")); pnlButtons.add(new JButton(">"));
		 * pnlButtons.add(new JButton(">>"));
		 * frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		 * setVisible(true);
		 */
		JTextArea instrukcja = new JTextArea();
		instrukcja
				.setText("Najpierw wybierasz Akcje np.\n Drow, zeby dopisac do ekranLabel swoj ruch.\n potem wybierasz karty jakie chcesz wyslac\n i klikasz Wyslij, zeby poszlo.");

		ekranPula = new JLabel("Pula");
		ekranPula.setForeground(Color.YELLOW);
		ekranKasa = new JLabel("Stan konta", test, 0);
		ekranKasa.setForeground(Color.white);
		ekranLabel = new JTextArea(1, 3);
		ekranLabel2 = new JTextArea(1, 1);
		ekranLabel.setBounds(3, 3, 300, 200);
		Font font = new Font("Verdana", Font.BOLD, 19);
		Font font2 = new Font("Serif", Font.ITALIC, 24);
		ekranLabel.setFont(font);
		ekranLabel.setForeground(Color.BLACK);
		ekranLabel.setText("Tutaj wpisz nickname,\n a potem Wyslij.");
		ekranLabel2.setFont(font2);
		ekranLabel.setForeground(Color.DARK_GRAY);

		frame.getContentPane().setLayout(new GridLayout(2, 6));

		buttonname = new JButton("Wyslij");
		buttonname.setIcon(test);
		buttonname.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("button clicked.");
				send(ekranLabel.getText() + ekranLabel2.getText());
				ekranLabel.setText("");
				ekranLabel2.setText("");
			}
		});
		contentPane.add(buttonname);

		callButton = new JButton("Call");
		callButton.setIcon(call);
		callButton.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("button clicked.");

				ekranLabel.setText("");
				ekranLabel2.setText("");
				texter = "BC";
				ekranLabel.setText(texter);
			}
		});
		contentPane.add(callButton);

		resetButton = new JButton();
		resetButton.setIcon(reset);
		resetButton.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				texter = "";
				intTexter = 0;
				ekranLabel.setText("");
				ekranLabel2.setText("");
			}
		});
		contentPane.add(resetButton);

		drowButton = new JButton();
		drowButton.setIcon(drow);
		drowButton.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				ekranLabel.setText("D");
				// ekranLabel.setText("wymieniam Karty");
			}
		});

		handButton = new JButton();
		handButton.setIcon(hand);
		handButton.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("handbutton clicked.");
				ekranLabel.setText("HAND");
			}
		});
		contentPane.add(handButton);

		allInButton = new JButton("All-In");
		allInButton.setIcon(allin);
		allInButton.addActionListener(new ActionListenerButton() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("button clicked.");
				ekranLabel.setText("BA");
			}
		});
		contentPane.add(allInButton);

		checkButton = new JButton();
		checkButton.setIcon(check);
		contentPane.add(checkButton);
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("check clicked.");
				ekranLabel.setText("BS");
			}
		});
		raiseButton = new JButton("Raise");
		raiseButton.setIcon(raise);
		contentPane.add(raiseButton);
		raiseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Raise clicked.");
				texter = "BR";
				ekranLabel.append(texter);
			}
		});
		foldButton = new JButton("Fold");
		foldButton.setIcon(fold);
		contentPane.add(foldButton);
		foldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Fold clicked.");
				ekranLabel.setText("BF");
			}
		});
		contentPane.add(ekranKasa);

		plus1Button = new JButton();
		plus1Button.setIcon(p1);
		contentPane.add(plus1Button);
		plus1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("+1 clicked.");
				intTexter = intTexter + 1;
				ekranLabel2.setText(toStr(intTexter));
			}
		});
		minus1Button = new JButton();
		minus1Button.setIcon(m1);
		contentPane.add(minus1Button);
		minus1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("-1 clicked.");
				intTexter = intTexter - 1;
				ekranLabel2.setText(toStr(intTexter));
			}
		});
		plus10Button = new JButton();
		plus10Button.setIcon(p10);
		contentPane.add(plus10Button);
		plus10Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("+10 clicked.");
				intTexter = intTexter + 10;
				ekranLabel2.setText(toStr(intTexter));
			}
		});
		minus10Button = new JButton();
		minus10Button.setIcon(m10);
		contentPane.add(minus10Button);
		minus10Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("-10 clicked.");
				intTexter = intTexter - 10;
				ekranLabel2.setText(toStr(intTexter));
			}
		});
		betButton = new JButton();
		betButton.setIcon(bet);
		contentPane.add(betButton);
		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intTexter = 0;
				// texter="B";
				System.out.println("Bet clicked.");
				ekranLabel.setText("B");

			}
		});
		contentPane.add(drowButton);
		card1 = new JButton("card1");
		// card1.setIcon(raise);
		contentPane.add(card1);
		card1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ekranLabel.append("1,");
				// System.out.println("Bet clicked.");
				// ekranLabel.setText("card1 chosen");
			}
		});
		contentPane.add(card1);

		card2 = new JButton("card2");
		// card2.setIcon(raise);
		contentPane.add(card2);
		card2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ekranLabel.append("2,");
				// System.out.println("Bet clicked.");
				// ekranLabel.setText("card2 chosen");
			}
		});
		contentPane.add(card2);

		card3 = new JButton("card3");
		// card3.setIcon(raise);
		contentPane.add(card3);
		card3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ekranLabel.append("3,");
				// System.out.println("Bet clicked.");
				// ekranLabel.setText("card3 chosen");
			}
		});
		contentPane.add(card3);

		card4 = new JButton("card4");
		// card4.setIcon(raise);
		contentPane.add(card4);
		card4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ekranLabel.append("4,");
				// System.out.println("Bet clicked.");
				// ekranLabel.setText("card4 chosen");
			}
		});
		contentPane.add(card4);
		contentPane.add(ekranLabel);
		contentPane.add(ekranLabel2);
		contentPane.add(instrukcja);
		contentPane.add(ekranPula);
		setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	/**
	 * 
	 * @param entry
	 *            entrance for calculus
	 * @return result of calculus
	 * @throws ScriptException
	 */
	public static String calc(String entry) throws ScriptException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String foo = entry;
		entry = (String) engine.eval(foo);

		return entry;
	}

	/**
	 * 
	 * @param msgFromClientToServer
	 *            message from client to server when prompted
	 * @return message of null ? depends on round
	 */
	public static BufferedReader send(String msgFromClientToServer) {

		try {
			StringReader sr = new StringReader(msgFromClientToServer);
			BufferedReader br = new BufferedReader(sr);
			os.println(br.readLine());
			System.out.println(br.readLine() + "_syso5_sender");
			return br;
		} catch (Exception e) {
			System.out.println("send(String msgFromClientToServer): " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param path
	 *            pathline to the icon in: jpg,png,gif
	 * @return ImageIcon
	 */
	static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ClientG.class.getResource(path);
		// System.out.println(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.out.println("Could not find image.");
			return null;
		}
	}

	private class MyPanel extends JPanel {

		// public JTextArea ekranLabel;
		private BufferedImage image;

		// JPanel myPanel;
		public MyPanel() {
			try {
				image = ImageIO.read(MyPanel.class
						.getResource("/image/dek.jpg"));
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}

		/**
		 * 
		 * @return dimension of component
		 */
		@Override
		public Dimension getPreferredSize() {
			return image == null ? new Dimension(400, 300) : new Dimension(
					image.getWidth(), image.getHeight());
		}

		/**
		 * 
		 * @param g
		 *            graphics object to paint with Image
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	/**
	 * 
	 * @param args
	 *            entrance param to start Client
	 */
	public static void main(String[] args) {

		// The default port.
		int portNumber = 8969;
		// The default host.
		String host = "localhost";
		if (args.length < 2) {
			String portP;
			portP = JOptionPane.showInputDialog("Podaj port:");
			portNumber = toInt(portP);

			host = JOptionPane.showInputDialog("Podaj adres:");
			System.out
					.println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
							+ "Now using host="
							+ host
							+ ", portNumber="
							+ portNumber);
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
		 * Open a socket on a given host and port. Open input and output
		 * streams.
		 */
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream()); // to:
			// klient->serwer
			is = new DataInputStream(clientSocket.getInputStream());// to:
			kolory[0] = "Pik";
			kolory[1] = "Karo";
			kolory[2] = "Kier";
			kolory[3] = "Trefl";
			karty[0] = "As";
			karty[1] = "2";
			karty[2] = "3";
			karty[3] = "4";
			karty[4] = "5";
			karty[5] = "6";
			karty[6] = "7";
			karty[7] = "8";
			karty[8] = "9";
			karty[9] = "10";
			karty[10] = "J";
			karty[11] = "D";
			karty[12] = "K";

			// serwer->klient
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to the host "
							+ host);
			System.exit(-3);
		}

		/*
		 * If everything has been initialized then we want to write some data to
		 * the socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket != null && os != null && is != null) {
			try {

				/* Create a thread to read from the server. */
				new Thread(new MultiThreadChatClient()).start();
				while (!isClosed()) {
					os.println(inputLine.readLine().trim());
					// System.out.println(inputLine.readLine().trim()+"_syso1");
				}
				/*
				 * Close the output stream, close the input stream, close the
				 * socket.
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
	/**
	 * 
	 * @param respoLine
	 *            param to set text on ekranLabel
	 */
	public void ekranLabelSetter(String respoLine) {
		MultiThreadChatClient.ekranLabel.setText(respoLine);
	}

	/**
	 * 
	 * @param splittedInt
	 *            param for splitting to set the cards and their icons not impe
	 *            yet
	 */
	public void ekranButtonCardDisplayer(int[] splittedInt) {
		card1.setIcon(null);
		card2.setIcon(null);
		card3.setIcon(null);
		card4.setIcon(null);
	}

	/**
	 * 
	 * @param splitted
	 *            param to get splitted into Integer
	 * @return
	 */
	public int[] splittedToInt(String[] splitted) {
		int[] splittedInt = null;
		try {
			splittedInt[0] = Integer.parseInt(splitted[0]);
			splittedInt[1] = Integer.parseInt(splitted[1]);
			splittedInt[2] = Integer.parseInt(splitted[2]);
			splittedInt[3] = Integer.parseInt(splitted[3]);
			return splittedInt;
		} catch (Exception e) {
			System.out.println("splittedToInt(String[] splitted): " + e);
		}
		return splittedInt;
	}

	/**
	 * 
	 * @param x
	 *            integer becoming
	 * @return String made from x
	 */
	public static String toStr(int x) {
		return Integer.toString(x);
	}

	/**
	 * 
	 * @param xs
	 *            String becoming
	 * @return Integer made of xs
	 */
	public static int toInt(String xs) {
		return Integer.parseInt(xs);
	}

	/**
	 * 
	 * @param respo
	 *            to split and set cards into buttons
	 */
	public void ekranCardsDealer(String[] respo) {
		if (respo.length < 5 && respo.length > 3) {
			card1.setText(kolory[toInt(respo[0]) % 13]);
			card1.setText(card1.getText() + (karty[toInt(respo[0]) / 13]));
			card2.setText(kolory[toInt(respo[1]) % 13]);
			card2.setText(card2.getText() + (karty[toInt(respo[1]) / 13]));
			card3.setText(kolory[toInt(respo[2]) % 13]);
			card3.setText(card3.getText() + (karty[toInt(respo[2]) / 13]));
			card4.setText(kolory[toInt(respo[3]) % 13]);
			card4.setText(card4.getText() + (karty[toInt(respo[3]) / 13]));
		}
	}

	/**
	 * 
	 * @param res
	 *            string to substring it wihout first letter of B/D/H
	 * @return same line without first char
	 */
	public String lineWithoutLetter(String res) {
		String responseLineWithoutLetter = "";
		responseLineWithoutLetter = res.substring(1);
		return responseLineWithoutLetter;
	}

	/**
	 * 
	 * @param splitMe
	 * @return splitMe without regex
	 */
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

	/**
	 * General method for corespondence with server
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine = "";

		try {
			while ((responseLine = is.readLine()) != null) {
				System.out.println(responseLine + "_syso3");
				if (responseLine.startsWith("H") && responseLine.length() > 4) {
					ekranCardsDealer(splited(withoutRegx(lineWithoutLetter(responseLine))));
					System.out.println(responseLine + "_syso2");
					// ekranLabelSetter(responseLineWithoutLetter);
				}
				if (responseLine.indexOf("Q ") != -1) {
					break;
				}
				if (responseLine.indexOf("Server too busy. Try later.") != (-2)) {
					setClosed(false);
				}
				if (responseLine.startsWith("BA")) {
					System.out.println("_syso4_widacBA?");
				}
				if (responseLine.startsWith("V")) {
					ekranKasa.setText(responseLine.substring(1));
				}
			}
			setClosed(true);
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
	}

	/**
	 * @return the closed true = working, false =not working
	 */
	public static boolean isClosed() {
		return closed;
	}

	/**
	 * @param closed
	 *            the closed to set
	 */
	public static void setClosed(boolean closed) {
		MultiThreadChatClient.closed = closed;
	}
}
