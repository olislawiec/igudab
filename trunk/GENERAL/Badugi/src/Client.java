//package odnowa;
//Chat client
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**Klasa Client sluzaca do Implementowania jako Watek Kliencki */
class Client extends Frame implements Runnable
{
/**@param soc odpowiedzialny za gniazdo klienta
@param tf Textfield do zczytywania polecen
@param ta TextArea do wypisywania komunikatow


//ciekawe co zrobimy z tymi punktami w ktorych dwa razy sa wypisane parametry wejsciowe gry, najpierw ilosc graczy i tokenow
// a potem opisane, ze stawki(wysokosci) obu zakladow w ciemno tez sa parametrami wejsciowymi gry
//tak mysle, ze trzeba by bylo na serverze zrobic samowolke i pozwolic pierwszemu klientowi zdecydowac ilu graczy bedzie gralo z nim mecza
//Do tego zauwazyc, czy zawsze dwie pierwsze osoby maja stawke w ciemno, bo nie ogarniam tej gry...
//I wtedy dac po prostu, ze drugi i trzeci ruch musi jakas stawke wyslac... A jako, ze nie wiem co znaczy w ciemno to nie wiem nawet co pisac..



*/
 Socket soc;    
 TextField tf;
 TextArea ta;
 Button btnSend,btnClose;
 String sendTo;
 String LoginName;
 Thread t=null;
 DataOutputStream dout;
 DataInputStream din;
	
