package odnowa;

import java.net.*;
import java.util.*;
import java.io.*;
/*! \mainpage ServerKlient Instrukcja Obslugi
 *
 * \section intro_sec Introduction
 *
 * O - operacja
 * E - blad
 * R - rozklad liczby
 * P - czy pierwsza
 * M - minimalny dzielnik
 * Q - wyjscie
 *
 * \section install_sec Installation
 *
 * \subsection step1 Step 1: "java Server port rozklad liczby"
 * \subsection step2 Step 2: "java Client / java Client login adres port"
 * 
 */
/**Klasa g³ówna stworzona do utworzenia Servera */
class Server
{
	/**@param a zmienna statyczna utworzonoa do RozkladuLiczby */
	//public static RozkladLiczby a; //zamienic na deck
    static Vector ClientSockets;
    static Vector LoginNames;
    /**Konstruktor do zczytywania portu na ktorym ma sie komunikowac z klientami */
    Server(int portS) throws Exception
    {
		try{
			ServerSocket soc=new ServerSocket(portS);
			ClientSockets=new Vector();
			LoginNames=new Vector();

			while(true) //petla do przyjecia klientow
			{    
				Socket CSoc=soc.accept();        
				AcceptClient obClient=new AcceptClient(CSoc);
			}
		}catch(Exception e){return;}
    }
	/**Glowna metoda do uruchomienia Servera jako konsolowego programu */
    public static void main(String args[]) throws Exception
    {
        int portSS=Integer.parseInt(args[0]);
        Server ob=new Server(portSS);
		
		int sitko=Integer.parseInt(args[1]); //zmienic na tasowanie kart w decku
		try{
			a=new RozkladLiczby(sitko);
		}catch(RozkladException e){}
    }
	/**Klasa do Akceptowania polaczen i tworzenia Streamow z klientAmi */
	class AcceptClient extends Thread
	{// watki do servera
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;
		AcceptClient (Socket CSoc) throws Exception
		{
			try{ClientSocket=CSoc;

				din=new DataInputStream(ClientSocket.getInputStream());
				dout=new DataOutputStream(ClientSocket.getOutputStream());
			
				String LoginName=din.readUTF();
		
				System.out.println("Polaczenie rozpoczete z: " + LoginName);
				LoginNames.add(LoginName);
				ClientSockets.add(ClientSocket);    
				start();
			}catch(Exception e){System.out.println(e); return;}
		}
		/**Najwazniejsza metoda do oblugiwania watkow wewnatrz servera. Zczytuje informacje przesylane od klienta i przetwarza je w kolejne Operacje */
		public void run()
		{
			try{
				while(true)
				{
					//try{Thread.sleep(15000);}catch(InterruptedException e){}//Blokowanie spamu bledow.
					try
					{	if(){
						/**Obsluga Tokenow do rozrozniania OP od NUM */
						System.out.println("Oczekuje na klienta tutaj:"+InetAddress.getLocalHost()+"::"+InetAdress.getHostAdress+"\n<Aby przerwac wcisnij ctrl+C.>\n");}
						String msgFromClient=new String(); //TYM BEDZIEMY PRZEKAZYWALI TEN TOKEN TURY I KARTY
						msgFromClient=din.readUTF();
						System.out.println(msgFromClient); //TUTAJ PRZESYLAMY KARTY Z DANEJ TURY
						StringTokenizer st=new StringTokenizer(msgFromClient);
						String Sendto=st.nextToken(); 
						System.out.println(Sendto);
						String MsgType=st.nextToken();
						System.out.println(MsgType);
						
						int iCount=0;
						String msg2="";
						/*if(MsgType.equals("Q")) // TE CZESCI RACZEJ SIE NAM NIE PRZYDADZA Z TYMI OPCJAMI Q P R itd...
						{
							for(iCount=0;iCount<LoginNames.size();iCount++)
							{
								if(LoginNames.elementAt(iCount).equals(Sendto))
								{
									System.out.println("Przystepuje do zamykania watku");
									//ClientSockets.elementAt(iCount).close();
									LoginNames.removeElementAt(iCount);
									ClientSockets.removeElementAt(iCount);
									System.out.println("Polaczenie z: " + Sendto +" zakonczone.");
									
									continue;
								}
							}
		
						}/**P - isPrime() */
							/*		else if(MsgType.equals("P")){
									String msg="";
									 
									while(st.hasMoreTokens())
									{	
										System.out.println(Sendto+"Czy liczba(1)"+msg);
										msg=msg+" " +st.nextToken();
										msg2=msg;
										msg2=msg2.replace("P","");
										msg2=msg2.trim();
										int uMax=0;
										uMax=Integer.parseInt(msg2);
										Pierwsze.hain(msg2);
										
										System.out.println("jest Pierwsza?(2)"+msg);
									}*/
									for(iCount=0;iCount<LoginNames.size();iCount++)
									{
										if(LoginNames.elementAt(iCount).equals(Sendto))
										{    
											Socket tSoc=(Socket)ClientSockets.elementAt(iCount);                            
											DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
											tdout.writeUTF(msg);                            
											break;
										}
									}
									if(iCount==LoginNames.size())
									{
										System.out.println("Wysylam wynik.");
										dout.writeUTF("O "+Pierwsze.hain(msg2)); // tutaj podmienic najpierw na porownanie zestawow kart z rundy a potem rozeslanie nowych
										System.out.println("Zakonczono dzialanie.</"+Sendto+">");
									}
									else
									{
										
									}
										
								}
									/*/**Q - quit */
									/*else if(MsgType.equals("Q")){
									String msg="";
									 
									while(st.hasMoreTokens())
									{	
										System.out.println(Sendto+"Zamykam(1)"+msg);
										msg=msg+" " +st.nextToken();
										msg2=msg;
										msg2=msg2.replace("Q","");
										msg2=msg2.trim();
										int uMax=0;
										uMax=Integer.parseInt(msg2);
										Pierwsze.hain(msg2);
										
										System.out.println("Exit(2)"+msg);
									}*/
										for(iCount=0;iCount<LoginNames.size();iCount++)
										{//TO MIEJSCE SIE PRZYDA BO TRZEBA POZAMYKAC GNIAZDA PO GRZE
											if(LoginNames.elementAt(iCount).equals(Sendto))
											{
												System.out.println("Przystepuje do zamykania watku");
												//ClientSockets.elementAt(iCount).close();
												LoginNames.removeElementAt(iCount);
												ClientSockets.removeElementAt(iCount);
												System.out.println("Polaczenie z: " + Sendto +" zakonczone.");
												
												continue;
											}
										}
									/*	
									if(iCount==LoginNames.size())
									{
										System.out.println("Wysylam wynik.");
										dout.writeUTF("O "+Pierwsze.hain(msg2));
										System.out.println("Zakonczono dzialanie.</"+Sendto+">");
									}
									else
									{
										
									}*/
								/*}/**M - minimalny dzielnik */
							/*else if(MsgType.equals("M")){
							String msg="";
							 
							while(st.hasMoreTokens())
							{	
								System.out.println(Sendto+"Dzialanie minimalny dzielnik(1)"+msg);
								msg=msg+" " +st.nextToken();
								msg2=msg;
								msg2=msg2.replace("M","");
								msg2=msg2.trim();
								int uMax=0;
								uMax=Integer.parseInt(msg2);
								RozkladLiczbyTest.hain(msg2);
								
								System.out.println("Dzielnik(2)"+msg);
							}*/
							for(iCount=0;iCount<LoginNames.size();iCount++)
							{
								if(LoginNames.elementAt(iCount).equals(Sendto))
								{    
									Socket tSoc=(Socket)ClientSockets.elementAt(iCount);                            
									DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
									tdout.writeUTF(msg);                            
									break;
								}
							}
							if(iCount==LoginNames.size())
							{
								//dout.writeUTF("O "+Min.nain(msg2));
								System.out.println("Zakonczono dzialanie.</"+Sendto+">");
							}
							else
							{
								
							}
								
						}/**R - pelen rozklad liczby na czynniki pierwsze i wyswietlenie ich */
						else if(MsgType.equals("R")){
							String msg="";
							 
							while(st.hasMoreTokens())
							{	
								System.out.println(Sendto+"Dzialanie pelnego rozkladu(1)"+msg);
								msg=msg+" " +st.nextToken();
								msg2=msg;
								msg2=msg2.replace("R","");
								System.out.println(""+msg2);
								msg2=msg2.trim();
								System.out.println(""+msg2);
								int uMax=0;
								uMax=Integer.parseInt(msg2);
								RozkladLiczbyTest.hain(msg2);
								
								System.out.println("Dzialanie rozklad(2)"+msg);
							}
							for(iCount=0;iCount<LoginNames.size();iCount++)
							{
								if(LoginNames.elementAt(iCount).equals(Sendto))
								{    
									Socket tSoc=(Socket)ClientSockets.elementAt(iCount);                            
									DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
									tdout.writeUTF(msg);                            
									break;
								}
							}
							if(iCount==LoginNames.size())
							{
								System.out.println("Wysylam wynik.");
								dout.writeUTF("O "+RozkladLiczbyTest.hain(msg2));
								System.out.println("Zakonczono dzialanie.</"+Sendto+">");
							}
							else
							{
								
							}
								
						}
						else
						{
							String msg="";
							while(st.hasMoreTokens())
							{	
								System.out.println(Sendto+"Dzialanie blednie okreslone(1)"+msg);
								msg=msg+" " +st.nextToken();
								System.out.println("Dzialanie watku PUSTE(2)"+msg);
							}
							for(iCount=0;iCount<LoginNames.size();iCount++)
							{
								if(LoginNames.elementAt(iCount).equals(Sendto))
								{    
									Socket tSoc=(Socket)ClientSockets.elementAt(iCount);                            
									DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
									tdout.writeUTF(msg);                            
									break;
								}
							}
							if(iCount==LoginNames.size())
							{
								System.out.println("Brak danych");
								dout.writeUTF("E Akcja bez danych");
								System.out.println("Zakonczono dzialanie.</"+Sendto+">");
							}
							else
							{
								
							}
						}
						if(MsgType.equals("BREAK"))
						{
							break;
						}

					}
					catch(Exception ex)
					{
						//ex.printStackTrace();
						//System.out.println("Zamykam: Bledne polaczenie");
						
						continue;
					}
				
				
				
				}	    
			}catch(Exception e){System.out.println("Zglaszam blad i zamykam polaczenia.");return;}
		}
		/**Metoda do zakonczenia bezpiecznie polaczenia servera z klientami */
		protected void finalize() {
		try {
			System.out.println("Koniec polaczenia");
		  din.close();
		  dout.close();
		  ClientSocket.close();
		  //soc.close();
		  
		} 
		catch (IOException e) {
		  System.out.println("Nie moge zamknac polaczenia."); System.exit(-1);
		}
	  }
	}

}
