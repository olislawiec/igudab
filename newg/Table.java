import java.util.ArrayList;
import java.util.List;


public class Table {
	private int[] playerHands;
	private Bank bank;
	private Deck deck;
	private Rules rules;
	private char[] playerStatus;
	private List<Integer> returnCards;
	private int players;
	private String Messange;
	
	Table(int players,int startMoney)
	{
		playerHands=new int[(players*4)+1];
		bank=new Bank(players,startMoney);
		deck=new Deck();
		rules= new Rules(players);
		this.players=players;
		returnCards=new ArrayList<Integer>();
		playerStatus=new char[players+1];
		//returnCards.add((int) 1);
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
	
	private void changeCard(int player,int card)//prywatna klasa do zmiany karty
	{
		int cardP=(int) ((4*player)+(card-4));
		if(returnCards.size()>(50-(players*4)))rCardToDeck();
		returnCards.add(playerHands[cardP]);
		playerHands[cardP]=deck.drow();
	}
	
	
	private void rCardToDeck()//zwracanie kart do decku 
	{
		int[] temp=new int[returnCards.size()];
		for(int i = 0; i < returnCards.size(); i++)
		{
			temp[i] = returnCards.get(i);
		}
		returnCards.clear();
		deck.returnCard(temp);
	}
	
	public String getHand(int player)
	{
		String send="H";
		for(int i=1;i<=4;i++)
		{
			send+=playerHands[((4*player)+(i-4))];
			if(i!=4)send+=",";
		}
		send+=";";
		return send;
	}
	
	public String drow(int player,String order)
	{
		String temp="";
		for(int i=1;i<order.length();i++)
		{
			if(order.charAt(i)==',')continue;
			temp=""+order.charAt(i);
			changeCard(player,Integer.parseInt(temp));
		}
		
		return getHand(player);
	}
//////////////////////////////////////////////////////	
	
	private int choseWinner()
	{
		return rules.win(playerHands);
		
	}
	
	private void Money4Winner(int player)
	{
		if(player !=0)bank.win(player);
		else bank.draw();
	}
	
	public String Winner()
	{
		int playerWin=choseWinner();
		Money4Winner(playerWin);
		
		if(playerWin==0)return "Draw";
		else return "Player: "+playerWin+" win";
		
	}

////////////////////////////////////////////////////
	
	public void bet(int player,String action)
	{
		char order=action.charAt(0);
		
		switch(order)
		{
		case 'A':
			Messange="Player:"+player+" - Alin";
			bank.allIn(player);
			break;
		case 'C':
			Messange="Player:"+player+" - Call";
			bank.call(player);
			break;
		case'R':
			Messange="Player:"+player+" - Rise"+Integer.parseInt(action.substring(1));
			bank.rise(player, Integer.parseInt(action.substring(1)));
		}
	}
	
	public String getAccountValue(int player)
	{
		return "B"+Integer.toString(bank.PlayerBilance(player));
	}
	
	public String getMessage()
	{
		return Messange;
	}
///////////////////////////////////////////////////	
	public int[] test()
	{
		return playerHands;
	}
	
	public int[] test2()
	{
		return deck.send();
	}
	
	
	
}
