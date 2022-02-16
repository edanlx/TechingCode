package exam;

public class Code1414 {
    public static void main(String[] args) {
        System.out.println(new Code1414().findMinFibonacciNumbers(5));
    }

    public int findMinFibonacciNumbers(int k) {
        if (k == 0) {
            return 0;
        }
        if (k <= 2) {
            return 1;
        } else {
            int i1 = 1;
            int i2 = 1;
            while (i2 <= k) {
                int temp = i2;
                i2 = i1 + i2;
                i1 = temp;
            }
            return findMinFibonacciNumbers(k - i1) + 1;
        }
    }
}

