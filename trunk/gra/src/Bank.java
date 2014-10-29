
public class Bank {
	int[] accountValue;
	int[] playerBet;
	int pool;
	int maxBet;
	
	Bank(byte players,int startMoney)
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
	
	public void allIn(byte player)
	{
		pool+=accountValue[player];
		playerBet[player]+=accountValue[player];
		accountValue[player]=0;
	}
	
	public void call(byte player)
	{
		int bet=maxBet-playerBet[player];
		if(bet>accountValue[player])System.out.println("cos");//tu powino wyjatek rzucac 
		else
		{
			accountValue[player]=accountValue[player]-bet;
			pool+=bet;
		}
	}
	
	public void rise(byte player,int value)
	{
		if(playerBet[player]!=maxBet)call(player);
		playerBet[player]+=value;
		accountValue[player]=accountValue[player]-value;
		maxBet+=value;
		pool+=value;
		
	}
	
	public void endRund()
	{
		maxBet=0;
		pool=0;
		for(int i=1;i>playerBet.length;i++)
		{
			playerBet[i]=0;
		}
		
	}
	
	public int PlayerBilance(byte player)
	{
		return accountValue[player];
	}
}
