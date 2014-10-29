import java.util.ArrayList;
import java.util.List;


public class Table {
	private byte[] playerHands;
	private Bank bank;
	private Deck deck;
	private char[] playerStatus;
	private List<Byte> returnCards;
	private byte players;
	
	Table(byte players,int startMoney)
	{
		playerHands=new byte[(players*4)+1];
		bank=new Bank(players,startMoney);
		deck=new Deck();
		this.players=players;
		returnCards=new ArrayList<Byte>();
		playerStatus=new char[players+1];
		//returnCards.add((byte) 1);
	}
	//obsluga decka
///////////////////////////////////////////////////	
	public void deal()//rozdanie
	{
		deck.shuffling();
		for(int i=playerHands.length-1;i>0;i--)
		{
			playerHands[i]=deck.drow();
		}
	}
	
	private void change(byte player,byte card)//prywatna klasa do zmiany karty
	{
		byte cardP=(byte) ((4*player)+(card-4));
		if(returnCards.size()>50)rCardToDeck();
		returnCards.add(playerHands[cardP]);
		playerHands[cardP]=deck.drow();
	}
	
	public byte changeCard(byte player,byte card)//zmiana karty
	{
		change(player,card);
		return playerHands[((4*player)+(card-4))];
		
	}
	
	private void rCardToDeck()//zwracanie kart do decku 
	{
		byte[] temp=new byte[returnCards.size()];
		for(int i = 0; i < returnCards.size(); i++)
		{
			temp[i] = returnCards.get(i);
		}
		returnCards.clear();
		deck.returnCard(temp);
	}
//////////////////////////////////////////////////////	
	public byte[] test()
	{
		return playerHands;
	}
	
	public byte[] test2()
	{
		return deck.send();
	}
	
	
	
}
