package gra2;

public class TableTest {
	public static void main(String[] args)
	{
		int play=6;// ilosc graczy
		Table t=new Table(play,100);
		
		int[] l=t.test2();

		for(int i=0;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		
		t.deal();
		l=t.test2();
		
		for(int i=0;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		
		l=t.test();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		
		//t.changeCard((int)1,(int)2);
		l=t.test();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		
	//	t.changeCard((int)2,(int)1);
	//	t.changeCard((int)2,(int)2);
	//	t.changeCard((int)1,(int)2);
	//	t.changeCard((int)1,(int)3);
	//	t.changeCard((int)1,(int)4);
	l=t.test();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		/*l[1]=18;
		l[2]=46;l[3]=8;l[4]=24;
		l[5]=15;l[6]=29;l[7]=45;l[8]=37;
		l[9]=5;l[10]=6;l[11]=7;l[12]=34;
		l[13]=15;l[14]=31;l[15]=6;l[16]=9;*/
	//	Rules r=new Rules(play,l);
		//l=r.lol();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print((l[i]%13)+"-"+(l[i])+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		//System.out.println("winner:"+r.win()+"-"+r.cards);
		System.out.println("-----------------------------------------------");
		System.out.print(t.getHand(1));
		System.out.print(t.Winner());
		System.out.println(t.drow(1, "D1,2"));
	}
}