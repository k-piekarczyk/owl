package owl.utils;

public class General {
    public static boolean compareDoublesWithPrecision(double d1, double d2, double prec) {
        return Math.abs(d1 - d2) < prec;
    }
}
