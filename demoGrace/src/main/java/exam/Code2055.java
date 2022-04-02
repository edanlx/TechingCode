package exam;

import java.util.Map;
import java.util.TreeMap;

public class Code2055 {
    public static void main(String[] args) {
//        new Code2055().platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}});
//        new Code2055().platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}});
        new Code2055().platesBetweenCandles("||*", new int[][]{{2, 2}});

    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] result = new int[queries.length];
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                map.put(i, j++);
            }
        }
        for (int i = 0; i < queries.length; i++) {
            Map.Entry<Integer, Integer> leftEntry = map.ceilingEntry(queries[i][0]);
            Map.Entry<Integer, Integer> rightEntry = map.floorEntry(queries[i][1]);
            if (leftEntry == null || rightEntry == null) {
                result[i] = 0;
                continue;
            }
            result[i] = Math.max(0, rightEntry.getKey() - leftEntry.getKey() - (rightEntry.getValue() - leftEntry.getValue()));
        }
        return result;
    }
}
