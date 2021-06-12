public class DoubleUtils {

    public static String doubleForPrint(double d) {
        return d % 1 == 0 ? String.valueOf((long) d) : String.format("%.3f", d);
    }

    public static double sqrt(double x) {
        double epsilon;
        double guess;

        guess = x;
        epsilon = 1e-15;
        while (abs(guess - x / guess) > epsilon * guess) {
            guess = (x / guess + guess) / 2.0;
        }
        if (guess != Math.sqrt(x)) {
            System.out.println("fddffddf");
            throw new RuntimeException("fddffddf");
        }
        return guess;
    }

    public static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }//FFFF=1
}

//5*X^0 + 4*X + -9.3 * X^2=1*X^0