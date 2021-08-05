package exam;

import java.util.ArrayList;
import java.util.List;

public class Code802 {
    /**
     * 三色算法经典题
     * 从初始点开始，即0点，找到该点的指向并进行递归，如果有指向染色的则停止，如果未染色则将其染色，如果该点没有下一个指向则染为终色并返回递归，最终会得到染为双色的列表
     *
     * @param args
     */
    public static void main(String[] args) {
        new Code802().eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}});
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }
}
