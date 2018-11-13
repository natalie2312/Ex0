package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {


	@Override
	public int compare(Monom s1, Monom s2)
	{
		if(s1.get_power() > s2.get_power())
			return 1;
		else if(s1.get_power() == s2.get_power())
			return 0;
		else
			return -1;
	}
	
}
