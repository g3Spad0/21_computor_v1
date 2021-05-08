import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //run("5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0");

        if (args.length == 2 && args[1].equals("fromBash")) {
            run(args[0]);
            return ;
        }
        else {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter equation: ");

                String s = scanner.nextLine();

                if (s.equalsIgnoreCase("exit")) {
                    break ;
                }
                run(s);
                System.out.println();
            }
        }
    }

    private static void run(String equation) {
        Polynomial polynomial;

        try {
            polynomial = new Polynomial(equation);
        }
        catch (Exception e) {
            System.out.println("Validation failed");
            return ;
        }
        polynomial.reduceTerms();
        System.out.println("Reduced form: " + polynomial.getReducedForm());

        if (polynomial.getMaxDegree() > 2) {
            System.out.println("Polynomial degree: " + polynomial.getMaxDegree() + "\nThe polynomial degree is strictly greater than 2, I can't solve.");
        }
        else if (polynomial.getMinDegree() < 0) {
            System.out.println("Incorrect min degree: " + polynomial.getMinDegree() + "\nThe polynomial degree is strictly less or equals than 0, I can't solve.");
        }
        else {
            System.out.println("Polynomial degree: " + polynomial.getMaxDegree());
            polynomial.solution();
        }
    }
}
