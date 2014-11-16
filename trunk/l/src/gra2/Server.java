package gra2;

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

/**
 * Klasa g³ówna stworzona do utworzenia Servera
 */
class Server {

    /**
     * @param a zmienna statyczna utworzonoa do RozkladuLiczby
     */
    //public static RozkladLiczby a; //zamienic na deck
    static Vector ClientSockets;
    static Vector LoginNames;

    /**
     * Konstruktor do zczytywania portu na ktorym ma sie komunikowac z klientami
     */
    Server(int portS) throws Exception {
        try {
            ServerSocket soc = new ServerSocket(portS);
            ClientSockets = new Vector();
            LoginNames = new Vector();

            while (true) //petla do przyjecia klientow
            {
                Socket CSoc = soc.accept();
                AcceptClient obClient = new AcceptClient(CSoc);
            }
        } catch (Exception e) {
            System.out.println("Wyjatek: " + e);
            return;
        }
    }

    /**
     * Glowna metoda do uruchomienia Servera jako konsolowego programu
     */
    public static void main(String args[]) throws Exception {
        // int portSS=Integer.parseInt(args[0]);
        int portSS = 8969;
        Server ob = new Server(portSS);
        //int sitko=Integer.parseInt(args[1]); //zmienic na tasowanie kart w decku
        System.out.println("Startuje!");
        /*try{
         a=new RozkladLiczby(sitko);
         }catch(RozkladException e){}
         */
    }

    /**
     * Klasa do Akceptowania polaczen i tworzenia Streamow z klientAmi
     */
    class AcceptClient extends Thread {// watki do servera

        Socket ClientSocket;
        DataInputStream din;
        DataOutputStream dout;
        private boolean isStopped = false;

