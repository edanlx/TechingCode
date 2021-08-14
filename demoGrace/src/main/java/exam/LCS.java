package exam;

public class LCS {
    public static void main(String[] args) {

    }

    /**
     * 最长子序列经典题，利用双循环加dp，即固定某一段的情况下匹配
     * @param A
     * @param B
     * @return
     */
    public static int findLCS(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[A.length()][B.length()];
    }
}
