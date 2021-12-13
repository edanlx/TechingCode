package exam;

public class Code807 {

    public static void main(String[] args) {
        System.out.println(new Code807().maxIncreaseKeepingSkyline(new int[][]{{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}}));
    }

    /**
     * 求出顶部，左侧分别的最大值，遍历增的同时等于行列min-1
     *
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int result = 0;
        int[] col = new int[grid.length];
        int[] row = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                row[i] = Math.max(grid[i][j], row[i]);
                col[j] = Math.max(grid[i][j], col[j]);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int cur = grid[i][j];
                int min = Math.min(row[i], col[j]);
                if (cur >= min) {
                    continue;
                }
                result += min - cur;
                grid[i][j] = min;
            }
        }
        return result;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
