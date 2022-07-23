package tests.test8;

import java.util.List;

public class Q2bad {
    List<Double> X, Y;

    public Q2bad(List<Double> X, List<Double> Y) {
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

    // probability to see xi and yj in X and Y
    private double Pxy(double xi, double yj) {
        double count = 0;
        for (int i = 0; i < X.size(); i++)
            if (X.get(i) == xi && Y.get(i) == yj)
                count++;
        return count / X.size();
    }

    public double conditionalEntropy() {
        double sum = 0;
        for (int i = 0; i < X.size(); i++) {
            for (int j = 0; j < Y.size(); j++) {
                if (Pxy(X.get(i), Y.get(j)) > 0)
                    sum += Pxy(X.get(i), Y.get(j)) * Math.log10(Pxy(X.get(i), Y.get(j)) / Py(Y.get(j))) / Math.log10(2);
            }
        }

        return -sum;
    }
}
