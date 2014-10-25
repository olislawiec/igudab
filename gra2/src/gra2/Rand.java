package gra2;
import java.util.Random;

public class Rand {
	   Random fate;
	   
	   Rand()
	   {
		   fate=new Random();
	   }
	   
	   int random(int zakres)
	   {
	      return fate.nextInt(zakres);
	   }
}