package com.example.demo.util;

import com.example.demo.bean.TestTreeObj;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

;

/**
 * 树和平铺相关工具类
 *
 * @author 876651109@qq.com
 * @date 2021/3/1 8:21 下午
 */
public class TreeUtils {
    public static void main(String[] args) {
        List<TestTreeObj> list = new ArrayList<TestTreeObj>() {{
            add(TestTreeObj.builder().id(1).build());
            add(TestTreeObj.builder().id(11).pid(1).build());
            add(TestTreeObj.builder().id(12).pid(1).build());
            add(TestTreeObj.builder().id(111).pid(11).build());
            add(TestTreeObj.builder().id(112).pid(11).build());
            add(TestTreeObj.builder().id(121).pid(12).build());
            add(TestTreeObj.builder().id(122).pid(12).build());
            add(TestTreeObj.builder().id(2).build());
            add(TestTreeObj.builder().id(21).pid(2).build());
            add(TestTreeObj.builder().id(22).pid(2).build());
            add(TestTreeObj.builder().id(211).pid(21).build());
            add(TestTreeObj.builder().id(212).pid(21).build());
            add(TestTreeObj.builder().id(221).pid(22).build());
            add(TestTreeObj.builder().id(222).pid(22).build());
        }};
        List<TestTreeObj> treeResult = listToTree(list, TestTreeObj::setTestTreeObj, TestTreeObj::getId, TestTreeObj::getPid, (l) -> l.getPid() == 0);

        List<TestTreeObj> testTreeObjs = new ArrayList<TestTreeObj>() {{
            add(TestTreeObj.builder().id(1).testTreeObj(new ArrayList<TestTreeObj>() {{
                add(TestTreeObj.builder().id(11).testTreeObj(new ArrayList<TestTreeObj>() {{
                    add(TestTreeObj.builder().id(111).build());
                    add(TestTreeObj.builder().id(112).build());
                }}).build());
            }}).build());
        }};
        List<TestTreeObj> result = new ArrayList<>();
        treeToListDeep(testTreeObjs, result, TestTreeObj::getTestTreeObj, (l) -> l.getTestTreeObj() == null);
        List<TestTreeObj> result2 = new ArrayList<>();
        treeToListDeep(testTreeObjs, result2, TestTreeObj::getTestTreeObj, (l) -> l.getPid() == 0);
        System.out.println(result2);
        System.out.println("loopTree示例过滤出以1结尾的id");
        List<TestTreeObj> filterResult = new ArrayList<>();
        loopTree(testTreeObjs, TestTreeObj::getTestTreeObj, s -> {
            if (String.valueOf(s.getId()).endsWith("1")) {
                filterResult.add(s);
            }
        });
        System.out.println(filterResult.size());
    }

    /**
     * 树转平铺
     * treeToListDeep(testTreeObjs, result, TestTreeObj::getTestTreeObj, (l) -> l.getTestTreeObj() == null);
     *
     * @param source             源数据
     * @param target             目标容器
     * @param childListFn        递归调用方法
     * @param addTargetCondition 添加到容器的判断方法
     * @author 876651109@qq.com
     * @date 2021/3/1 8:19 下午
     */
    public static <F> void treeToListDeep(List<F> source, List<F> target, Function<F, List<F>> childListFn, Predicate<F> addTargetCondition) {
        loopTree(source, childListFn, (l) -> {
            if (addTargetCondition.test(l)) {
                target.add(l);
            }
        });
    }

    public static <F> void treeToListDeep(List<F> source, Function<F, List<F>> childListFn, Predicate<F> addTargetCondition) {
        treeToListDeep(source, new ArrayList<>(), childListFn, addTargetCondition);
    }

    /**
     * List<TestTreeObj> treeResult = listToTree(list, TestTreeObj::setTestTreeObj, TestTreeObj::getId, TestTreeObj::getPid, (l) -> l.getPid() == 0);
     *
     * @param source           源数据
     * @param setChildListFn   设置递归的方法
     * @param idFn             获取id的方法
     * @param pidFn            获取父id的方法
     * @param getRootCondition 获取根节点的方法
     * @return {@link List<F>}
     * @author 876651109@qq.com
     * @date 2021/3/1 8:18 下午
     */
    public static <F, T> List<F> listToTree(List<F> source, BiConsumer<F, List<F>> setChildListFn, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition) {
        return listToTree(source, setChildListFn, idFn, pidFn, getRootCondition, null);
    }

