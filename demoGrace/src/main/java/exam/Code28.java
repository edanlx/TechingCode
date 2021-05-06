package exam;

public class Code28 {
    public static void main(String[] args) {
        System.out.println(new Code28().strStr("abc", "c"));
        ;
    }

    public int strStr(String haystack, String needle) {
        int result = -1;
        if (haystack.equals(needle)) {
            return 0;
        }
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            String substring = haystack.substring(i, needle.length() + i);
            if (substring.equals(needle)) {
                return i;
            }
        }
        return result;
    }

    public int strStr2(String haystack, String needle) {
        int result = -1;
        if (haystack.equals(needle)) {
            return 0;
        }
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        byte[] bytes1 = haystack.getBytes();
        byte[] bytes2 = needle.getBytes();
        for (int i = 0; i < bytes1.length; i++) {
            if (result != -1) {
                break;
            }
            if (bytes1[i] != bytes2[0]) {
                continue;
            }
            for (int j = 0; j < bytes2.length; j++) {
                if (i + j >= bytes1.length) {
                    break;
                }
                if (bytes1[i + j] != bytes2[j]) {
                    break;
                }
                if (j == bytes2.length - 1) {
                    result = i;
                }
            }
        }
        return result;
    }
}
