package cz.tul.ppj;

public class MathUtils {

    public static boolean isPrime(int number) {
        int i = (int) Math.sqrt(number);
        while (i > 1) {
            if (number % i == 0) {
                return false;
            }
            i--;
        }
        return true;
    }
}
