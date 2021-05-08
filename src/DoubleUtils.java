
public class DoubleUtils {

    public static String doubleForPrint(double d) {
        return d % 1 == 0 ? String.valueOf((long) d) : String.format("%.3f", d);
    }

    public static double sqrt(double d) {
        return Math.sqrt(d);
    }
}
//25 * X^2 - 10 * X^1 - 5 = 0