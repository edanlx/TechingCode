package exam;

import java.util.PriorityQueue;

public class Code786 {
    public static void main(String[] args) {
        System.out.println(new Code786().kthSmallestPrimeFraction2(new int[]{1, 2, 3, 5}, 3));
    }

    /**
     * 利用小堆和指针移动找到第k个小
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> arr[x[0]] * arr[y[1]] - arr[x[1]] * arr[y[0]]);
        for (int i = 1; i < arr.length; i++) {
            pq.offer(new int[]{0, i});
        }
        for (int i = 1; i < k; i++) {
            int[] poll = pq.poll();
            // 如果分子还有移动的空间则进行移动
            if (arr[poll[0] + 1] < arr[poll[1]]) {
                pq.offer(new int[]{poll[0] + 1, poll[1]});
            }
        }
        int[] poll = pq.poll();
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }

    /**
     * 利用二分法，不断二分得到某个实数，多次循环得到刚好小于该实数的个数为k个，返回最大值
     * 通过减枝速度比小堆更优秀
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        double left = 0d;
        double right = 1d;
        while (true) {
            double mid = left + (right - left) / 2;
            int[] max = new int[]{0, arr.length - 1};
            int count = 0;
            int j = -1;
            for (int i = 1; i < arr.length; i++) {
                // 此处减枝，如果x/y小于mid那么后续x分子的y递增都小于mid
                while ((double) arr[j + 1] / arr[i] < mid) {
                    // 如果分子加1，分母不变则j++
                    j++;
                    if (arr[j] * arr[max[1]] > arr[i] * arr[max[0]]) {
                        max[0] = j;
                        max[1] = i;
                    }
                }
                count += j + 1;
            }
            if (count == k) {
                return new int[]{arr[max[0]], arr[max[1]]};
            }
            if (count < k) {
                left = mid;
            }
            if (count > k) {
                right = mid;
            }
        }
    }
}
