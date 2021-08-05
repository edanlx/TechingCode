package exam;

import java.util.Arrays;

class Solution {
    /**
     * 最短距离经典示例
     * @param args
     */
    public static void main(String[] args) {
        new Solution().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        // 填充最大值，方便后续逻辑
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        // 循环数据，将a点到b点的距离填入
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }
        // 创建当前k点距离其它位置的距离(可以视为g[k-1])，同时自己与自己的距离设为0
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int x = -1;
            // 找到当前所有为更新中距离最小的，第一次进来必然找到自己
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            // 循环更新k到其它位置，使用k到x的距离+x到其它位置的距离(即此时更新的是x到其他位置的距离,第一次进来时x即为k,即更新k到它所能到达位置的距离)
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}