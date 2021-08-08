package exam;

public class Code1137 {
    public static void main(String[] args) {
        System.out.println(new Code1137().tribonacci(4));
    }

    /**
     * 先导知识快速幂模板算法
     * a^n
     * @param a
     * @param n
     * @return
     */
    int power(int a, int n)
    {
        int ans = 1;
        while (n > 0) {
            //当n为奇数时，乘以余下的一个a
            if ((n & 1) == 1) {
                ans *= a;
            }
            a *= a;
            n /= 2;
        }
        return ans;
    }

    /**
     * 矩阵快速幂经典题
     * [f(i)
     * f(i-1)
     * f(i-2)]
     * =[
     * f(i−1)∗1+f(i−2)∗1+f(i−3)∗1
     * f(i−1)∗1+f(i−2)∗0+f(i−3)∗0
     * f(i−1)∗0+f(i−2)∗1+f(i−3)∗0
     * ]
     * 又等于
     * [
     * 1 1 1
     * 1 0 0
     * 0 1 0
     * ]*
     * [
     * f(i-1)
     * f(i-2)
     * f(i-3)
     * ]
     * <p>
     * 将
     * [
     * 1 1 1
     * 1 0 0
     * 0 1 0
     * ]记作Mat则有
     * 【f(n)、f(n-1)、f(n-2)】=Mat^(n-2)*【f(2)、f(1)、f(0)】
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int[][] q = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        int[][] res = pow(q, n);
        return res[0][2];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (n > 0) {
            // n & 1奇数为1，偶数为0
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            // 右移，除以2
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
            }
        }
        return c;
    }
}
