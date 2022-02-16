package exam;

public class Code2000 {
    public static void main(String[] args) {
        String s = new Code2000().reversePrefix("abcdefd", 'd');
        System.out.println(s);
    }

    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);
        return new StringBuilder(word.substring(0, i + 1)).reverse().append(word.substring(i + 1)).toString();
    }
}
