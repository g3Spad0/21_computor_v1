import java.text.DecimalFormat;

public class DoubleUtils {

    public static String doubleForPrint(double d) {
        DecimalFormat df = new DecimalFormat("0");

        df.setMaximumFractionDigits(6);
        return df.format(d);
    }

    public static double sqrt(double x) {
        double epsilon;
        double guess;

        guess = x;
        epsilon = 1e-15;
        while (abs(guess - x / guess) > epsilon * guess) {
            guess = (x / guess + guess) / 2.0;
        }
        return guess;
    }

    public static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }
}
