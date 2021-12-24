package exam;

import java.util.Arrays;

public class Code475 {


    public static void main(String[] args) {
        System.out.println(new Code475().findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(new Code475().findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
        System.out.println(new Code475().findRadius(new int[]{1, 5}, new int[]{2}));
//        System.out.println(new Code475().findRadius(new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923}, new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612}));

    }

    /**
     * 先取左侧位置和最右侧位置全覆盖的半径取最大值
     * <p>
     * 其它每个位置的房子找距离自己最近的火炉
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int result;
        result = 0;
        for (int i = 0, point = 0; i < houses.length; i++) {
            int minDistinct = Math.abs(heaters[point] - houses[i]);
            while (point < heaters.length - 1 && minDistinct >= Math.abs(heaters[point + 1] - houses[i])) {
                point++;
                minDistinct = Math.min(minDistinct, Math.abs(heaters[point] - houses[i]));
            }
            result = Math.max(minDistinct, result);
        }
        return result;
    }
}
