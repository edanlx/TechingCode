package exam;

public class Coed504 {
    public static void main(String[] args) {
        System.out.println(new Coed504().convertToBase7(-7));
    }

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = num * -1;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num = num / 7;
        }
        if (flag) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
