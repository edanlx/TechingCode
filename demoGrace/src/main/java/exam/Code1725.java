package exam;

public class Code1725 {
    public static void main(String[] args) {
        System.out.println(new Code1725().countGoodRectangles(new int[][]{{5, 8}, {3, 9}, {5, 12}, {16, 5}}));
    }

    public int countGoodRectangles(int[][] rectangles) {
        int count = 0;
        int max = 0;
        for (int[] rectangle : rectangles) {
            int maxRect = Math.min(rectangle[0], rectangle[1]);
            if (maxRect == max) {
                count++;
                continue;
            }
            if (maxRect > max) {
                max = maxRect;
                count = 1;
            }
        }
        return count;
    }
}
