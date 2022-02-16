package exam;

import java.util.HashMap;
import java.util.Map;

public class Code1748 {
    public static void main(String[] args) {
        System.out.println(new Code1748().sumOfUnique(new int[]{1, 1, 1, 1}));
    }

    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            Integer integer = map.get(num);
            if (integer == null) {
                sum += num;
                map.put(num, 1);
            } else {
                if (integer == 1) {
                    sum -= num;
                }
                map.put(num, integer + 1);

            }
        }
        return sum;
    }
}
