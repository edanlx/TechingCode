package exam;

import java.util.LinkedList;
import java.util.Queue;

public class Code847 {
    /**
     * 状态压缩经典题
     * 状态压缩也即用一个变量来表示当前状态，比较常用的方式是利用一个 n 位 k 进制数 mask 表示当前 n 个节点的所处的 k 个不同状态
     * 一些状态压缩的基本操作如下：
     * u 表示当前位于的节点编号
     * mask 是一个长度为 n 的二进制数，表示每一个节点是否经过。如果 mask 的第 i 位是 1，则表示节点 i 已经过，否则表示节点 i 未经过
     * 说明:以题目中的4个节点为例，分别初始化1，2，4，8分别为，1，10，100，1000，分别代表第一位，第二位，第三位，第四位被访问过即自己
     * dist 表示到当前节点为止经过的路径长度。
     *
     * <p>
     * （1）访问第 i 个点的状态(0表示未访问过,1表示访问过)：state=(1 << i) & mask
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
        // 节点编号及mask被访问情况，存储是否被访问过避免重复改mask状态
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            // 三个属性分别为 idx, mask, dist(起始距离为0)，注:因为是双向的所以必然会访问，不用关心先后顺序
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            // (1 << n) - 1即为15，也即1111，代表全部访问完毕
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                // 返回false表示有将要访问的目标加上本体路径已经有访问过的了不用再访问，通常为0->1->0这种被1->0直接覆盖
                if (!seen[v][maskV]) {
                    // 旅行者问题经典思路，到达某点后从某点可到达其它点继续延伸
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }
}
