package tests.test13;

public class GoodCode {


    public static double expSum(double[] vec) {
        double sum = 0;
        for (int i = 0; i < vec.length; i++)
            sum += exp(vec[i]);
        return sum;
    }

    public static double exp(double z) {
        return Math.exp(z);
    }

    public static double[] softmaxOpt(double[] vec) {
        double[] prop = new double[vec.length];
        double ex = expSum(vec);
        for (int i = 0; i < vec.length; i++) {
            prop[i] = exp(vec[i]) / ex;
        }
        return prop;
    }

}
