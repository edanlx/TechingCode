package exam;

import java.util.*;

public class Code403 {

    /**
     * 滚动向前，则每个位置可能会有多个选择
     * 如0号位则有初始为0，则仅可选择step1
     * 1号位则有step=2，和step=1两种选择
     * 则有单元格3
     * <p>
     * 0,1,3,5,6,8,12,17则有true
     * 0,1,2,3,4,8,9,11则有false
     * 0,2,有false
     * [0,1,3,6,10,15,16,21]则有true
     */
    public static void main(String[] args) {
        System.out.println(new Code403().canCross(new int[]{0, 1, 3, 6, 10, 15, 16, 21}));
    }

    public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> memory = new HashMap<>(stones.length);
        memory.put(0, new HashSet<Integer>() {{
            add(0);
        }});
        for (int stone : stones) {
            Set<Integer> integers = memory.get(stone);
            if (integers == null) {
                return false;
            }
            for (Integer integer : integers) {
                if (integer >= 2) {
                    Set<Integer> tempList1 = memory.getOrDefault(stone + integer - 1, new HashSet<>());
                    tempList1.add(integer - 1);
                    memory.put(stone + integer - 1, tempList1);
                }
                Set<Integer> tempList2 = memory.getOrDefault(stone + integer, new HashSet<>());
                tempList2.add(integer);
                memory.put(stone + integer, tempList2);
                Set<Integer> tempList3 = memory.getOrDefault(stone + integer + 1, new HashSet<>());
                tempList3.add(integer + 1);
                memory.put(stone + integer + 1, tempList3);
            }
        }
        return true;
    }

    public boolean canCross2(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k > j + 1) {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
