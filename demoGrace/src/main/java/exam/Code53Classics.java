package exam;

public class Code53Classics {

    public static void main(String[] args) {
        System.out.println(new Code53Classics().maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /**
     * 从左到右相加如果小于零则整体丢弃，但最大值已然保留
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * 进阶，求任意子线段最大和
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        return handleData(0, nums.length - 1, nums).mSum;
    }

    /**
     * 即左边最大值(取左侧mSum)或右边最大值(取右侧mSum)
     * 左边延续到右边的最大值(左侧必须以当前middle为右终点时的最大值(定义左侧的rSum)+右侧必须以当前middle为左终点时的最大值(定义右侧的lSum))
     * <p>
     * 那么当前节点的mSum即为上述取最大，
     * 当前节点的lSum即为子左节点的lSum或者右子节点的lSum+左侧全部值
     * <p>
     * lSum 表示 [l,r] 内以 l 为左端点的最大子段和
     * rSum 表示 [l,r] 内以 r 为右端点的最大子段和
     * mSum 表示 [l,r] 内的最大子段和
     * iSum 表示 [l,r] 的区间和
     */

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status() {
        }

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    private Status handleData(int left, int right, int[] nums) {
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        Status status = new Status();
        int middle = (left + right) >> 1;
        Status statusLeft = handleData(left, middle, nums);
        Status statusRight = handleData(middle + 1, right, nums);
        status.lSum = Math.max(statusLeft.lSum, statusRight.lSum + statusLeft.iSum);
        status.rSum = Math.max(statusRight.rSum, statusLeft.rSum + statusRight.iSum);
        status.iSum = statusLeft.iSum + statusRight.iSum;
        status.mSum = Math.max(Math.max(statusLeft.mSum, statusRight.mSum), statusLeft.rSum + statusRight.lSum);
        return status;
    }
}
