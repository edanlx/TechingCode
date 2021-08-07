package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code1743 {
    public static void main(String[] args) {
        // int[] ints = new Code1743().restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}});
        int[] ints = new Code1743().restoreArray(new int[][]{{4, -2}, {1, 4}, {-3, 1}});
        System.out.println();
    }

    /**
     * 除去第一个先循环一遍，将其放入mapList
     *
     * @param adjacentPairs
     * @return
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<int[]>> map = new HashMap<>(adjacentPairs.length * 2);
        if (adjacentPairs.length == 1) {
            return adjacentPairs[0];
        }
        List<Integer> result = new ArrayList<>();
        for (int[] adjacentPair : adjacentPairs) {
            for (int i : adjacentPair) {
                List<int[]> tempList = map.getOrDefault(i, new ArrayList<>(2));
                tempList.add(adjacentPair);
                map.put(i, tempList);
            }
        }
        result.add(adjacentPairs[0][0]);
        result.add(adjacentPairs[0][1]);
        // 头部循环
        while (true) {
            List<int[]> ints = map.get(result.get(0));
            if (ints.size() == 1) {
                break;
            }
            int first = result.get(0);
            int second = result.get(1);
            int newFirst = 0;
            oo:
            for (int i = 0; i < ints.size(); i++) {
                for (int j = 0; j < ints.get(i).length; j++) {
                    int temp = ints.get(i)[j];
                    if (temp != first && temp != second) {
                        newFirst = temp;
                        break oo;
                    }
                }
            }
            result.add(0, newFirst);
        }

        // 尾部循环
        while (true) {
            List<int[]> ints = map.get(result.get(result.size() - 1));
            if (ints.size() == 1) {
                break;
            }
            int first = result.get(result.size() - 1);
            int second = result.get(result.size() - 2);
            int newFirst = 0;
            oo:
            for (int i = 0; i < ints.size(); i++) {
                for (int j = 0; j < ints.get(i).length; j++) {
                    int temp = ints.get(i)[j];
                    if (temp != first && temp != second) {
                        newFirst = temp;
                        break oo;
                    }
                }
            }
            result.add(newFirst);
        }


        int[] ints = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ints[i] = result.get(i);
        }
        return ints;
    }
}
