package gra2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.*;

/**Beautiful GUI for our GAME experience! */
public class ClientG extends Frame implements Runnable {

	 /**
     * @param soc odpowiedzialny za gniazdo klienta
     * @param tf Textfield do zczytywania polecen
     * @param ta TextArea do wypisywania komunikatow
     *
     */
	String imgFileName="ico.gif";
	ImageIcon icon=new ImageIcon(imgFileName);
	JTextArea ekranLabel;
    Socket soc;
    TextField tf;
    TextArea t1, t2, t3, t4, t5, t6;
    Button btnSend, btnClose, btn1, btn2, btn3, btn4, btnP, btnM, btn10P, btn10M, btnB, btnG ;
    String sendTo;
    String LoginName;
    Thread t = null;
    DataOutputStream dout;
    DataInputStream din;
    private MyPanel contentPane;

    ClientG(String LoginName, String adres, String port, String players) throws Exception {
        super(LoginName);
        try {
        	int playersI = Integer.parseInt(players);
            int portZ = Integer.parseInt(port);
            this.LoginName = LoginName;
            sendTo = port;
            tf = new TextField(50);
            if (playersI == 2) {
            	t1 = new TextArea(15, 15);
            	t2 = new TextArea(15, 15);
            } else if (playersI == 3) {
            	t1 = new TextArea(15, 15);
            	t2 = new TextArea(15, 15);
            	t3 = new TextArea(15, 15);
            } else if (playersI == 4) {
            	t1 = new TextArea(15, 15);
            	t2 = new TextArea(15, 15);
            	t3 = new TextArea(15, 15);
            	t4 = new TextArea(15, 15);
            } else if (playersI == 5) {
            	t1 = new TextArea(15, 15);
            	t2 = new TextArea(15, 15);
            	t3 = new TextArea(15, 15);
            	t4 = new TextArea(15, 15);
            	t5 = new TextArea(15, 15);
            } else if (playersI == 6) {
            	t1 = new TextArea(15, 15);
            	t2 = new TextArea(15, 15);
            	t3 = new TextArea(15, 15);
            	t4 = new TextArea(15, 15);
            	t5 = new TextArea(15, 15);
            	t6 = new TextArea(15, 15);
            } else {
            	System.out.println("Max players capacity is 6! Lets play in SIX!");
            }
            btn1 = new Button("1");
            btn2 = new Button("2");
            btn3 = new Button("3");
            btn4 = new Button("4");
            btnP = new Button("+1");
            btnM = new Button("-1");
            btn10P = new Button("+10");
            btn10M = new Button("-10");
            btnB = new Button("Bet");
            btnG = new Button("Go");
            btnSend = new Button("Send");
            btnClose = new Button("Close");
            soc = new Socket(adres, portZ);
            din = new DataInputStream(soc.getInputStream());
            dout = new DataOutputStream(soc.getOutputStream());
            dout.writeUTF(LoginName);
            
            t = new Thread(this);
            t.start();
        } catch (Exception e) {
            return;
        }

    }

    
    private void displayGUI() {
        JFrame frame = new JFrame("Badugi POKERLIKE");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new MyPanel();
        frame.getContentPane().setLayout(new GridLayout(3, 3));
        ImageIcon a = createImageIcon("/image/test.gif");
        ImageIcon icon = createImageIcon("/image/ico.gif");
        ekranLabel=new JTextArea("LoL");
        contentPane.add(ekranLabel);
        JButton buttonname;
        buttonname = new JButton("ButtonTittle", a);
        //buttonname.setIcon(a);
        buttonname.addActionListener(new ActionListenerButton() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("button clicked.");
        		ekranLabel.setText("chuj");
        	}
        });
        
        contentPane.add(buttonname);
        //DO KAZDEGO BUTTONA OD RAZU PRZYLOZYLEM ACTIONLISTENER ZEBYS MOGL WSADZIC W NIE AKCJE GRY
        JButton betButton = new JButton("Bet");
        contentPane.add(betButton);
        betButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Bet clicked.");
        		ekranLabel.setText("bet");
        		
        	}
        });
        JButton checkButton = new JButton("Check");
        contentPane.add(checkButton);
        checkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("check clicked.");
        		ekranLabel.setText("check");
        	}
        });
        JButton raiseButton = new JButton("Raise");
        contentPane.add(raiseButton);
        raiseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Raise clicked.");
        		ekranLabel.setText("raise");
        	}
        });
        JButton foldButton = new JButton("Fold");
        contentPane.add(foldButton);
        foldButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Fold clicked.");
        		ekranLabel.setText("fold");
        	}
        });
        JButton plus1Button = new JButton("$ +1");
        contentPane.add(plus1Button);
        plus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("+1 clicked.");
        		ekranLabel.setText("+1");
        	}
        });
        JButton minus1Button = new JButton("$ -1");
        contentPane.add(minus1Button);
        minus1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("-1 clicked.");
        		ekranLabel.setText("-1");
        	}
        });
        JButton plus10Button = new JButton("$ +10");
        contentPane.add(plus10Button);
        plus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("+10 clicked.");
        		ekranLabel.setText("+10");
        	}
        });
        JButton minus10Button = new JButton("$ -10");
        contentPane.add(minus10Button);
        minus10Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.out.println("-10 clicked.");
        		ekranLabel.setText("-10");
        	}
        });
        
        
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ClientG.class.getResource(path);
		System.out.println(path);
		if(imgURL!=null){ 
		return new ImageIcon(imgURL);}
		else {
			System.out.println("Could not find image.");
			return null;
		}
	}

	private class MyPanel extends JPanel {

        private BufferedImage image;
        //JPanel myPanel;
        public MyPanel(/*JPanel thisPanel*/) {
        	//myPanel=thisPanel;
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

    /**
     * Ustawia szybkie setup do GUI
     */
    @SuppressWarnings("deprecation")
    void setup() {
        setSize(800, 600);
        
        setLayout(new GridLayout(4, 4));
        Panel p = new Panel();
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);
        p.add(btn4);
        p.add(btnG);
        p.add(btnP);
        p.add(btnM);
        p.add(btn10P);
        p.add(btn10M);
        p.add(btnB);
        p.add(tf);
        p.add(btnSend);
        p.add(btnClose);
        add(p);
        setVisible(true);
        try{
        add(t1);
        }catch(NullPointerException t1) {
        	System.out.println(t1);
        }try{
            add(t2);
            }catch(NullPointerException t2) {
            	System.out.println(t2);
            }
        try{
                add(t3);
            }catch(NullPointerException t3) {
            	System.out.println(t3);
            }
        try{
                add(t4);
            }catch(NullPointerException t4) {
            	System.out.println(t4);
            }
        try{
                add(t5);
            }catch(NullPointerException t5) {
            	System.out.println(t5);
            }
        try{
                add(t6);
            }catch(NullPointerException t6) {
            	System.out.println(t6);
            }
        show();
        t1.append("ADRES - by polaczyc z ADRESEM i PORTEM\n"
                + " GC - get card\n"
                + " H - show hand\n"
                + " C - check \n"
                + " B - bet\n"
                + " R - raise\n"
                + " W - call\n"
                + " F - fold \n"
                + " A - all-in\n"
                + " Przycisk CLOSE - by wyjsc (Q)\n"
                + " >O/E - komunikaty ze strony serwera\n");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    dout.writeUTF(LoginName + " Q");
                } catch (Exception ex) {
                	System.out.println("Invalid disconnection: "+ex);
                	System.exit(-1);
                    
                }
                System.exit(0);
                
                }
        });
    }

    public static void main(String[] args) throws Exception {
    	try {
	if (args.length <= 2) {

			final String playerZ = JOptionPane.showInputDialog("Podaj ilosc graczy:");
            final String portP;
            portP = JOptionPane.showInputDialog("Podaj port:");
            String pelne_dane;
            final String LoginName;
            LoginName = JOptionPane.showInputDialog("Podaj nick:");
            final String adresP;
            adresP = JOptionPane.showInputDialog("Podaj adres:");
            pelne_dane = "Twoj nick: <" + LoginName + ">\nTwoj adres:<" + adresP + ">\nport:<" + portP + ">";
            System.out.println("_." + pelne_dane + "._\n" + "_." + LoginName + "._\n" + "_." + adresP + "._\n" + "_." + portP + "._\n");
            JOptionPane.showMessageDialog(null, pelne_dane);
            if ((LoginName != "" && adresP != "" && portP != "")) {
            	Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
							new ClientG(LoginName, adresP, portP, playerZ).displayGUI();
						} catch (Exception e) {
							
							e.printStackTrace();
							System.exit(-1);
						}
                    }
                };
                EventQueue.invokeLater(runnable);
                
                System.out.println("puste polaczenie");
                ClientG Client2 = new ClientG(LoginName, adresP, portP, playerZ);
                Client2.setup();
            } else {
                JOptionPane.showMessageDialog(null, "Brak danych, zamykam polaczenie.");
                System.exit(1);
                return;

            }
        } else {
            ClientG Client1 = new ClientG(args[0], args[1], args[2], args[3]);
            Client1.setup();
        }
    	}catch(Exception a1) {
            System.out.println(a1);
            System.exit(-1);
            }
      }

	@Override
	public void run() {
		try {
            while (true) {
                try {
                	String incommingMsg=din.readUTF();
                	t2.append(">" + incommingMsg + "<");
                	ekranLabel.append(">" + incommingMsg );
                    t1.append("\n>" + din.readUTF());

                } catch (Exception ex) {
                    t1.append("\n> Zerwano polaczenie z serwerem\n");
                    System.out.println("Zerwano polaczenie z serwerem");
                    try {
                        Thread.sleep(5019);
                    } catch (InterruptedException exit) {
                        System.out.println(exit);
                    }
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            return;
        }
		
	}
}