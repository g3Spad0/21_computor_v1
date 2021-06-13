import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Term> parse(String equation) {
        if (equation == null || !equation.contains("=")) {
            throw new RuntimeException();
        }
        List<Term> terms = new ArrayList<>();
        String[] tmp = equation.split("=");

        if (tmp.length != 2) {
            throw new RuntimeException();
        }

        parse(tmp[0], terms, true);
        parse(tmp[1], terms, false);

        if (terms.isEmpty()) {
            throw new RuntimeException();
        }
        return terms;
    }

    private static void parse(String equation, List<Term> terms, boolean isLeft) {
        char[] arr = equation.toUpperCase().trim().toCharArray();
        int i = 0, j = 0;

        for (; i < arr.length; i++) {
            if (arr[i] == '-' || arr[i] == '+') {
                if (arr[i] == '-') {
                    if (i == 0 && nextIsNumber(arr, i)) {
                        continue ;
                    }
                }
                terms.add(getTerm(arr, j, i - 1, isLeft));
                j = i;
            }
        }
        terms.add(getTerm(arr, j, i - 1, isLeft));
    }

    private static Term getTerm(char[] arr, int start, int end, boolean isLeft) {
        String curr = getCurrString(arr, start, end).trim();
        char firstChar = '+';
        int degree = 0;
        double coef = 1;

        if (curr.charAt(0) == '+' || curr.charAt(0) == '-' || curr.charAt(0) == '=') {
            firstChar = curr.charAt(0);
            curr = curr.substring(1).trim();
        }

        if (curr.contains("*")) {
            String[] tmp = curr.split("\\*");

            if (tmp.length != 2) {
                throw new RuntimeException();
            }
            if (tmp[0].contains("X")) {
                degree = getDegree(tmp[0]);
                coef = Double.parseDouble(tmp[1].trim());
            }
            else {
                coef = Double.parseDouble(tmp[0].trim());

                degree = getDegree(tmp[1]);
            }
        }
        else {
            if (curr.contains("X")) {
                degree = getDegree(curr);
            }
            else {
                coef = Double.parseDouble(curr.trim());
            }
        }
        if (firstChar == '-') {
            coef *= -1;
        }

        return new Term(degree, coef, isLeft);
    }

    private static int getDegree(String from) {
        String[] tmp = from.split("\\^");

        if (tmp.length > 1) {
            return Integer.parseInt(tmp[1].trim());
        }
        if (tmp[0].trim().equals("X")) {
            return 1;
        }
        throw new RuntimeException();
    }

    private static String getCurrString(char[] arr, int start, int end) {
        char[] curr = new char[end - start + 1];

        for (int i = start, j = 0; i <= end; i++, j++) {
            curr[j] = arr[i];
        }
        return new String(curr);
    }

    private static boolean nextIsNumber(char[] arr, int i) {
        try {
            return arr.length > i + 1 && Character.isDigit(arr[i + 1]);
        }
        catch (Exception e) {
            return false;
        }
    }
}
