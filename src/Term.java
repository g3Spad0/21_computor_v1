
public class Term implements Comparable<Term> {
    private int degree;
    private double coef;
    private boolean left;

    public Term() {
    }

    public Term(int degree, double coef, boolean left) {
        this.degree = degree;
        this.coef = coef;
        this.left = left;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    @Override
    public int compareTo(Term term) {
        return Integer.compare(this.getDegree(), term.getDegree());
    }

    @Override
    public String toString() {
        return DoubleUtils.doubleForPrint(coef < 0 ? -coef : coef) + " * " + "X^" + degree;
    }
}
