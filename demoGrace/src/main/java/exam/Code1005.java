package exam;

import java.util.PriorityQueue;

public class Code1005 {

    public static void main(String[] args) {
        System.out.println(new Code1005().largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));

    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        // 小堆负数集合
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                pq.add(nums[i]);
            } else {
                min = Math.min(min, nums[i]);
                // 所有正数相加
                sum += nums[i];
            }
        }
        int size = pq.size();
        if (pq.size() >= k) {
            // 负数大于等于k则全部转化负负得正，剩下的累加
            for (int i = 1; i <= size; i++) {
                if (i > k) {
                    sum += pq.poll();
                } else {
                    sum += -1 * pq.poll();
                }
            }
        } else {
            // 负数小于k，全部转化负负得正，余下的次数如果是偶数则直接返回，如果是奇数变为负数，即减去两次最小值
            if ((Math.abs(k - pq.size()) & 1) == 1) {
                // 奇数
                for (int i = 1; i <= size; i++) {
                    int temp = -1 * pq.poll();
                    sum += temp;
                    min = Math.min(min, temp);
                }
                sum = sum - min - min;
            } else {
                // 偶数
                for (int i = 1; i <= size; i++) {
                    sum += -1 * pq.poll();
                }
            }
        }
        return sum;
    }
}
