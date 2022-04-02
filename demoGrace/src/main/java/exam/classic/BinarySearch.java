package exam.classic;

public class BinarySearch {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 9, 12, 18};
        System.out.println(new BinarySearch().find(18, ints, 0, ints.length - 1));
    }

    public int find(int target, int[] ints, int left, int right) {
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (ints[mid] == target) {
                return mid;
            } else if (target > ints[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