    /**
     * 复杂形式，进行listen回调,用流式写法可以与外界交互
     * (idx,obj)->{System.out.println(123);},idx是从0开始的层级
     *
     * @param listen 回调函数
     * @author seal 876651109@qq.com
     * @date 2021/6/3 7:46 下午
     */
    public static <F, T> List<F> listToTree(List<F> source, BiConsumer<F, List<F>> setChildListFn, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition, BiConsumer<Integer, F> listen) {
        List<F> tree = new ArrayList<>();
        Map<T, List<F>> map = new HashMap<>(source.size());
        for (F f : source) {
            if (getRootCondition.test(f)) {
                tree.add(f);
            } else {
                List<F> tempList = map.getOrDefault(pidFn.apply(f), new ArrayList<>());
                tempList.add(f);
                map.put(pidFn.apply(f), tempList);
            }
        }
        tree.forEach(l -> assembleTree(l, map, setChildListFn, idFn, listen, 0));
        return tree;
    }

    /**
     * 组装树
     */
    private static <F, T> void assembleTree(F current, Map<T, List<F>> map, BiConsumer<F, List<F>> setChildListFn, Function<F, T> idFn, BiConsumer<Integer, F> listen, int idx) {
        List<F> fs = map.get(idFn.apply(current));
        setChildListFn.accept(current, fs);
        if (CollectionUtils.isNotEmpty(fs)) {
            fs.forEach(l -> assembleTree(l, map, setChildListFn, idFn, listen, idx + 1));
        }
        if (listen != null) {
            listen.accept(idx, current);
        }
    }

    /**
     * 树的简易递归,listen会回调当前对象
     *
     * @param source      源数据
     * @param childListFn get方法
     * @param listen      回调函数
     * @author seal 876651109@qq.com
     * @date 2021/6/5 16:47
     */
    public static <F> void loopTree(List<F> source, Function<F, List<F>> childListFn, Consumer<F> listen) {
        loopTree(source, childListFn, listen, null);
    }

    public static <F> void loopTree(List<F> source, Function<F, List<F>> childListFn, Consumer<F> preListen, Consumer<F> postFun) {
        if (CollectionUtils.isEmpty(source)) {
            return;
        }
        source.forEach(l -> {
            Optional.ofNullable(preListen).ifPresent(s -> s.accept(l));
            loopTree(childListFn.apply(l), childListFn, preListen, postFun);
            Optional.ofNullable(postFun).ifPresent(s -> s.accept(l));
        });
    }

    /**
     * 与listToTree功能一致，但是前方法循环两次，该方法利用递归只循环一次，用空间换时间的方式
     *
     * @return
     */
    public static <T, K> List<T> buildTree(List<T> dataList, int index, Map<K, T> dataMap,
                                           Function<T, K> idFn,
                                           Function<T, K> pIdFn,
                                           Function<T, List<T>> getChildFn,
                                           BiConsumer<T, List<T>> setChildFn,
                                           Predicate<K> predicate) {
        List<T> resultList = new ArrayList<>(dataList.size());
        while (index < dataList.size()) {
            T item = dataList.get(index);
            dataMap.put(idFn.apply(item), item);
            K pId = pIdFn.apply(item);
            if (predicate.test(pId)) {
                resultList.add(item);
            } else {
                T parent = dataMap.get(pId);
                //为null表示还没被循环到父级,则递归，再次循环剩下的部分
                if (Objects.isNull(parent)) {
                    index += 1;
                    List<T> list = buildTree(dataList, index, dataMap, idFn, pIdFn, getChildFn, setChildFn, predicate);
                    parent = dataMap.get(pId);
                    //  如果为null 表示列表没有父级，自动升级为顶节点
                    if (Objects.isNull(parent)) {
                        resultList.add(item);
                    } else {
                        List<T> childList = Optional.ofNullable(getChildFn.apply(parent)).orElse(new ArrayList<>());
                        childList.add(item);
                        setChildFn.accept(parent, childList);
                    }
                    if (list.size() != 0) {
                        resultList.addAll(list);
                    }
                } else {
                    List<T> childList = Optional.ofNullable(getChildFn.apply(parent)).orElse(new ArrayList<>());
                    childList.add(item);
                    setChildFn.accept(parent, childList);
                }
            }
            index += 1;
        }
        return resultList;
    }
}
