package tests.test4;

public class Q2 {

    static double[] averageCols(double[][] data) {
        int colLen = data[0].length;
        int rowLen = data.length;
        double[] avg = new double[colLen];

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                avg[col] += data[row][col];
            }
        }

        for (int i = 0; i < avg.length; i++) {
            avg[i] /= rowLen;
        }
        return avg;
    }


}
