import java.util.*;

public class Polynomial {
    private List<Term> terms = new ArrayList<>();

    public Polynomial(String equation) {
        this.terms = Parser.parse(equation);
    }

    public void toLeft() {
        for (Term term : terms) {
            if (!term.isLeft()) {
                term.setCoef(term.getCoef() * -1);
                term.setLeft(true);
            }
        }
    }

    public void reduceTerms() {
        Map<Integer, List<Term>> map = new HashMap<>();
        List<Term> currTerms = new ArrayList<>();
        toLeft();

        for (Term term : terms) {
            if (map.containsKey(term.getDegree())) {
                map.get(term.getDegree()).add(term);
            }
            else {
                map.put(term.getDegree(), new ArrayList<>(Collections.singletonList(term)));
            }
        }
        for (Map.Entry<Integer, List<Term>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                currTerms.add(entry.getValue().get(0));
                continue ;
            }
            Term mainTerm = entry.getValue().get(0);

            for (int i = 1; i < entry.getValue().size(); i++) {
                mainTerm.setCoef(entry.getValue().get(i).getCoef() + mainTerm.getCoef());
            }
            if (mainTerm.getCoef() != 0) {
                currTerms.add(mainTerm);
            }
        }
        Collections.sort(currTerms);
        this.terms = currTerms;
    }

    public String getReducedForm() {
        StringBuilder builder = new StringBuilder();

        if (terms.isEmpty()) {
            builder.append("0 ");
        }
        else {
            for (int i = 0; i < terms.size(); i++) {
                if (terms.get(i).getCoef() < 0) {
                    builder.append("-");
                }
                else if (i != 0) {
                    builder.append("+");
                }
                if (i != 0) {
                    builder.append(" ");
                }
                builder.append(terms.get(i)).append(" ");
            }
        }
        return builder.append("= 0").toString();
    }

    public int getMaxDegree() {
        return !terms.isEmpty() ? terms.get(terms.size() - 1).getDegree() : 0;
    }

    public int getMinDegree() {
        return !terms.isEmpty() ? terms.get(0).getDegree() : 0;
    }

    public void solution() {
        double a = terms.stream().filter(T -> T.getDegree() == 2).map(Term::getCoef).findFirst().orElse(0d);
        double b = terms.stream().filter(T -> T.getDegree() == 1).map(Term::getCoef).findFirst().orElse(0d);
        double c = terms.stream().filter(T -> T.getDegree() == 0).map(Term::getCoef).findFirst().orElse(0d);

        if (a != 0) {
            discriminant(a, b, c);
        }
        else if (b != 0) {
            System.out.println("The polynomial degree is equals 1, here is 1 solution:");
            System.out.println(DoubleUtils.doubleForPrint(-c / b));
        }
        else if (c != 0) {
            System.out.println("There is no solution to the equation. Equation is incorrect");
        }
        else {
            System.out.println("Any value is a solution to the equation");
        }
    }

    private static void discriminant(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");

            double sqrt = DoubleUtils.sqrt(discriminant);
            double first = (-b + sqrt) / (2 * a);
            double second= (-b - sqrt) / (2 * a);

            System.out.println(DoubleUtils.doubleForPrint(first));
            System.out.println(DoubleUtils.doubleForPrint(second));
        }
        else if (discriminant == 0) {
            System.out.println("Discriminant is equals 0, the only solution is:");
            System.out.println(DoubleUtils.doubleForPrint(-b / 2 * a));
        }
        else {
            System.out.println("Discriminant is negative, the two imaginary solutions are:");
            double realPart = -b / (2 * a);
            double imagine = DoubleUtils.sqrt(-discriminant) / (2 * a);

            System.out.println(DoubleUtils.doubleForPrint(realPart) + " + " + DoubleUtils.doubleForPrint(imagine) + "i");
            System.out.println(DoubleUtils.doubleForPrint(realPart) + " - " + DoubleUtils.doubleForPrint(imagine) + "i");
        }
    }
}
