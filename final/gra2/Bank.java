package gra2;


public class Bank {
	int[] accountValue;
	int[] playerBet;
	int pool;
	int maxBet;
	/**
         * Konstruktor klasy deck
         * @param players-maksymalna liczba graczy
         * @param startMoney startowa ilosc zetonow
         */
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
	/**
         * 
         * @return zwaraca najwyzszy zaklad
         */
	public int getMaxBet() {
		return maxBet;
	}
        /**
         * 
         * @return zwraca pule 
         */
	public int getPool() {
		return pool;
	}
        /**
         * 
         * @param player numer gracza
         * @return zaklad wybranego gracza
         */
	public int getPlayerBet(int player) {
		return playerBet[player];
	}
	/**
         * metoda stawia wszystkie pieni¹dze danego gracza
         * @param player numer gracza
         */
	public void allIn(int player)
	{
		pool+=accountValue[player];
		playerBet[player]+=accountValue[player];
		accountValue[player]=0;
	}
	/**
         * metoda wyrownuje do najwyzszego zakladu
         * @param player numer gracza
         */
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
	/**
         * metoda podnosi beta do okreslonej wartosci 
         * @param player numer gracza
         * @param value  wartosc o ktora chcesz podniesc zaklad
         */
	public void rise(int player,int value)
	{
		if(playerBet[player]<maxBet)call(player);
		if((playerBet[player]+value)>accountValue[player])System.out.print("wyjatek");
		playerBet[player]+=value;
		accountValue[player]=accountValue[player]-value;
		maxBet+=value;
		pool+=value;
		
	}
        /**
         * metoda oddaje pulle zwyciescy
         * @param player numer gracza
         */
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
        /**
         * metoda oddaje zaklady graczom w przypadku remisu 
         */
	public void draw()
	{
		for(int i=1;i<accountValue.length;i++)
		{
			accountValue[i]+=playerBet[i];
		}
		endRund();
	}
	/**
         * Konczy runde licytacji 
         */
	private void endRund()
	{
		maxBet=0;
		pool=0;
		for(int i=1;i>playerBet.length;i++)
		{
			playerBet[i]=0;
		}
		
	}
	/**
         * 
         * @param player numer gracza
         * @return zwaracawartosc konta gracza
         */
	public int PlayerBilance(int player)
	{
		return accountValue[player];
	}
}