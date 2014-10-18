
public class Deck {

	private byte[] deck;
	
	Deck()
	{
		this.deck=new byte[53];
		
		
		for(byte i=0;i<53;i++)
		{
			deck[i]=i;
		}
	}
	
	public void shuffling()
	{
		Rand rand=new Rand();
		byte temp , fate;
		byte cards=(byte)(deck.length-1);
		
		while(this.deck[cards]==0)
		{
			cards--;
		}
		
		for(int i=cards;i>1;i--)
		{
			fate=(byte)(rand.random(i) + 1);
			temp=this.deck[i];
			this.deck[i]=this.deck[fate];
			this.deck[fate]=temp;
		}
		
	}
	
	public byte drow ()
	{
		byte temp;
		byte i=(byte)(this.deck.length-1);
		while(this.deck[i]==0)
		{
			i--;
		}
		temp=this.deck[i];
		drowDel(i);
		return temp;
	}
	
	private void drowDel(byte position)
	{
		this.deck[position]=0;
	}
	
	
	public void returnCard(byte[] Cards)
	{
		byte i=1;
		byte j=(byte)(Cards.length);
		byte temp=0;
		
		while(this.deck[i]!=0)
		{
			i++;
		}
		
		while(j!=0)
		{
			deck[i]=Cards[temp];
			i++;
			temp++;
			j--;
		}
		shuffling();
		
	}
	
	public byte[] send()
	{
		return this.deck;
	}
}
