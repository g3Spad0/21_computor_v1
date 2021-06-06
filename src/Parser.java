import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Term> parse(String equation) {
        char[] arr = equation.toCharArray();
        List<Term> terms = new ArrayList<>();
        int i = 0, j = 0;
        boolean isLeft = true;

        for (; i < arr.length; i++) {
            if (arr[i] == '-' || arr[i] == '+' || arr[i] == '=') {
                if ((arr[i] == '-' || arr[i] == '+') && i == 0) {
                    continue ;
                }
                terms.add(getTerm(arr, j, i - 1, isLeft));
                j = i;
            }
            if (isLeft && arr[i] == '=') {
                isLeft = false;
            }
        }
        terms.add(getTerm(arr, j, i - 1, isLeft));

        if (terms.isEmpty()) {
            throw new RuntimeException();
        }
        return terms;
    }

    private static Term getTerm(char[] arr, int start, int end, boolean isLeft) {
        String curr = getCurrString(arr, start, end);
        char firstChar = '+';
        int degree = 0;
        double coef = 1;

        if (curr.charAt(0) == '+' || curr.charAt(0) == '-' || curr.charAt(0) == '=') {
            firstChar = curr.charAt(0);
            curr = curr.substring(1);
        }

        if (curr.contains("*")) {
            String[] tmp = curr.split("\\*");

            if (tmp[0].contains("\\^")) {
                String[] tmp1 = tmp[0].split("\\^");
                degree = Integer.parseInt(tmp1[1].trim());

                coef = Double.parseDouble(tmp[1].trim());
            }
            else {
                coef = Double.parseDouble(tmp[0].trim());

                String[] tmp1 = tmp[1].split("\\^");
                degree = tmp1.length > 1 ? Integer.parseInt(tmp1[1].trim()) : 1;
            }
        }
        else {
            if (curr.contains("X")) {
                String[] tmp = curr.split("\\^");
                degree = tmp.length > 1 ? Integer.parseInt(tmp[1].trim()) : 1;
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

    private static String getCurrString(char[] arr, int start, int end) {
        char[] curr = new char[end - start + 1];

        for (int i = start, j = 0; i <= end; i++, j++) {
            curr[j] = arr[i];
        }
        return new String(curr);
    }
}
