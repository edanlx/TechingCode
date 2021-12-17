package exam;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code630 {
    /**
     * 优先安排结束早的
     * 储存结束时间
     * 如果有时长更短的则弹出最长的时间
     *
     * @param args
     */
    public static void main(String[] args) {
//         new Code630().scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}});
        new Code630().scheduleCourse(new int[][]{{5, 5}, {4, 6}, {2, 6}});

    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int min = 0;
        int total = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < courses.length; i++) {
            int[] peek = pq.peek();
            if (min + courses[i][0] <= courses[i][1]) {
                total++;
                min += courses[i][0];
                pq.add(courses[i]);
                continue;
            }
            if (peek != null && courses[i][0] <= peek[0]) {
                pq.poll();
                min -= peek[0];
                min += courses[i][0];
                pq.add(courses[i]);
            }
        }
        return total;
    }
}
