package gra2;

public class Deck {

	private byte[] deck;
	
	Deck() 
	{
		this.deck=new byte[53];
		
		
		for(byte i=0;i<53;i++) // wypelnia tablice indeksami kart 1-52
		{
			deck[i]=i;
		}
	}
	
	public void shuffling()
	{
		Rand rand=new Rand(); 
		byte temp , fate;
		byte cards=search();// zmienna indekst pierwszej wolnej karty
				
		for(int i=cards;i>1;i--) // petla tasuje karty
		{
			fate=(byte)(rand.random(i) + 1);
			temp=this.deck[i];
			this.deck[i]=this.deck[fate];
			this.deck[fate]=temp;
		}
		
	}
	
	private byte search()// klassa wysukuje pierwsza dostepna karte
	{
		byte i=(byte)(this.deck.length-1);
		
		while(this.deck[i]==0)
		{
			i--;
		}
		return i;
	}
	
	public byte drow ()// klasa dobierajaca karty
	{
		byte temp;
		byte i=search();// zmienna indekst pierwszej wolnej karty
		
		
		temp=this.deck[i]; // zmienna przechowuje wybrana karte
		drowDel(i);
		return temp;
	}
	
	private void drowDel(byte position)//klasa usuwa uzyta karte
	{
		this.deck[position]=0;
	}
	
	
	public void returnCard(byte[] Cards)//klasa dodaje karty do decku 
	{
		byte i=1;
		byte j=(byte)(Cards.length);
		byte temp=0;
		
		while(this.deck[i]!=0)//petla wyszukuje pierwsza zuĹyta karte
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
	
	public byte[] send()//klasa testowa
	{
		return this.deck;
	}
}