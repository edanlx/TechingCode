package exam;

public class Code748 {
    public static void main(String[] args) {
        System.out.println(new Code748().shortestCompletingWord("1s3 pst", new String[]{"step","steps","stripe","stepple"}));
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        Integer minIdx = null;
        String s = licensePlate.toLowerCase();
        int[] template = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp >= 'a' && temp <= 'z') {
                template[temp - 'a'] = template[temp - 'a'] + 1;
            }
        }
        for (int i = 0; i < words.length; i++) {
            int[] tempMap = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                char tempChar = words[i].charAt(j);
                tempMap[tempChar - 'a'] = tempMap[tempChar - 'a'] + 1;
            }
            boolean doChange = true;
            for (int j = 0; j < template.length; j++) {
                if (template[j] > tempMap[j]) {
                    doChange = false;
                    break;
                }
            }
            if (doChange) {
                if (minIdx == null) {
                    minIdx = i;
                } else {
                    minIdx = words[i].length() >= words[minIdx].length() ? minIdx : i;
                }
            }
        }
        return words[minIdx];
    }
}
