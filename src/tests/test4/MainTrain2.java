package tests.test4;

public class MainTrain2 {

    static double avg(double[] x) {
        double sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += x[i];
        return sum / x.length;
    }

    static double[] averageCols(double[][] data) {
        double avg[] = new double[data[0].length];
        for (int col = 0; col < data[0].length; col++) {
            double column[] = new double[data.length];
            for (int row = 0; row < data.length; row++) {
                column[row] = data[row][col];
            }
            avg[col] = avg(column);
        }
        return avg;
    }

    public static void main(String[] args) {
        int n = 10000;
        double data[][] = new double[n][n / 2];

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = Math.random() * 10;
            }

        long t0 = System.nanoTime();
        double[] avg = averageCols(data);
        long badTime = System.nanoTime() - t0;
        System.out.println("inefficient time: " + badTime);


        t0 = System.nanoTime();
        double[] avgStudent = Q2.averageCols(data);
        long goodTime = System.nanoTime() - t0;
        System.out.println("your time: " + goodTime);

        boolean resultsEqual = true;
        for (int i = 0; i < avg.length && resultsEqual; i++)
            if (Math.abs(Math.round(1000 * avg[i]) - Math.round(1000 * avgStudent[i])) > 0.01)
                resultsEqual = false;

        try {
            if (!resultsEqual) {
                System.out.println("your avgCols did not get the same result (-50)");
                return;
            }
            if (goodTime >= badTime) {
                System.out.println("your avgCols is not efficient (-50)");
                return;
            }
            if (goodTime >= 0.5 * badTime) {
                System.out.println("your avgCols only has a lower bound of 0.5 of the inefficient time (-25)");
                return;
            }
            if (goodTime >= 0.25 * badTime) {
                System.out.println("your avgCols only has a lower bound of 0.25 of the inefficient time (-13)");
                return;
            }
            if (goodTime >= 0.1 * badTime) {
                System.out.println("your avgCols only has a lower bound of 0.1 of the inefficient time (-5)");
                return;
            }
            if (goodTime >= 0.08 * badTime) {
                System.out.println("your avgCols only has a lower bound of 0.08 of the inefficient time (-2)");
                return;
            }
            if (goodTime >= 0.04 * badTime) {
                System.out.println("your avgCols only has a lower bound of 0.04 of the inefficient time (-1)");
                return;
            }

        } finally {
            System.out.println("done");
        }
    }

}
