package exam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Code851 {
    public static void main(String[] args) {
        new Code851().loudAndRich3(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}}, new int[]{3, 2, 5, 4, 6, 1, 7, 0});
//        new Code851().loudAndRich(new int[][]{}, new int[]{1, 0});
    }

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<Integer>[] draw = new ArrayList[quiet.length];
        TreeMap<Integer, Integer> quietTree = new TreeMap<>();
        for (int i = 0; i < quiet.length; i++) {
            quietTree.put(quiet[i], i);
        }
        int[] result = new int[quiet.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < richer.length; i++) {
            if (draw[richer[i][0]] == null) {
                draw[richer[i][0]] = new ArrayList<>();
            }
            if (draw[richer[i][1]] == null) {
                draw[richer[i][1]] = new ArrayList<>();
            }
            draw[richer[i][0]].add(richer[i][1]);
        }
        for (Map.Entry<Integer, Integer> entry : quietTree.entrySet()) {
            List<Integer> node = draw[entry.getValue()];
            if (node != null) {
                fillResult(node, entry.getValue(), result, draw, entry.getValue());
            } else {
                result[entry.getValue()] = entry.getValue();
            }
        }
        return result;
    }

    public void fillResult(List<Integer> node, int min, int[] result, List<Integer>[] draw, int current) {
        if (result[current] != -1) {
            return;
        }
        result[current] = min;
        if (node.size() > 0) {
            for (int i = 0; i < node.size(); i++) {
                fillResult(draw[node.get(i)], min, result, draw, node.get(i));
            }
        }
    }

    // 利用递归逆向指，然后填入最小值
    public int[] loudAndRich2(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] r : richer) {
            g[r[1]].add(r[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            dfs(i, quiet, g, ans);
        }
        return ans;
    }

    public void dfs(int x, int[] quiet, List<Integer>[] g, int[] ans) {
        if (ans[x] != -1) {
            return;
        }
        ans[x] = x;
        for (int y : g[x]) {
            dfs(y, quiet, g, ans);
            if (quiet[ans[y]] < quiet[ans[x]]) {
                ans[x] = ans[y];
            }
        }
    }

    public int[] loudAndRich3(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            ++inDeg[r[1]];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = i;
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x]; // 更新 x 的邻居的答案
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;
    }

}
