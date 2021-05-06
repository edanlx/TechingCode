package exam;

public class Code633 {
    public boolean judgeSquareSum(int c) {
        int right = c / 2 + 1;
        for (int left = 0; left < right; left++) {
            int temp = c - left * left;
            double sqrt = Math.sqrt(temp);
            if (sqrt - Math.floor(sqrt) < 1e-10) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Code633().judgeSquareSum(11));
    }
}
