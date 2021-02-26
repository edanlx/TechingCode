package com.example.demo.util;

import com.example.demo.bean.TestTreeObj;
import com.example.demo.util.reflect.IFn;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

    public static <F> void treeToListDeep(List<F> source, List<F> target, IFn<F, List<F>> childListFn, Predicate<F> addTargetCondition) {
        if (CollectionUtils.isEmpty(source)) {
            return;
        }
        for (F f : source) {
            if (addTargetCondition.test(f)) {
                target.add(f);
            }
            treeToListDeep(childListFn.apply(f), target, childListFn, addTargetCondition);
        }
    }

    public static <F, T> List<F> listToTree(List<F> source, BiConsumer<F, List<F>> childListFn, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition) {
        List<F> tree = new ArrayList<>();
        Map<T, List<F>> map = new HashMap<>();
        for (F f : source) {
            if (getRootCondition.test(f)) {
                tree.add(f);
            } else {
                List<F> tempList = map.getOrDefault(pidFn.apply(f), new ArrayList<>());
                tempList.add(f);
                map.put(pidFn.apply(f), tempList);
            }
        }
        tree.forEach(l -> assembleTree(l, map, childListFn, idFn));
        return tree;
    }

    private static <F, T> void assembleTree(F current, Map<T, List<F>> map, BiConsumer<F, List<F>> childListFn, Function<F, T> idFn) {
        List<F> fs = map.get(idFn.apply(current));
        if (CollectionUtils.isEmpty(fs)) {
            return;
        }
        childListFn.accept(current, fs);
        fs.forEach(l -> assembleTree(l, map, childListFn, idFn));
    }
}
