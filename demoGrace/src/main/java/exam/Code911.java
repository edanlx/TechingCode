package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Code911 {
    public static void main(String[] args) {
        System.out.println(new Code911(new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}).q(0));
    }

    TreeMap<Integer, Integer> result = new TreeMap<>();
    Map<Integer, Integer> vote = new HashMap<>();

    public Code911(int[] persons, int[] times) {
        int maxPersons = -1;
        for (int i = 0; i < persons.length; i++) {
            int current = vote.getOrDefault(persons[i], 0) + 1;
            vote.put(persons[i], current);
            if (maxPersons == -1) {
                maxPersons = persons[i];
            } else {
                if (current >= vote.get(maxPersons)) {
                    maxPersons = persons[i];
                }
            }
            result.put(times[i], maxPersons);
        }
    }

    public int q(int t) {
        return result.floorEntry(t).getValue();
    }
}
