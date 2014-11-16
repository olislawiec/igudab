package gra2;

public class Deck {

	private int[] deck;

	Deck() {
		this.deck = new int[52];

		for (int i = 0; i < 52; i++) // wypelnia tablice indeksami kart 1-52
		{
			deck[i] = i;
		}
	}

	public void shuffling() {
		Rand rand = new Rand();
		int temp, fate;
		int cards = search();// zmienna indekst pierwszej wolnej karty

		for (int i = cards; i > 1; i--) // petla tasuje karty
		{
			fate = rand.random(i);
			temp = this.deck[i];
			this.deck[i] = this.deck[fate];
			this.deck[fate] = temp;
		}

	}

	private int search()// klassa wysukuje pierwsza dostepna karte
	{
		int i = (int) (this.deck.length - 1);

		while (this.deck[i] == 66) {
			i--;
		}
		return i;
	}

	public int drow()// klasa dobierajaca karty
	{
		int temp;
		int i = search();// zmienna indekst pierwszej wolnej karty

		temp = this.deck[i]; // zmienna przechowuje wybrana karte
		drowDel(i);
		return temp;
	}

	private void drowDel(int position)// klasa usuwa uzyta karte
	{
		this.deck[position] = 66;
	}

	public void returnCard(int[] Cards)// klasa dodaje karty do decku
	{
		int i = 0;
		int j = (int) (Cards.length);
		int temp = 0;

		while (this.deck[i] != 66)// petla wyszukuje pierwsza zużyta karte
		{
			i++;
		}

		while (j != 0)// petla dodaje karty do decku
		{
			deck[i] = Cards[temp];
			i++;
			temp++;
			j--;
		}
		shuffling(); // tasowanie kart

	}

	public int[] send()// klasa testowa
	{
		return this.deck;
	}
}