	/*Client()throws Exception
	{
		super("Ghost");
		try{
		 t=new Thread(this);
		 
     t.start();
		
				String adresP;
				adresP = JOptionPane.showInputDialog("Podaj adres:");
				
				String portP;
				portP = JOptionPane.showInputDialog("Podaj port:");
				
				String pelne_dane;
				pelne_dane = "Twoj adres:<" + adresP + "> i port<" + portP+">";
				setVisible(false);
				
				JOptionPane.showMessageDialog(null, pelne_dane);
				//System.exit(0);
				
				try{
				Client Client2=new Client("ReMake",adresP,portP);
				Client2.setup();
				}catch(Exception ex){System.out.println("GUI");}
				din=new DataInputStream(soc.getInputStream()); 
				dout=new DataOutputStream(soc.getOutputStream()); 
		}catch(Exception e){return;}
	}	*/
 /** Glowny konstruktor do Watku klienta
	@throws Exception rzuca wyjatkiem w razie niepowodzenia przy konstruowaniu GUI klienta*/
	Client(String LoginName,String adres,String port) throws Exception
 {
		super(LoginName);
		try{
			int portZ=Integer.parseInt(port);
    
			this.LoginName=LoginName;
			sendTo=port;
			tf=new TextField(50);
			ta=new TextArea(50,50);
			btnSend=new Button("Send");
			btnClose=new Button("Close");
			soc=new Socket(adres,portZ);

			din=new DataInputStream(soc.getInputStream()); 
			dout=new DataOutputStream(soc.getOutputStream());        
			dout.writeUTF(LoginName);

			t=new Thread(this);
			t.start();
		}catch(Exception e){return;}

 }
	/**Ustawia szybkie setup do GUI */
 void setup()
 {
     setSize(600,400);
		setVisible(true);
     setLayout(new GridLayout(2,1));
		Panel p=new Panel();
		p.add(tf);
     p.add(btnSend);
     p.add(btnClose);
		 add(p);
     add(ta);
     
     
    
    
     show();       
		ta.append("ADRES - by polaczyc z ADRESEM i PORTEM\n P - by sprawdzic czy liczba jest pierwsza\n M - by uzyskac minimalny dzielnik\n R - by otrzymac rozklad liczby na czynniki pierwsze\n Przycisk CLOSE - by wyjsc (Q)\n >O/E - komunikaty ze strony serwera\n");
			addWindowListener( new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
								try
								{
									dout.writeUTF(LoginName + " Q");
									//System.exit(1);
								}
								catch(Exception ex)
								{
								}
                              System.exit(0);
                            }
                          } );
 }
	/**Obsluga wyjatkow bezposrednio z klawiszy SEND i CLOSE */
 public boolean action(Event e,Object o)
 {
     if(e.arg.equals("Send"))
     {
         try
         {
			String komenda="";
			komenda=tf.getText().toString();
			ta.append("\n"+komenda+"\n");
			if(komenda.equals("ADRES")){
				tf.setText("");
				String adresP;
				adresP = JOptionPane.showInputDialog("Podaj adres:");
				
				String portP;
				portP = JOptionPane.showInputDialog("Podaj port:");
				
				String pelne_dane;
				pelne_dane = "Twoj adres:<" + adresP + "> i port<" + portP+">";
				setVisible(false);
				
				JOptionPane.showMessageDialog(null, pelne_dane);
				//System.exit(0);
				ta.append("\n"+"Przekazano polaczenie z: "+LoginName+" do "+ "ReMake\n");
				try
				{
					dout.writeUTF(LoginName + " Q");
					//System.exit(1);
				}
				catch(Exception ex)
				{
				}
				try{
				Client Client2=new Client("ReMake",adresP,portP);
				Client2.setup();
				}catch(Exception ex){System.out.println("GUI");}
				din=new DataInputStream(soc.getInputStream()); 
				dout=new DataOutputStream(soc.getOutputStream()); 
						
			}else{
             dout.writeUTF(sendTo + " " + tf.getText().toString());
             ta.append("\n" + LoginName + ":" + tf.getText().toString());
             tf.setText("");
			}
         }
         catch(Exception ex)
         {
         }    
     }
     else if(e.arg.equals("Close"))
     {
         try
         {
             dout.writeUTF(LoginName + " Q");
             System.exit(1);
         }
         catch(Exception ex)
         {
         }
         
     }
     
     return super.action(e,o);
 }/**Glowna metoda MAIN do uruchomienia jako GUI z konsoli */
 public static void main(String args[]) throws Exception
 {
		if(args.length<=2){
				//Client Client1=new Client("Anonim","localhost","4444");
				//Client1.setup();
		
				String LoginName;
				LoginName=JOptionPane.showInputDialog("Podaj nick:");
				
				String adresP;
				adresP = JOptionPane.showInputDialog("Podaj adres:");
				
				String portP;
				portP = JOptionPane.showInputDialog("Podaj port:");
				
				String pelne_dane;
				pelne_dane ="Twoj nick: <"+ LoginName+ ">\nTwoj adres:<" + adresP + ">\nport:<" + portP+">";
				System.out.println("_."+pelne_dane+"._\n"+"_."+LoginName+"._\n"+"_."+adresP+"._\n"+"_."+portP+"._\n");
				JOptionPane.showMessageDialog(null, pelne_dane);
				if((LoginName!=""&&adresP!=""&&portP!="")){
				System.out.println("puste polaczenie");
				Client Client2=new Client(LoginName,adresP,portP);
				Client2.setup();
				}
				else{
				JOptionPane.showMessageDialog(null,"Brak danych, zamykam polaczenie.");
				System.exit(1);
				return;
		
				}
		}else{
			Client Client1=new Client(args[0],args[1],args[2]);
			Client1.setup();
		}
			
 }    
	/**Metoda do przebiegu Obslugi watkow i Streamowania tekstu */
 public void run()
 {        try{
     while(true)
     {
         try
         {
             ta.append( "\n>" + din.readUTF());
             
         }
         catch(Exception ex)
         {
				ta.append( "\n> Zerwano polaczenie z serwerem\n");
				System.out.println("Zerwano polaczenie z serwerem");
				try{Thread.sleep(20000);}catch(InterruptedException exit){}
             System.exit(0);
         }
     }}catch(Exception e){return;}
 }
}