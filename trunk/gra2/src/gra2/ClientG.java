package gra2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.*;

/**Beautiful GUI for our GAME experience! */
public class ClientG extends Frame implements Runnable, ActionListener {

	 /**
     * @param soc odpowiedzialny za gniazdo klienta
     * @param tf Textfield do zczytywania polecen
     * @param ta TextArea do wypisywania komunikatow
     *
     */
	String imgFileName="ico.gif";
	ImageIcon icon=new ImageIcon(imgFileName);
	
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
            btn1 =new Button("1");
            btn2 =new Button("2");
            btn3 =new Button("3");
            btn4 =new Button("4");
            btnP =new Button("+1");
            btnM =new Button("-1");
            btn10P =new Button("+10");
            btn10M =new Button("-10");
            btnB =new Button("Bet");
            btnG=new Button("Go");
            
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new MyPanel();
        frame.getContentPane().setLayout(new FlowLayout());
        JButton test = new JButton("TEST");
        contentPane.add(test);
        test.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent klik1){
        		PopUpMyUltRightNow();
        	}
        });
        	
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    String PopUpMyUltRightNow(){
    	String score = JOptionPane.showInputDialog("How much do you want to bet?");
    	
    	return score;
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

    public static void main(String[] args) throws Exception {
    	try {
	if (args.length <= 2) {

				//ClientGUI Cl1=new ClientGUI("A","localhost","4444");
            //Cl1.setup();
        	
        	
        		int toTheExit=0;
            	//do {
            		
            	//playerZ=JOptionPane.showInputDialog("Przykro mi nie mozna uruchomic takiej rozgrywki!");
            		final String playerZ = JOptionPane.showInputDialog("Podaj ilosc graczy:");
                   // if(playerZ!="")continue;
                    //else{toTheExit++;}
            	//}while(toTheExit<=4 );//Integer.parseInt(playerZ)>6 || Integer.parseInt(playerZ)<2
                	
            
            final String portP;
            portP = JOptionPane.showInputDialog("Podaj port:");
            String pelne_dane;
          /*  try {
            	int portA = Integer.parseInt(portP);
            	Server ob = new Server(portA);
            } catch(Exception e1) {
            	System.out.println(e1);
            	System.out.println("Nie mozna na takim porcie utworzyc sto³u gry!");
            	return;
            }*/
            final String LoginName;
            LoginName = JOptionPane.showInputDialog("Podaj nick:");
            final String adresP;
            adresP = JOptionPane.showInputDialog("Podaj adres:");
            //Server ob = new Server(portA);
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
                ClientGUI Client2 = new ClientGUI(LoginName, adresP, portP, playerZ);
                Client2.setup();
            } else {
                JOptionPane.showMessageDialog(null, "Brak danych, zamykam polaczenie.");
                System.exit(1);
                return;

            }
        } else {
            ClientGUI Client1 = new ClientGUI(args[0], args[1], args[2], args[3]);
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


	@Override
	public void actionPerformed(ActionEvent arg0) {
		PopUpMyUltRightNow();
	}
}