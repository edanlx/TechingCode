package com.example.demo.util.example.apache;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.list.PredicatedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionExample {
    public static void main(String[] args) {
        // 以下5个方法经常用于判空，返回空数组等避免重复创建对象
        // CollectionUtils
        // ListUtils
        // MapUtils
        // SetUtils
        // QueueUtils

        // CollectionUtilsExample();
        // predicateUtilsExample();

        // 以下为非重要方法
        // BagUtils
        // ClosureUtils
        // ComparatorUtils
        // EnumerationUtils
        // FactoryUtils
        // IterableUtils
        // IteratorUtils
        // MultiMapUtils
        // MultiSetUtils
        // PredicateUtils
        // SplitMapUtils
        // TransformerUtils
        // TrieUtils
    }

    public static void CollectionUtilsExample() {
        // addAll 改变原数组
        List<Integer> list = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        List<Integer> list2 = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        System.out.println(CollectionUtils.addAll(
                list,
                list2
        ));
        System.out.println(list);

        //循环获得新数组，不如用直接用stream
        list = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        System.out.println(CollectionUtils.collect(list, x -> (x + 1)));

        // 根据hash值返回下标，似乎毫无作用
        System.out.println("get");
        System.out.println(CollectionUtils.get(new HashMap<String, String>(3) {
            {
                put("10", "1");
                put("2", "2");
                put("3", "3");
            }
        }, 0).getKey());

        //比filter更简洁的写法，但是似乎意义不大
        System.out.println(CollectionUtils.select(list, x -> x > 1));

        //只用这个比较好用
        CollectionUtils.isEmpty(list2);

        //这个也还凑合 防止各种NPE
        Collections.emptyList();
        Collections.emptyMap();
        Collections.emptySet();
        Collections.singletonList("1");
        Collections.singletonMap("1", "2");
    }

    public static void predicateUtilsExample() {
        // 验证
        // OnePredicate.onePredicate() 一个
        // UniquePredicate.uniquePredicate() 不可重复
        // ComparatorPredicate.comparatorPredicate 比较

        Predicate<Integer> stringPredicate = PredicateUtils.allPredicate(
                NotNullPredicate.notNullPredicate(),
                UniquePredicate.uniquePredicate()
        );
        List<Integer> list = PredicatedList.predicatedList(new ArrayList<>(), stringPredicate);
        list.add(1);
        // list.add(1);
        // list.add(null);
        System.out.println(list);
    }
}
