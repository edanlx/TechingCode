package exam;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Code91 {
    /**
     * 12->2
     * 226->3
     * 10->1
     * 2101->1
     * 0->0
     * 1123->5
     */
    public static void main(String[] args) {
        System.out.println(new Code91().numDecodings("1123"));
    }

    /**
     * 分析题目，已知最多是2位数
     * 如果在第x位有n种分发，则x+1位有几种?
     * 若x与x+1可以组成数字则有n种解法
     * 若x与x+1不可以组成数字则为n种解法(与前一个结合则需要前一个的f(x-1)的解法)
     * <p>
     * 那么第0位则有1种
     * <p>
     * 注意如果是0，那么0只能和上一位进行组队，如果上一位和上上位组队则有问题
     */
    public int numDecodings(String s) {
        int result = 0;
        if (s.startsWith("0")) {
            return 0;
        }
        Set<String> collect = IntStream.rangeClosed(1, 26).boxed().map(l -> l + "").collect(Collectors.toSet());
        String pre = "";
        String next = "";
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (i != s.length() - 1) {
                next = String.valueOf(s.charAt(i + 1));
            } else {
                next = "";
            }
            if (!"0".equals(next) && !"0".equals(c)) {
                if (collect.contains(pre.concat(c))) {
                    result++;
                }
            }
            pre = c;
        }
        return Math.max(result, 1);
    }
}
