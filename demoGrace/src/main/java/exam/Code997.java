package exam;

public class Code997 {
    public static void main(String[] args) {
        System.out.println(new Code997().findJudge(3, new int[][]{{1, 3}, {2, 3}}));
    }

    public int findJudge(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }
        int[] state = new int[n];
        boolean[] state2 = new boolean[n];
        for (int i = 0; i < trust.length; i++) {
            int temp = trust[i][1] - 1;
            state2[trust[i][0] - 1] = true;
            state[temp] = state[temp] + 1;
        }
        for (int i = 0; i < trust.length; i++) {
            int temp = trust[i][1] - 1;
            if (state[temp] == n - 1 && !state2[temp]) {
                return trust[i][1];
            }
        }
        return -1;
    }
}
