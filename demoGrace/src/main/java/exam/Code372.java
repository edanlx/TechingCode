package exam;

/**
 * 现在最外层按此公式拆解
 * 公式一：(a*b)%k=[(a%k) * (b%k)]%k
 * 公式二：a^11 = (a^1)^10 * a^1
 * 内层再按照快速乘拆解
 */
public class Code372 {
    int base = 1337;

//    public static void main(String[] args) {
//        System.out.println(new Code372Write().superPow(2, new int[]{1, 0}));
//    }

    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    public int dfs(int a, int[] b, int idx) {
        if (idx == -1) {
            return 1;
        }
        return qpow(dfs(a, b, idx - 1), 10) * qpow(a, b[idx]) % base;
    }

    public int qpow(int a, int b) {
        int result = 1;
        a = a % base;
        while (b != 0) {
            if ((b & 1) != 0) {
                // 奇数
                result = result * a % base;
            }
            a = a * a % base;
            b = b >> 1;
        }
        return result;
    }

    public static int myqpow(int a, int b) {
        int result = 1;
        a = a;
        while (b != 0) {
            if ((b & 1) != 0) {
                result = result * a;
            }
            a = a * a;
            b = b >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myqpow(2,10));
    }
}
