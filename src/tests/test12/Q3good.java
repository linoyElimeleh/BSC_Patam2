package tests.test12;

public class Q3good {

    public static boolean vote(boolean[][] votes) {
        int countFor = 0;
        for (int i = 0; i < votes.length; i++)
            for (int j = 0; j < votes[i].length; j++)
                countFor += (votes[i][j] ? 1 : 0);

        return countFor > votes.length * votes[0].length / 2;
    }
}
