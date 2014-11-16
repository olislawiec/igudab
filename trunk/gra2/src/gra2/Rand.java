package gra2;
import java.util.Random;
/**
 * Klasa Randomizujaca
 * @author Andrzej
 */
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