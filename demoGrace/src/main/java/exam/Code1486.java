package exam;

public class Code1486 {
    public static void main(String[] args) {
        System.out.println(new Code1486().xorOperation(5, 0));;
    }

    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans ^= (start + i * 2);
        }
        return ans;
    }
}
