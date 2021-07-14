package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Code1418 {
    // 表中的数据行应该按餐桌桌号升序排列。
    public static void main(String[] args) {
        List<List<String>> arrayLists = new ArrayList<>();
        arrayLists.add(Arrays.asList("David", "3", "Ceviche"));
        arrayLists.add(Arrays.asList("Corina","10","Beef Burrito"));
        arrayLists.add(Arrays.asList("David","3","Fried Chicken"));
        arrayLists.add(Arrays.asList("Carla","5","Water"));
        arrayLists.add(Arrays.asList("Carla","5","Ceviche"));
        arrayLists.add(Arrays.asList("Rous","3","Ceviche"));
        System.out.println(new Code1418().displayTable(arrayLists));
    }
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> result = null;
        Set<String> tableNumberSet = new HashSet<>();
        List<Integer> tableNumberList = new ArrayList<>();
        Set<String> foodItemSet = new HashSet<>();
        List<String> foodItemList = new ArrayList<>();
        Map<String, Map<String, Integer>> foodItemMap = new HashMap<>();
        for (List<String> order : orders) {
            String tableNumber = order.get(1);
            Map<String, Integer> foodItemCount = foodItemMap.getOrDefault(tableNumber, new HashMap<>());
            if (tableNumberSet.add(tableNumber)) {
                tableNumberList.add(Integer.valueOf(tableNumber));
            }
            String foodItem = order.get(2);
            if (foodItemSet.add(foodItem)) {
                foodItemList.add(foodItem);
            }
            Integer orDefault = foodItemCount.getOrDefault(foodItem, 0);
            foodItemCount.put(foodItem, orDefault + 1);
            foodItemMap.put(tableNumber, foodItemCount);
        }
        result = new ArrayList<>(tableNumberList.size() + 1);
        Collections.sort(tableNumberList);
        Collections.sort(foodItemList);
        foodItemList.add(0, "Table");
        result.add(foodItemList);
        for (Integer tableNumber : tableNumberList) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(tableNumber));
            for (int i = 1; i < foodItemList.size(); i++) {
                Integer orDefault = foodItemMap.get(String.valueOf(tableNumber)).getOrDefault(foodItemList.get(i), 0);
                list.add(String.valueOf(orDefault));
            }
            result.add(list);
        }
        return result;
    }
}
