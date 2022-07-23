package tests.test2;

public class Q2 {
    static int xLen, yLen;
    static double avgX, avgY;
    static double sumX , sumY ;

    // the original code:
    public static void avg(double[] x, double[] y) {
        sumX = 0;
        sumY = 0;
        for (int i = 0; i < xLen; i++) {
            sumY += y[i];
            sumX += x[i];
        }
        avgX = sumX / xLen;
        avgY = sumY / xLen;
    }

    public static double pearson(double[] x, double[] y) {
        if(x==y)
            return 1;

        xLen = x.length;
        yLen = y.length;

        if (xLen == yLen) {
            double sumXY = 0, sumX = 0, sumY = 0;
            avg(x, y);


            for (int i = 0; i < xLen; i++) {

                double xMath = x[i] - avgX;
                double yMath = y[i] - avgY;

                sumXY += xMath * yMath;

                sumX += xMath * xMath;

                sumY += yMath * yMath;
            }


            return sumXY / Math.sqrt(sumX * sumY);
        }
        return 0;
    }


    public static void warmup() {
    }

}
