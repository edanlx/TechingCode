package exam;

public class Code686 {
    public static void main(String[] args) {
        System.out.println(new Code686().repeatedStringMatch2("abcd", "cdabcdab"));
//        System.out.println(new Code686().repeatedStringMatch("a", "aa"));
//        System.out.println(new Code686().repeatedStringMatch("a", "a"));
//        System.out.println(new Code686().repeatedStringMatch("aa", "a"));
//        System.out.println(new Code686().repeatedStringMatch("abc", "wxyz"));
//        System.out.println(new Code686().repeatedStringMatch("aaac", "aac"));
//        System.out.println(new Code686().repeatedStringMatch("abc", "cabcabca"));
//        System.out.println(new Code686().repeatedStringMatch("aaaaaaaaaaaaaaaaaaaaaab", "ba"));
//        System.out.println(new Code686().repeatedStringMatch("abcd", "bcdab"));
    }

    public int repeatedStringMatch(String a, String b) {
        if (b.length() == 0) {
            return 0;
        }
        int[] aState = getInts(a);

        // 是否有第一次匹配成功
        boolean matchFirst = false;
        int result = -1;
        for (int i = 0, point = 0; i < b.length(); i++) {
            if (matchFirst) {
                break;
            }
            if (i == 2 * a.length()) {
                break;
            }
            while (point > 0 && b.charAt(i) != a.charAt(point)) {
                point = aState[point - 1];
            }
            if (b.charAt(i) == a.charAt(point)) {
                point++;
                if (point == a.length()) {
                    matchFirst = true;
                    result = 1;
                    int postIdx = i - a.length();
                    // 向前检查
                    for (int j = 0; j < postIdx + 1; j++) {
                        if (b.charAt(j) != a.charAt(a.length() - postIdx - 1 + j)) {
                            return -1;
                        }
                    }
                    if (postIdx != -1) {
                        result++;
                    }
                    // 向后检查
                    for (int j = i + 1; j < b.length(); j++, point++) {
                        if (point == a.length()) {
                            result++;
                            point = 0;
                        }
                        if (b.charAt(j) != a.charAt(point)) {
                            return -1;
                        }
                    }
                }
            }
        }
        if (result == -1 && b.length() < a.length() * 2) {
            int point = 0;
            int[] bState = getInts(b);
            String aa = a + a;
            for (int i = 0; i < aa.length(); i++) {
                while (point > 0 && aa.charAt(i) != b.charAt(point)) {
                    point = bState[point - 1];
                }
                if (aa.charAt(i) == b.charAt(point)) {
                    point++;
                    if (point == b.length()) {
                        if (i >= aa.length() / 2) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    private int[] getInts(String a) {
        int[] aState = new int[a.length()];
        for (int i = 1, j = 0; i < a.length(); i++) {
            while (j > 0 && a.charAt(i) != a.charAt(j)) {
                j = aState[j - 1];
            }
            if (a.charAt(i) == a.charAt(j)) {
                j++;
                aState[i] = j;
            }
        }
        return aState;
    }


    public int repeatedStringMatch2(String a, String b) {
        int an = a.length(), bn = b.length();
        // 返回第一次出现的位置
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        }
        if (an - index >= bn) {
            return 1;
        }
        // 计算得到需要拼接几次
        return (bn + index - an - 1) / an + 2;
    }

    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i - j < n; i++) { // b 开始匹配的位置是否超过第一个叠加的 a
            while (j > 0 && haystack.charAt(i % n) != needle.charAt(j)) { // haystack 是循环叠加的字符串，所以取 i % n
                j = pi[j - 1];
            }
            if (haystack.charAt(i % n) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
