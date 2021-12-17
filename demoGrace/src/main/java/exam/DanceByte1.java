package exam;

import java.util.Arrays;

public class DanceByte1 {
    public static void main(String[] args) {
        System.out.println(new DanceByte1().execute(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    int[] dp = null;
    int[] m1 = null;

    public int execute(int[] m) {
        dp = new int[m.length];
        m1 = m;
        Arrays.fill(dp, -1);
        dp[2] = m[2];
        dp[3] = m[3];
        dp[4] = m[4];
        getValue(m.length - 1);
        getValue(m.length - 2);
        getValue(m.length - 3);
        getValue(m.length - 4);
        getValue(m.length - 5);

        return 0;
    }

    public int getValue(int i) {
        if (dp[i] != -1) {
            return dp[i];
        }
        int min = Integer.MAX_VALUE;
        if (i - 5 >= 0) {
            min = Math.min(min, getValue(i - 5));
        }
        if (i - 4 >= 0) {
            min = Math.min(min, getValue(i - 4));
        }
        if (i - 3 >= 0) {
            min = Math.min(min, getValue(i - 3));
        }
        if (min != Integer.MAX_VALUE) {
            dp[i] = m1[i] + min;
        }
        return min;
    }
}
