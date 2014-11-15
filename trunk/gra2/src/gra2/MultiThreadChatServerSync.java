package gra2;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

/*
 * A chat server that delivers public and private messages.
 */
public class MultiThreadChatServerSync {
	public static int playersQuantity;
	public static int players;
  // The server socket.
  private static ServerSocket serverSocket = null;
  // The client socket.
  private static Socket clientSocket = null;
  private static Table tableG;
  // This chat server can accept up to maxClientsCount clients' connections.
  private static final int maxClientsCount = 6;
  private static final clientThread[] threads = new clientThread[maxClientsCount];

  public static void main(String args[]) {

    // The default port number.
    int portNumber = 8969;
    if (args.length < 1) {
      System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
          + "Now using port number=" + portNumber);
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }
    
    
    String playerZ;
    playerZ = JOptionPane.showInputDialog("Podaj ilosc graczy:");
    try{
    	while(Integer.parseInt(playerZ)>6 || Integer.parseInt(playerZ)<2 )
    	{
    	playerZ=JOptionPane.showInputDialog("Przykro mi nie mozna uruchomic takiej rozgrywki!");
    	
    	}
    }catch(Exception a1) {
    System.out.println(a1);
    System.exit(-1);
    }
    String portP;
    portP = JOptionPane.showInputDialog("Podaj port:");
    String pelne_dane;
   // String LoginName;
   // LoginName = JOptionPane.showInputDialog("Podaj nick:");
    String tokenVault;
    tokenVault = JOptionPane.showInputDialog("Podaj ilosc zetonow w tej rozgrywce:");
    // String adresP;
    // adresP = JOptionPane.showInputDialog("Podaj adres:");
    //	Server ob = new Server(portA);
    pelne_dane = /*"Nickname: <" + LoginName +*/"\nport:<" + portP + ">" + ">\nplayers:<" + playerZ + ">" + ">\ntokens:<" + tokenVault + ">";
    System.out.println( pelne_dane );
    JOptionPane.showMessageDialog(null, pelne_dane);
    portNumber = toInt(portP);
    
    playersQuantity = toInt(playerZ);
    tableG=new Table(playersQuantity,toInt(tokenVault));
    /*
     * Open a server socket on the portNumber (default 8969). Note that we can
     * not choose a port less than 1023 if we are not privileged users (root).
     */
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new clientThread(clientSocket, threads,tableG,i)).start();
            break;
          }
        }
        if (i == maxClientsCount) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Server too busy. Try later.");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
  
  static int toInt(String str) { //PARSES STRING TO INT
	  try {
	  int iStr = Integer.parseInt(str);
	  return iStr;
	  } catch (Exception e) {
		  System.out.println("Nie mozna zamienic danego stringa na int" + e);
	  }
	  return -1;
  }
}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class clientThread extends Thread {

  private String clientName = null;
  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int maxClientsCount;
  private int whichPlayer;
  private Table table;

  public clientThread(Socket clientSocket, clientThread[] threads, Table table, int whichPlayer) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClientsCount = threads.length;
    this.table=table;
    this.whichPlayer=whichPlayer+1;
  }

  public void run() {
    int maxClientsCount = this.maxClientsCount;
    clientThread[] threads = this.threads;

    try {
      /*
       * Create input and output streams for this client.
       */
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      String name;
      String inMsg;
      boolean tillEnd=false;
      while (true) {
    	  while (true) {
    	        os.println("Enter your name.");
    	        name = is.readLine().trim();
    	        if (name.indexOf('@') == -1) {
    	          break;
    	        } else {
    	          os.println("The name should not contain '@' character.");
    	        }
    	      }
    	  while(!tillEnd){
    		  synchronized(this){
    		  if( table.getTura()==0 && table.getDealer()==whichPlayer ){
    			  table.deal();
    			  table.incTura();  
    		  	}
    		  sendPriv(this, table.getHand(whichPlayer));
    		  }
    		 while(table.getTura()==1) {
    			synchronized(this){
    			 if(table.getCurrentPlayer()==whichPlayer && table.getPlayerStatus(whichPlayer) == 'T') {
    				 while(true) {
    					 inMsg=is.readLine();
    					 if(inMsg.startsWith("D")) {
    					 sendPriv(this, table.drow(whichPlayer,inMsg));
    					// table.incCurrentPlayer();
    	    			  break;
    					 }
    				 }
    			 }
    			 }
    			/*
    			if(table.getCurrentPlayer()==whichPlayer &&(table.getPlayerStatus(whichPlayer)!='A' || table.getPlayerStatus(whichPlayer)!='F')) {
    				while(true) {
    					inMsg=is.readLine();
    					if(inMsg.startsWith("B")) {
    						
    					}
    				}
    			}*/
    		 }
    		  
    	  }
      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
      synchronized (this) {
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] == this) {
            threads[i] = null;
          }
        }
      }
      /*
       * Close the output stream, close the input stream, close the socket.
       */
      is.close();
      os.close();
      clientSocket.close();
      }
     } catch (IOException e) {
    }
  }
  public void sendAll (String messange)
  {
      for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null && threads[i].clientName != null) {
            threads[i].os.println(messange);
          }
        }
  }
  public void sendPriv (clientThread thread,String messange)
  {
	  thread.os.println(messange);
  }
}
