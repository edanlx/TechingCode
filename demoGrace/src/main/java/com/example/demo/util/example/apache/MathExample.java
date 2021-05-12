package com.example.demo.util.example.apache;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class MathExample {
    public static void main(String[] args) {
        // 如果不涉及统计类或者数学公式计算应该都用不上
        // MathUtils;
        // CombinatoricsUtils
        // ArithmeticUtils
        double[] values = new double[] { 0.33, 1.33,0.27333, 0.3, 0.501,
                0.444, 0.44, 0.34496, 0.33,0.3, 0.292, 0.667 };
        double[] values2 = new double[] { 0.89, 1.51,0.37999, 0.4, 0.701,
                0.484, 0.54, 0.56496, 0.43,0.3, 0.392, 0.567 };

        //计数
        System.out.println("计算样本个数为：" +values.length);
        //mean--算数平均数
        System.out.println("平均数：" + StatUtils.mean(values));
        //sum--和
        System.out.println("所有数据相加结果为：" + StatUtils.sum(values));
        //max--最小值
        System.out.println("最小值：" + StatUtils.min(values));
        //max--最大值
        System.out.println("最大值：" + StatUtils.max(values));
        //范围
        System.out.println("范围是：" + (StatUtils.max(values)-StatUtils.min(values)));
        //标准差
        StandardDeviation standardDeviation =new StandardDeviation();
        System.out.println("一组数据的标准差为：" + standardDeviation.evaluate(values));
        //variance--方差
        System.out.println("一组数据的方差为：" + StatUtils.variance(values));
        //median--中位数
        Median median= new Median();
        System.out.println("中位数：" + median.evaluate(values));
        //mode--众数
        double[] res = StatUtils.mode(values);
        System.out.println("众数：" + res[0]+","+res[1]);
        for(int i = 0;i<res.length;i++){
            System.out.println("第"+(i+1)+"个众数为："+res[i]);
        }
        //geometricMean--几何平均数
        System.out.println("几何平均数为：" +StatUtils.geometricMean(values));
        //meanDifference-- 平均差，平均概率偏差
        System.out.println("平均差为："+StatUtils.meanDifference(values, values2));
        //normalize--标准化
        double[] norm = StatUtils.normalize(values2);
        for(int i = 0;i<res.length;i++){
            System.out.println("第"+(i+1)+"个数据标准化结果为：" + norm[i]);
        }
        //percentile--百分位数
        System.out.println("从小到大排序后位于80%位置的数：" + StatUtils.percentile(values, 70.0));
        //populationVariance--总体方差
        System.out.println("总体方差为：" + StatUtils.populationVariance(values));
        //product--乘积
        System.out.println("所有数据相乘结果为：" + StatUtils.product(values));
        //sumDifference--和差
        System.out.println("两样本数据的和差为：" + StatUtils.sumDifference(values,values2));
        //sumLog--对数求和
        System.out.println("一组数据的对数求和为：" + StatUtils.sumLog(values));
        //sumSq--计算一组数值的平方和
        System.out.println("一组数据的平方和：" + StatUtils.sumSq(values));
        //varianceDifference --方差差异性。
        System.out.println("一组数据的方差差异性为：" + StatUtils.varianceDifference(values,values2,StatUtils.meanDifference(values, values2)));
    }
}
