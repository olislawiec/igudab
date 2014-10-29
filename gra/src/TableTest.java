
public class TableTest {
	public static void main(String[] args)
	{
		Table t=new Table((byte)2,100);
		
		byte[] l=t.test2();
		
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
		
		t.changeCard((byte)1,(byte)2);
		l=t.test();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");
		
		t.changeCard((byte)2,(byte)1);
		t.changeCard((byte)2,(byte)2);
		t.changeCard((byte)1,(byte)2);
		t.changeCard((byte)1,(byte)3);
		t.changeCard((byte)1,(byte)4);
		l=t.test();
		
		for(int i=1;i<l.length;i++)
		{
			System.out.print(l[i]+";");
			if (i%4==0)System.out.println("");
		}
		System.out.println("");
		System.out.println("-----------------------------------------------");

		
	}
}
