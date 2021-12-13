package exam;

public class Code1446 {
    public static void main(String[] args) {
        System.out.println(new Code1446().maxPower("leetcode"));
    }

    public int maxPower(String s) {
        int max = 1;
        char pre = s.charAt(0);
        int curLength = 1;
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == pre) {
                curLength++;
                max = Math.max(curLength, max);
            } else {
                curLength = 1;
                pre = cur;
            }
        }
        return max;
    }
}
