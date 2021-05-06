package exam;

public class Code27 {
    public static void main(String[] args) {
        System.out.println(new Code27().removeElement(new int[]{3, 2, 2, 3}, 3));
    }

    public int removeElement(int[] nums, int val) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                int temp = nums[i];
                nums[i] = nums[result];
                nums[result] = temp;
                result++;
            }
        }
        return result;
    }
}
