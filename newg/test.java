
public class test {
	public static void main(String[] args)
	{
		int[] deck;
		int[] ret=new int[2];
		
		Deck d = new Deck();
		
		deck=d.send();
		
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i]+";");
			if (i%4==0)System.out.println("");
		}
		
		d.shuffling();
		
		System.out.println("-----------------------------------------------");
		
		deck=d.send();
		
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i]+";");
			if (i%4==0)System.out.println("");
		}
		
		System.out.println("-----------------------------------------------");
		
		int dr=d.drow();
		ret[0]=dr;
		System.out.println("drow:"+dr);
		
		dr=d.drow();
		ret[1]=dr;
		System.out.println("drow:"+dr);
			
		deck=d.send();
		
		
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i]+";");
			if (i%4==0)System.out.println("");
		}
		
		System.out.println("-----------------------------------------------");
		d.shuffling();
		deck=d.send();
		
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i]+";");
			if (i%4==0)System.out.println("");
		}
		
		System.out.println("-----------------------------------------------");
		d.returnCard(ret);
		d.shuffling();
		
		deck=d.send();
		
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i]+";");
			if (i%4==0)System.out.println("");
		}
		
	}
	
	
	
}
