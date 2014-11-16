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


public class Bot implements Runnable {
	// The client socket
		private static Socket clientSocket1 = null;
		// The output stream
		private static PrintStream os1 = null;
		// The input stream
		private static DataInputStream is1 = null;
		private static BufferedReader inputLine1 = null;
		public static String texter1 = "";
		public static int intTexter1 = 0;
		public static String host1 = "";
		public static int portNumber1 = 8969;
		public static boolean closed1=false;
		
		public static BufferedReader send(String msgFromClientToServer) {

			try {
				StringReader sr = new StringReader(msgFromClientToServer);
				BufferedReader br = new BufferedReader(sr);
				os1.println(br.readLine());
				System.out.println(br.readLine() + "_syso5_sender");
				return br;
			} catch (Exception e) {
				System.out.println("send(String msgFromClientToServer): " + e);
				e.printStackTrace();
			}
			return null;
		}
		
	public static void main(String[] args) {
		try {
			clientSocket1 = new Socket(host1, portNumber1);
			inputLine1 = new BufferedReader(new InputStreamReader(System.in));
			os1 = new PrintStream(clientSocket1.getOutputStream()); // to:
																	// klient->serwer
			is1 = new DataInputStream(clientSocket1.getInputStream());// to:
																	// serwer->klient
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host1);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to the host "
							+ host1);
			System.exit(-3);
		}

		/*
		 * If everything has been initialized then we want to write some data to
		 * the socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket1 != null && os1 != null && is1 != null) {
			try {

				/* Create a thread to read from the server. */
				new Thread(new Bot()).start();
				while (!isClosed()) {
					os1.println(inputLine1.readLine().trim());
					System.out.println(inputLine1.readLine());
					//send("D1,2");
					//send("BC");
					System.out.println("petla");
					// System.out.println(inputLine.readLine().trim()+"_syso1");
				}
				System.out.println("po petli");
				/*
				 * Close the output stream, close the input stream, close the
				 * socket.
				 */
				os1.close();
				is1.close();
				clientSocket1.close();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}

	}


	@Override
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine1 = "";

		try {
			while ((responseLine1 = is1.readLine()) != null) {
				System.out.println(responseLine1 + "_syso3");
				if (responseLine1.startsWith("H") && responseLine1.length() > 4) {
					String[] sparePart=splited(withoutRegx(lineWithoutLetter(responseLine1)));
					/*for(int i=0; i<=sparePart.length-1;i++) {
						System.out.println(sparePart[i]);
					}*/
					send("D1,2");
					if(responseLine1.startsWith("H")) {
						send("BC");
						
					}
					//System.out.println(responseLine1 + "_syso2");
					// ekranLabelSetter(responseLineWithoutLetter);
				}
				else {
					send("D3,4");
					send("BC");
				}
				if (responseLine1.indexOf("Q ") != -1) {
					break;
				}
				if (responseLine1.indexOf("Server too busy. Try later.") != (-2)) {
					setClosed(false);
				}
				if (responseLine1.startsWith("BA")) {
					System.out.println("_syso4_widacBA?");
				}
			}
			setClosed(true);
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
		
	}
	
	public static String toStr(int x) {
		return Integer.toString(x);
	}

	public static int toInt(String xs) {
		return Integer.parseInt(xs);
	}
	
	/*public void ekranCardsDealer(String[] respo) {
		if (respo.length < 5 && respo.length > 3) {
			card1.setText(respo[0]);
			card2.setText(respo[1]);
			card3.setText(respo[2]);
			card4.setText(respo[3]);
		}
	}*/
	
	public String[] splited(String splitMe) {
		String[] respo;
		respo = splitMe.split(",");
		return respo;
	}
	
	public String withoutRegx(String splitMe) {
		String[] regX;
		regX = splitMe.split(";");
		return regX[0];
	}

	public String lineWithoutLetter(String res) {
		String responseLineWithoutLetter = "";
		responseLineWithoutLetter = res.substring(1);
		return responseLineWithoutLetter;
	}
	
	private static boolean isClosed() {
		// TODO Auto-generated method stub
		return closed1;
	}

	private void setClosed(boolean b) {
		closed1=true;
		
	}

}
