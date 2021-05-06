package exam;

public class Code1011 {

    public int shipWithinDays(int[] weights, int D) {
        int result = 0;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            result = Math.max(result, weight);
        }
        result = (int) Math.max(Math.ceil((double) sum / (double) weights.length), result);
        outer:
        for (; ; result++) {
            int tempSum = 0;
            for (int i = 0, j = 0; i < weights.length; ) {
                tempSum += weights[i];
                if (tempSum > result) {
                    tempSum = 0;
                    j++;
                } else {
                    i++;
                }
                if (j >= D) {
                    break;
                }
                if (i == weights.length) {
                    break outer;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Code1011().shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }
}
