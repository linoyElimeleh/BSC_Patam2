package tests.test6;

public class BadCode {


    private static double average(int[] array) {

        double sum = 0; // sum the values
        for (int i = 0; i < array.length; i++)
            sum += array[i];

        return sum / (array.length); // average
    }

    // returns the squared distance of each value from the average
    public static double[] dists(int[] array) {
        double[] r = new double[array.length];
        for (int i = 0; i < r.length; i++)
            r[i] = Math.pow(Math.abs(array[i] - average(array)), 2);
        return r;
    }
}
