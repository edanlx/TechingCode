package exam;

import java.util.LinkedList;
import java.util.Queue;

public class Code847 {
    /**
     * 状态压缩经典题
     * 状态压缩也即用一个变量来表示当前状态，比较常用的方式是利用一个 n 位 k 进制数 mask 表示当前 n 个节点的所处的 k 个不同状态
     * 一些状态压缩的基本操作如下：
     * <p>
     * （1）访问第 i 个点的状态：state=(1 << i) & mask
     * （2）更改第 i 个点状态为 1：mask = mask | (1 << i)
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(new Code847().shortestPathLength(new int[][]{{1, 2, 3}, {0}, {0}, {0}}));
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        // 1.初始化队列及标记数组，存入起点
        Queue<int[]> queue = new LinkedList<int[]>();
        // 节点编号及当前状态
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            // 三个属性分别为 idx, mask, dist(其实距离为0)
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }
}
