package gra2;


public class Deck {

	private int[] deck;
	/**
         * konstruktor klay deck
         */
	Deck() 
	{
		this.deck=new int[52];
		
		
		for(int i=0;i<52;i++) // wypelnia tablice indeksami kart 1-52
		{
			deck[i]=i;
		}
	}
	
    /**
     *klasa tasujaca deck
     */
    public void shuffling()
	{
		Rand rand=new Rand(); 
		int temp , fate;
		int cards=search();// zmienna indekst pierwszej wolnej karty
				
		for(int i=cards;i>1;i--) // petla tasuje karty
		{
			fate=rand.random(i);
			temp=this.deck[i];
			this.deck[i]=this.deck[fate];
			this.deck[fate]=temp;
		}
		
	}
	/**
         * klasa wyszukuje pierwsza dostepna karte
         * @return int-indeks karty
         */
	private int search()// klassa wysukuje pierwsza dostepna karte
	{
		int i=(int)(this.deck.length-1);
		
		while(this.deck[i]==66)
		{
			i--;
		}
		return i;
	}
	/**
         * klasa dobierajaca karty
         * @return ibt-numer karty
         */
	public int drow ()// klasa dobierajaca karty
	{
		int temp;
		int i=search();// zmienna indekst pierwszej wolnej karty
		
		
		temp=this.deck[i]; // zmienna przechowuje wybrana karte
		drowDel(i);
		return temp;
	}
	/**
         * klasa usuwa uzyte karty
         * @param position pozycja karty ktora chcesz usunac
         */
	private void drowDel(int position)//klasa usuwa uzyta karte
	{
		this.deck[position]=66;
	}
	
	/**
         * klasa dodaje karty do decku 
         * @param Cards nr karty ktora chcesz dodac
         */
	public void returnCard(int[] Cards)//klasa dodaje karty do decku 
	{
		int i=0;
		int j=(int)(Cards.length);
		int temp=0;
		
		while(this.deck[i]!=66)//petla wyszukuje pierwsza zużyta karte
		{
			i++;
		}
		
		while(j!=0)//petla dodaje karty do decku
		{
			deck[i]=Cards[temp];
			i++;
			temp++;
			j--;
		}
		shuffling(); //tasowanie kart
		
	}
	/**
         * klasa wysyla tablice kart
         * @return tablica kart
         */
	public int[] send()//klasa testowa
	{
		return this.deck;
	}
}