package com.example.demo.util;

import com.example.demo.bean.TestTreeObj;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
        loopTreeSimple(source, childListFn, (l) -> {
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
     * @param childListFn      设置递归的方法
     * @param idFn             获取id的方法
     * @param pidFn            获取父id的方法
     * @param getRootCondition 获取根节点的提哦啊见
     * @return {@link List<F>}
     * @author 876651109@qq.com
     * @date 2021/3/1 8:18 下午
     */
    public static <F, T> List<F> listToTree(List<F> source, BiConsumer<F, List<F>> childListFn, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition) {
        return listToTree(source, childListFn, idFn, pidFn, getRootCondition, null);
    }

    /**
     * 复杂形式，进行listen回调,用流式写法可以与外界交互
     * (idx,obj)->{System.out.println(123);},idx是从0开始的层级
     *
     * @param listen 回调函数
     * @author seal 876651109@qq.com
     * @date 2021/6/3 7:46 下午
     */
    public static <F, T> List<F> listToTree(List<F> source, BiConsumer<F, List<F>> childListFn, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition, BiConsumer<Integer, F> listen) {
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
        tree.forEach(l -> assembleTree(l, map, childListFn, idFn, listen, 0));
        return tree;
    }

    /**
     * 组装树
     */
    private static <F, T> void assembleTree(F current, Map<T, List<F>> map, BiConsumer<F, List<F>> childListFn, Function<F, T> idFn, BiConsumer<Integer, F> listen, int idx) {
        if (listen != null) {
            listen.accept(idx, current);
        }
        List<F> fs = map.get(idFn.apply(current));
        if (CollectionUtils.isEmpty(fs)) {
            return;
        }
        childListFn.accept(current, fs);
        fs.forEach(l -> assembleTree(l, map, childListFn, idFn, listen, idx + 1));
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
    public static <F> void loopTreeSimple(List<F> source, Function<F, List<F>> childListFn, Consumer<F> listen) {
        if (CollectionUtils.isEmpty(source)) {
            return;
        }
        source.forEach(l -> {
            listen.accept(l);
            loopTreeSimple(childListFn.apply(l), childListFn, listen);
        });
    }

    /**
     * 树的复杂递归,listen会回调自身及往上的所有父级
     *
     * @param source      源数据
     * @param childListFn get方法
     * @param listen      回调函数
     * @author seal 876651109@qq.com
     * @date 2021/6/5 16:47
     */
    public static <F> void loopTree(List<F> source, Function<F, List<F>> childListFn, Consumer<List<F>> listen) {
        loopTree(source, childListFn, listen, new ArrayList<>());
    }

    private static <F> void loopTree(List<F> source, Function<F, List<F>> childListFn, Consumer<List<F>> listen, List<F> loopList) {
        if (CollectionUtils.isEmpty(source)) {
            return;
        }
        source.forEach(l -> {
            List<F> fs = new ArrayList<>(loopList);
            fs.add(l);
            listen.accept(fs);
            loopTree(childListFn.apply(l), childListFn, listen, fs);
        });
    }
}
