package gra2;


public class Bank {
	int[] accountValue;
	int[] playerBet;
	int pool;
	int maxBet;
	
	Bank(int players,int startMoney)
	{
		pool=0;
		maxBet=0;
		playerBet=new int[players+1];
		accountValue=new int[players+1];
		
		for(int i=1;i<players;i++)
		{
			accountValue[i]=startMoney;
		}
		
	}
	
	public void allIn(int player)
	{
		pool+=accountValue[player];
		playerBet[player]+=accountValue[player];
		accountValue[player]=0;
	}
	
	public void call(int player)
	{
		int bet=maxBet-playerBet[player];
		if(bet>accountValue[player])System.out.println("cos");//tu powino wyjatek rzucac 
		else
		{
			accountValue[player]=accountValue[player]-bet;
			pool+=bet;
		}
	}
	
	public void rise(int player,int value)
	{
		if(playerBet[player]!=maxBet)call(player);
		playerBet[player]+=value;
		accountValue[player]=accountValue[player]-value;
		maxBet+=value;
		pool+=value;
		
	}
	public void win(int player)
	{
		accountValue[player]+=pool;
		endRund();
	}
	
	public void draw()
	{
		for(int i=1;i<accountValue.length;i++)
		{
			accountValue[i]+=playerBet[i];
		}
		endRund();
	}
	
	private void endRund()
	{
		maxBet=0;
		pool=0;
		for(int i=1;i>playerBet.length;i++)
		{
			playerBet[i]=0;
		}
		
	}
	
	public int PlayerBilance(int player)
	{
		return accountValue[player];
	}
}