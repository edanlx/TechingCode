package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code1713 {
    public static void main(String[] args) {
        new Code1713().minOperations(new int[]{6, 4, 8, 1, 3, 2}, new int[]{4, 7, 6, 2, 3, 8, 6, 1});
    }

    /**
     * 最长子序列经典题，因为不需要保留之前值可以由动态规划考虑贪心算法，大大降低时间复杂度，
     * 则其特性，如果找得到比自己小的则替换，如果找不到则添加
     * 3，4，1，5，6->1,4,5,6
     * 3，4，1，2，3，5，6->1,2,3,5,6
     *
     * @param target
     * @param arr
     * @return
     */
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> pos = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            pos.put(target[i], i);
        }
        List<Integer> d = new ArrayList<Integer>();
        for (int val : arr) {
            if (pos.containsKey(val)) {
                int idx = pos.get(val);
                int it = binarySearch(d, idx);
                if (it != d.size()) {
                    d.set(it, idx);
                } else {
                    d.add(idx);
                }
            }
        }
        return n - d.size();
    }

    public int binarySearch(List<Integer> d, int target) {
        int size = d.size();
        if (size == 0 || d.get(size - 1) < target) {
            return size;
        }
        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (d.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
