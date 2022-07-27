package tests.test12;
public class Q3bad {

	public static boolean vote(boolean[][] votes) {
		int countFor=0,countAgainst=0; // for and against counters
		for(int i=0;i<votes.length;i++)
			for(int j=0;j<votes[i].length;j++)
				if(votes[i][j])
					countFor++;
				else
					countAgainst++;
		
		return countFor>countAgainst; 
	}
}
