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
		
		for(int i=1;i<=players;i++)
		{
			accountValue[i]=startMoney;
		}
		
	}
	
	public int getMaxBet() {
		return maxBet;
	}
	public int getPool() {
		return pool;
	}
	public int getPlayerBet(int player) {
		return playerBet[player];
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
			playerBet[player]+=bet;
			pool+=bet;
		}
	}
	
	public void rise(int player,int value)
	{
		if(playerBet[player]<maxBet)call(player);
		if((playerBet[player]+value)>accountValue[player])System.out.print("wyjatek");
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
	public void bet(int player,int bet)
	{
		accountValue[player]=accountValue[player]-bet;
		playerBet[player]+=bet;
		pool+=bet;
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