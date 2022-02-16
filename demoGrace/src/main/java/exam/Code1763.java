package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code1763 {
    public static void main(String[] args) {
        // new Code1763().longestSubstring("ababbc", 2);
        new Code1763().longestNiceSubstring("YazaAay");
    }

    private int maxPos;
    private int maxLen;

    public String longestNiceSubstring(String s) {
        this.maxPos = 0;
        this.maxLen = 0;

        int types = 0;
        for (int i = 0; i < s.length(); ++i) {
            types |= 1 << (Character.toLowerCase(s.charAt(i)) - 'a');
        }
        types = Integer.bitCount(types);
        for (int i = 1; i <= types; ++i) {
            check(s, i);
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    public void check(String s, int typeNum) {
        int[] lowerCnt = new int[26];
        int[] upperCnt = new int[26];
        // 大小写同时存在的字符种类数
        int cnt = 0;
        for (int l = 0, r = 0, total = 0; r < s.length(); ++r) {
            int idx = Character.toLowerCase(s.charAt(r)) - 'a';
            // 维护大小写字母出现情况
            if (Character.isLowerCase(s.charAt(r))) {
                ++lowerCnt[idx];
                if (lowerCnt[idx] == 1 && upperCnt[idx] > 0) {
                    ++cnt;
                }
            } else {
                ++upperCnt[idx];
                if (upperCnt[idx] == 1 && lowerCnt[idx] > 0) {
                    ++cnt;
                }
            }
            // 计算当前滑动窗口内的字符种类数目total
            total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
            // 如果字符种类数目大于所需数目则右移左边界，同时减去之前维护的各个状态
            while (total > typeNum) {
                idx = Character.toLowerCase(s.charAt(l)) - 'a';
                total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
                if (Character.isLowerCase(s.charAt(l))) {
                    --lowerCnt[idx];
                    if (lowerCnt[idx] == 0 && upperCnt[idx] > 0) {
                        --cnt;
                    }
                } else {
                    --upperCnt[idx];
                    if (upperCnt[idx] == 0 && lowerCnt[idx] > 0) {
                        --cnt;
                    }
                }
                ++l;
            }
            // 大小写同时存在的字符种类数等于内部维护的字符数
            if (cnt == typeNum && r - l + 1 > maxLen) {
                maxPos = l;
                maxLen = r - l + 1;
            }
        }
    }
//
//    private int maxPos;
//    private int maxLen;
//
//    public String longestNiceSubstring(String s) {
//        this.maxPos = 0;
//        this.maxLen = 0;
//        dfs(s, 0, s.length() - 1);
//        return s.substring(maxPos, maxPos + maxLen);
//    }

    public void dfs(String s, int start, int end) {
        if (start >= end) {
            return;
        }
        int lower = 0, upper = 0;
        for (int i = start; i <= end; ++i) {
            if (Character.isLowerCase(s.charAt(i))) {
                lower |= 1 << (s.charAt(i) - 'a');
            } else {
                upper |= 1 << (s.charAt(i) - 'A');
            }
        }
        if (lower == upper) {
            if (end - start + 1 > maxLen) {
                maxPos = start;
                maxLen = end - start + 1;
            }
            return;
        }
        // 大小写都有的进行保留
        int valid = lower & upper;
        int pos = start;
        while (pos <= end) {
            start = pos;
            // 按照里面全部都是重复元素进行切分,但当前切分可能并不包含全部大小写，dfs进行校验
            while (pos <= end && (valid & (1 << Character.toLowerCase(s.charAt(pos)) - 'a')) != 0) {
                // 有小写的进入
                ++pos;
            }
            dfs(s, start, pos - 1);
            ++pos;
        }
    }


    public int longestSubstring(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // 找到一个不符合的
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        // 全部都符合则直接返回
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            // 左边该字母剔除
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            // 右侧该字母剔除，如果不等于该字母则向有移动
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            // 上述步骤剔除一个不喝规范的字母

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
}
