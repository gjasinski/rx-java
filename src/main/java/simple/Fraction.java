package simple;

public class Fraction {
    private int a;
    private int b;

    public Fraction(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB(){
        return b;
    }

    @Override
    public String toString() {
        return "simple.Fraction{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public static void main(String[] args) {

    }
}
