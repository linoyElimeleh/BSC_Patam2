package tests.test8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Q2good {
    List<Double> X, Y;

    public Q2good(List<Double> X, List<Double> Y) {
        this.X = X;
        this.Y = Y;
    }

    // probability to see yj in Y
    private double Py(double yj) {
        double count = 0;
        for (int i = 0; i < Y.size(); i++)
            if (Y.get(i) == yj)
                count++;

        return count / Y.size();
    }

    HashMap<Double, ArrayList<Integer>> indices = new HashMap<>();

    // probability to see xi and yj in X and Y
    private double Pxy(double xi, double yj) {
        double count = 0;
        ArrayList<Integer> I = indices.get(xi);
        for (int j : I) {
            if (Y.get(j) == yj)
                count++;
        }
        return count / X.size();
    }

    public double conditionalEntropy() {
        double sum = 0;

        // calculate once Py(yi)
        double py[] = new double[Y.size()];
        for (int i = 0; i < py.length; i++)
            py[i] = Py(Y.get(i));

        // all indices xi appears in
        for (int i = 0; i < X.size(); i++) {
            if (!indices.containsKey(X.get(i)))
                indices.put(X.get(i), new ArrayList<>());

            indices.get(X.get(i)).add(i);
        }

        for (int i = 0; i < X.size(); i++) {
            for (int j = 0; j < Y.size(); j++) {
                // calculate once
                double pxy = Pxy(X.get(i), Y.get(j));
                if (pxy > 0)
                    sum += pxy * Math.log10(pxy / py[j]);
            }
        }

        return -sum / Math.log10(2);
    }
}