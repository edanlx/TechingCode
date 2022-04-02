package exam.classic;

public class KMP {
    public static void main(String[] args) {
        getInts("aabaaabaabaaab");
    }

    private static int[] getInts(String a) {
        int[] aState = new int[a.length()];
        for (int i = 1, j = 0; i < a.length(); i++) {
            // aabaaa
            // 前一个位置比如存x，则意味[i-1-x,i-1]=[0,x-1],此时查看[x]和[i]是否相等，如果相等则意味着此时[0,x]=[i-x,i]
            // 前一个位置比如存2，则意味[5-1-2,5-1]=[3,4]=[0,1],此时查看[2]和[5]是否相等，如果相等则意味着此时
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
}
