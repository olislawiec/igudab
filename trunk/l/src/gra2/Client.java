package gra2;
//Chat client

import java.net.*;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.Icon;

import java.awt.Image;

/**
 * Klasa Client sluzaca do Implementowania jako Watek Kliencki.
 */
@SuppressWarnings({"serial", "unused"})
class Client extends Frame implements Runnable {

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

    /**
     * Glowny konstruktor do Watku klienta
     *
     * @throws Exception rzuca wyjatkiem w razie niepowodzenia przy
     * konstruowaniu GUI klienta
     */
    Client(String LoginName, String adres, String port, String players) throws Exception {
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

    /**
     * Ustawia szybkie setup do GUI
     */
    @SuppressWarnings("deprecation")
    void setup() {
        setSize(600, 400);
        
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
        
        p.add(new ImageComponent("C:/Users/Arkadiusz/Bad2/gra2/src/gra2/ico.gif"));
        
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
                    //System.exit(1);
                } catch (Exception ex) {
                }
                System.exit(0);
            }
        });
    }
    

    class ImageComponent extends Component {

    	//Image img;
        BufferedImage img;

        public void BackgroundPanel()
        {
            // Loads the background image and stores in img object.
            img = (BufferedImage) Toolkit.getDefaultToolkit().createImage("C:/Users/Arkadiusz/Bad2/gra2/src/gra2/ico.gif");
        }
        public void paint(Graphics g) {
           g.drawImage(img, 0, 0, null);
        }

        public ImageComponent(String path) {
           try {
              img = ImageIO.read(new File(path));
           } catch (IOException e) {
              e.printStackTrace();
           }
        }

        public Dimension getPreferredSize() {
           if (img == null) {
              return new Dimension(100,100);
           } else {
              return new Dimension(img.getWidth(), img.getHeight());
           }
        }
     }
    /**
     * Obsluga wyjatkow bezposrednio z klawiszy SEND i CLOSE
     */
    @SuppressWarnings("deprecation")
    public boolean action(Event e, Object o) {
        if (e.arg.equals("Send")) {
            try {
                String komenda = "";
                komenda = tf.getText().toString();
                t1.append("\n" + komenda + "\n");
                if (komenda.equals("ADRES")) {
                    tf.setText("");
                    String playerZ;
                    playerZ = JOptionPane.showInputDialog("Podaj ilosc graczy:");
                    String adresP;
                    adresP = JOptionPane.showInputDialog("Podaj adres:");
                    String portP;
                    portP = JOptionPane.showInputDialog("Podaj port:");
                    String pelne_dane;
                    pelne_dane = "Twoj adres:<" + adresP + "> i port<" + portP + ">";
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, pelne_dane);
                    //System.exit(0);
                    t1.append("\n" + "Przekazano polaczenie z: " + LoginName + " do " + "ReMake\n");
                    try {
                        dout.writeUTF(LoginName + " Q");
                        //System.exit(1);
                    } catch (Exception ex) {
                    }
                    try {
                        Client Client2 = new Client("ReMake", adresP, portP, playerZ);
                        Client2.setup();
                    } catch (Exception ex) {
                        System.out.println("GUI");
                    }
                    din = new DataInputStream(soc.getInputStream());
                    dout = new DataOutputStream(soc.getOutputStream());
                } else {
                    dout.writeUTF(sendTo + " " + tf.getText().toString());
                    t1.append("\n" + LoginName + ":" + tf.getText().toString());
                    tf.setText("");
                }
            } catch (Exception ex) {System.out.println(ex);
            }
        } else if (e.arg.equals("Close")) {
            try {
                dout.writeUTF(LoginName + " Q");
                System.exit(1);
            } catch (Exception ex) {
            }
        }
        return super.action(e, o);
    }

    /**
     * Glowna metoda MAIN do uruchomienia jako GUI z konsoli
     */
    public static void main(String args[]) throws Exception {
        if (args.length <= 2) {
				//Client Cl1=new Client("A","localhost","4444");
            //Cl1.setup();
        	String playerZ;
            playerZ = JOptionPane.showInputDialog("Podaj ilosc graczy:");
            String portP;
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
            String LoginName;
            LoginName = JOptionPane.showInputDialog("Podaj nick:");
            String adresP;
            adresP = JOptionPane.showInputDialog("Podaj adres:");
            //Server ob = new Server(portA);
            pelne_dane = "Twoj nick: <" + LoginName + ">\nTwoj adres:<" + adresP + ">\nport:<" + portP + ">";
            System.out.println("_." + pelne_dane + "._\n" + "_." + LoginName + "._\n" + "_." + adresP + "._\n" + "_." + portP + "._\n");
            JOptionPane.showMessageDialog(null, pelne_dane);
            if ((LoginName != "" && adresP != "" && portP != "")) {
                System.out.println("puste polaczenie");
                Client Client2 = new Client(LoginName, adresP, portP, playerZ);
                Client2.setup();
            } else {
                JOptionPane.showMessageDialog(null, "Brak danych, zamykam polaczenie.");
                System.exit(1);
                return;

            }
        } else {
            Client Client1 = new Client(args[0], args[1], args[2], args[3]);
            Client1.setup();
        }

    }

    /**
     * Metoda do przebiegu Obslugi watkow i Streamowania tekstu
     */
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
}
