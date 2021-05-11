package exam;

public class Code1723 {
    public static void main(String[] args) {
        new Code1723().minimumTimeRequired(new int[]{3, 2, 3}, 3);
    }

    /**
     * 即将jobs切割成k个，使其加起来后相对平均，返回最大值
     * 思路1，从大到小进行分配，每次分配给最小的
     * 思路2，从小到大进行分配，每次分给最大的
     *
     * 思路3,进行最大最小2分获得limit，然后给每个工人进行组合不大于limit->引申问题将limit恰好完全分配
     *
     * @param jobs 工作数，需要分配给
     * @param k 人数
     * @return {@link int}
     * @author 876651109@qq.com
     * @date 2021/5/8 5:42 下午
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        
        return 0;
    }
}
