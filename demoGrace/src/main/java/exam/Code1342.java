package exam;

public class Code1342 {
    public static void main(String[] args) {
        System.out.println(new Code1342().numberOfSteps(8));
    }

    public int numberOfSteps(int num) {
        int step = 0;
        while (true) {
            if (num == 0) {
                return step;
            }
            if ((num & 1) == 0) {
                num = num >> 1;
            } else {
                num--;
            }
            step++;
        }
    }
}
