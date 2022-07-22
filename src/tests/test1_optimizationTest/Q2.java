package tests.test1_optimizationTest;

public class Q2 {

    public static double px(double x, double[] vec) {
        int count = 0;
        for (int i = 0; i < vec.length; i++)
            if (x == vec[i])
                count++;
        return (double) count / vec.length;
    }

    public static double log2(double x) {
        return Math.log10(x) / Math.log10(2);
    }


    public static double OPT_Hx(double[] vec) {
        double sum = 0;
        for (int i = 0; i < vec.length; i++) {
            double pxResult = px(vec[i], vec);
            double log2 = log2(pxResult);
            sum += pxResult * log2;
        }
        return -sum;
    }
}
