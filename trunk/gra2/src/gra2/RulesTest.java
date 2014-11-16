package gra2;

import static org.junit.Assert.*;

import org.junit.Test;

public class RulesTest {

	@Test
	public void testRules() {
		
		Rules r=new Rules(2);
		int[] hands=new int[9];
		hands[1]=13;
		hands[2]=1;
		hands[3]=2;
		hands[4]=3;
		hands[5]=0;
		hands[6]=14;
		hands[7]=(2*13)+2;
		hands[8]=(3*13)+3;
		 System.out.println("ll"+r.win(hands));
		assertEquals(2, r.win(hands));
		
		hands[1]=13;
		hands[2]=1;
		hands[3]=2;
		hands[4]=3;
		hands[5]=0;
		hands[6]=14;
		hands[7]=(2*13)+1;
		hands[8]=(3*13)+3;
		assertEquals(1, r.win(hands));
	}

}
