package exam;

public class Code689 {

    public static void main(String[] args) {
        new Code689().maxSumOfThreeSubarrays(new int[]{20, 18, 9, 2, 14, 1, 10, 3, 11, 18}, 3);
    }

    /**
     * 1.找到三个数字互相下标不重叠且和最大的子数组，长度为k
     * <p>
     * 思路：
     * 记录卡组3的maxIdx和CurIdx
     * <p>
     * 记录卡组2的maxIdx和CurIdx
     * <p>
     * 记录卡组1的maxIdx和CurIdx
     * <p>
     * 需要注意的是如果3往右动，2也往右动变大的可能性所以另外保存该结果
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        // 当前指针位置
        int curIdx3 = k * 2;
        // 最大值指针位置
        int maxIdx3 = curIdx3;
        int curSum3 = 0;
        int maxSum3 = 0;

        int curIdx2 = k * 1;
        int maxIdx2 = curIdx2;
        int curSum2 = 0;
        int maxSum2 = 0;

        int curIdx1 = k * 0;
        int maxIdx1 = curIdx1;
        int curSum1 = 0;
        int maxSum1 = 0;
        for (int i = 0; i < curIdx3 + k; i++) {
            if (i < curIdx2) {
                curSum1 += nums[i];
            } else if (i < curIdx3) {
                curSum2 += nums[i];
            } else {
                curSum3 += nums[i];
            }
        }
        maxSum1 = curSum1;
        int cureSum23 = curSum2 + curSum3;
        int cureSum12 = curSum1 + curSum2;
        int maxSum12 = cureSum12;
        int maxSumIdx12 = curIdx1;
        int maxSumIdx23 = curIdx2;
        int cureSum123 = curSum1 + curSum2 + curSum3;
        int maxSum123 = cureSum123;
        ans[0] = maxSumIdx12;
        ans[1] = maxSumIdx23;
        ans[2] = curIdx3;
        for (int i = curIdx3 + k; i < nums.length; i++) {
            curSum3 = curSum3 + nums[i] - nums[curIdx3];
            curSum2 = curSum2 + nums[curIdx3] - nums[curIdx2];
            curSum1 = curSum1 + nums[curIdx2] - nums[curIdx1];
            curIdx3++;
            curIdx2++;
            curIdx1++;
            cureSum12 = curSum1 + curSum2;
            cureSum23 = curSum2 + curSum3;
            cureSum123 = curSum1 + curSum2 + curSum3;
            // 判断当前移动是否可以让子集1移动到当前最前面预处理
            if (curSum1 > maxSum1) {
                maxSum1 = curSum1;
                maxIdx1 = curIdx1;
            }
            // 判断当前子集2是否能够移动到最前面预处理
            if (maxSum1 + curSum2 > maxSum12) {
                maxSum12 = maxSum1 + curSum2;
                maxSumIdx12 = maxIdx1;
                maxSumIdx23 = curIdx2;
            }
            // 判断当前子集3是否能够移动到最前面，如果子集3能够移动则前面的赋值全部生效
            if (maxSum12 + curSum3 > maxSum123) {
                maxSum123 = maxSum12 + curSum3;
                ans[0] = maxSumIdx12;
                ans[1] = maxSumIdx23;
                ans[2] = curIdx3;
            }
        }
        return ans;
    }
}
