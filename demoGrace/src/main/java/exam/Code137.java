package exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code137 {

    public static void main(String[] args) {
        new Code137().singleNumber(new int[]{2, 2, 3, 2});
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
        Arrays.stream(nums).forEach(s -> map.put(s, map.getOrDefault(s, 0) + 1));
        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            if (ent.getValue() == 1) {
                return ent.getKey();
            }
        }
        return 0;
    }
}
