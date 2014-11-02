
public class Rules {
	
	int[] hands;
	int[] playerRate;
	int players;
	int cards=0;
	int winner;
	
	Rules(int players,int[] hands)
	{
		playerRate=new int[players+1];
		
		for(int i=0;i<playerRate.length;i++)
		{
			playerRate[i]=4;
		}
		
		this.players=players;
		this.hands =hands;
	}

	private void sort()
	{

		int tempSort=0;
		int temp=0;
		for(int m=1;m<=this.players;m++)
		{
			if(m!=1)
			{
				temp+=4;
			}

			for (int i = 1+temp; i <= 4*m; i++) 
			{
	            for (int j = 1+temp; j < (4*m); j++) 
	            {
	                if (hands[j]%13 > hands[j + 1]%13) {
	                    tempSort = hands[j];
	                    hands[j] = hands[j + 1];
	                    hands[j + 1] = tempSort;
	                }
	            }
	            
	        }
		}
	}
	
	private void handsRate()
	{
		int temp=0;
		int card;

		
		for(int i=1;i<=players;i++)
		{
		//	card=hands[1+temp];
			if(i!=1)temp+=4;
			for(int j=1+temp;j<=(4*i);j++)
			{
				for(int m=j+1;m<=(4*i);m++)
				{
					if((hands[j]%13)==(hands[m]%13))
					{
						playerRate[i]-=1;
						//System.out.println("powtorka-"+hands[j]+"-"+hands[m]);
					}
				}
			}
			if(this.cards<playerRate[i])
			{
				this.cards=playerRate[i];
			}
		}
	}
	
	private void comp()
	{
		sort();
		handsRate();
		
		
		int[] player;
		int temp=0;
		//int j=1;
		int v=4;
		
		for(int i=1;i<playerRate.length;i++)
		{
			if(playerRate[i]==this.cards)
			{
				temp++;
				//System.out.println("dodaje do ratingu"+temp);
			}
		}
		
		player=new int[temp];
		temp=0;
		for(int i=1;i<=this.players;i++)
		{
			if(playerRate[i]==this.cards)
			{
				//System.out.println("dodggg"+i+"-"+temp);
				player[temp]=i;
				//System.out.println("dodggffg"+i+"-"+player[temp]);
				temp++;
			}
		}
		//System.out.println("lol1");
		
		int m;
		temp=player.length;
		while(v!=0 && temp!=0   )
		{
			
			System.out.println("lol2");
			for(int j=1;j<temp;j++)
			{
				//System.out.println("ffs"+(hands[((4*player[0])+(v-4))]%13)+"-"+hands[((4*player[j])+(v-4))]%13);
				if((hands[((4*player[0])+(v-4))]%13)>(hands[((4*player[j])+(v-4))]%13))
				{
					//System.out.println("");
					player[0]=player[j];
					player[j]=player[temp-1];
					player[temp-1]=66;	
					temp--;
					//System.out.println(player[j]);
				}
				else
				{
					if((hands[((4*player[0])+(v-4))]%13)<(hands[((4*player[j])+(v-4))]%13))
					{
						m=j;
						while(m<player.length)
						{
						//	System.out.println("mniejsze"+m+"--"+player.length);
							if((m+1)==temp)player[temp-1]=66;
							else player[m]=player[m+1];
							m++;
						}
						temp--;
					}
				}


				//System.out.println(j+"-j");
			}
			v--;

		}
		for(int i=0;i<player.length;i++)System.out.println(player[i]);
		winner=player[0];
	}
	
	public int win()
	{
		comp();
		return winner;
	}
	
	public int[] lol()
	{
		sort();
		return hands;
	}
}
