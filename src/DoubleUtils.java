public class DoubleUtils {

    public static String doubleForPrint(double d) {
        return d % 1 == 0 ? String.valueOf((long) d) : String.format("%.3f", d);
    }

    public static double sqrt(double d) {
        return Math.sqrt(d);
    }
}