        AcceptClient(Socket CSoc) throws Exception {
            try {
                ClientSocket = CSoc;

                din = new DataInputStream(ClientSocket.getInputStream());
                dout = new DataOutputStream(ClientSocket.getOutputStream());

                String LoginName = din.readUTF();

                System.out.println("Polaczenie rozpoczete z: " + LoginName);
                LoginNames.add(LoginName);
                ClientSockets.add(ClientSocket);
                start();
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }

        private boolean isStopped() {
            return isStopped;
        }

        /**
         * Najwazniejsza metoda do oblugiwania watkow wewnatrz servera. Zczytuje
         * informacje przesylane od klienta i przetwarza je w kolejne Operacje
         */
        public void run() {
            try {
                do // zamienic na do{}while(Connection_is_UP);
                {
                    try {
                        if (true) {
                        	try {
                        		Thread.sleep(15000);
                        		} catch (InterruptedException e) {
                        			System.out.println(e+"_Spam bledow?");
                        		}//Blokowanie spamu bledow.
                            /**
                             * Obsluga Tokenow do rozrozniania OP od NUM
                             */
                            System.out.println("Oczekuje na klienta tutaj:" + InetAddress.getLocalHost() + "::" +/*InetAdress.getHostAdress+*/ "\n<^C--^C--^C>\n");
                        }
                        String msgFromClient = new String(); //TYM BEDZIEMY PRZEKAZYWALI TEN TOKEN TURY I KARTY
                        msgFromClient = din.readUTF();
                        System.out.println(msgFromClient); //TUTAJ PRZESYLAMY KARTY Z DANEJ TURY
                        StringTokenizer st = new StringTokenizer(msgFromClient);
                        String sendTo = st.nextToken();
                        System.out.println(sendTo);
                        String msgType = st.nextToken();
                        System.out.println(msgType);
                        int iCount = 0;
                        String msg1 = "Karta testowa";
                        String msg2 = "";
                        for (iCount = 0; iCount < LoginNames.size(); iCount++) {
                            if (LoginNames.elementAt(iCount).equals(sendTo)) {
                                System.out.println(msgFromClient);
                                if (msgFromClient.equals(msgFromClient)) {
                                    System.out.println("Przyjalem!! @");
                                }
                                Socket tSoc = (Socket) ClientSockets.elementAt(iCount);
                                DataOutputStream tdout = new DataOutputStream(tSoc.getOutputStream());
                                tdout.writeUTF(msg1);
                                break;
                            }
                        }
                        if (iCount == LoginNames.size()) {
                            System.out.println("Wysylam wynik.");
                            dout.writeUTF("P2 " +/*Pierwsze.hain(msg2)*/ "tak to ja"); // tutaj podmienic najpierw na porownanie zestawow kart z rundy a potem rozeslanie nowych
                            System.out.println("1Zakonczono dzialanie.</" + sendTo + ">");
                        }
                        for (iCount = 0; iCount < LoginNames.size(); iCount++) {//TO MIEJSCE SIE PRZYDA BO TRZEBA POZAMYKAC GNIAZDA PO GRZE
                            if (LoginNames.elementAt(iCount).equals(sendTo)) {
                                System.out.println("Przystepuje do zamykania watku");
                                //ClientSockets.elementAt(iCount).();
                                LoginNames.removeElementAt(iCount);
                                ClientSockets.removeElementAt(iCount);
                                System.out.println("Polaczenie z: " + sendTo + " zakonczone.");

                                continue;
                            }
                        }

                        for (iCount = 0; iCount < LoginNames.size(); iCount++) {
                            if (LoginNames.elementAt(iCount).equals(sendTo)) {
                                Socket tSoc = (Socket) ClientSockets.elementAt(iCount);
                                DataOutputStream tdout = new DataOutputStream(tSoc.getOutputStream());
                                tdout.writeUTF(msg1);
                                break;
                            }
                        }
                        if (iCount == LoginNames.size()) {
                            //dout.writeUTF("O "+Min.nain(msg2));
                            System.out.println("2Zakonczono dzialanie.</" + sendTo + ">");
                        } else {

                        }

                        /*R - pelen rozklad liczby na czynniki pierwsze i wyswietlenie ich */
                        if (msgType.equals("R")) {
                            String msg = "";

                            while (st.hasMoreTokens()) {
                                System.out.println(sendTo + "Dzialanie pelnego rozkladu(1)" + msg);
                                msg = msg + " " + st.nextToken();
                                msg2 = msg;
                                msg2 = msg2.replace("R", "");
                                System.out.println("" + msg2);
                                msg2 = msg2.trim();
                                System.out.println("" + msg2);
								//int uMax=0;
                                //uMax=Integer.parseInt(msg2);
								/*RozkladLiczbyTest.hain(msg2);*/

                                System.out.println("Dzialanie rozklad(2)" + msg);
                            }
                            for (iCount = 0; iCount < LoginNames.size(); iCount++) {
                                if (LoginNames.elementAt(iCount).equals(sendTo)) {
                                    Socket tSoc = (Socket) ClientSockets.elementAt(iCount);
                                    DataOutputStream tdout = new DataOutputStream(tSoc.getOutputStream());
                                    tdout.writeUTF(msg);
                                    break;
                                }
                            }
                            if (iCount == LoginNames.size()) {
                                System.out.println("Wysylam wynik.");
                                dout.writeUTF("O "/*+RozkladLiczbyTest.hain(msg2)*/);
                                System.out.println("3Zakonczono dzialanie.</" + sendTo + ">");
                            } else {

                            }
                        }
                        if (msgType.equals("BREAK")) {
                            break;
                        }
                    } catch (Exception ex) {
						//ex.printStackTrace();
                        //System.out.println("Zamykam: Bledne polaczenie");

                        continue;
                    }
                } while (!isStopped());
            } catch (Exception e) {
                System.out.println("Zglaszam blad i zamykam polaczenia.");

			//socket.close();
                //serverSocket.close();
                return;
            }
        }
        /**
         * Metoda do zakonczenia bezpiecznie polaczenia servera z klientami
         */
        protected void finalize() {
            try {
            	this.isStopped = true;
                System.out.println("Koniec polaczenia");
                din.close();
                dout.close();
                ClientSocket.close();
                //soc.close();
            } catch (IOException e) {
                System.out.println("Nie moge zamknac polaczenia.");
                System.exit(-1);
            }
        }
    }

}
