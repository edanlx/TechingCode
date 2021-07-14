package exam;

public class Code275 {
    public static void main(String[] args) {
        System.out.println(new Code275().hIndex2(new int[]{0, 1, 3, 5, 6}));
    }

    int[] data;

    public int hIndex(int[] citations) {
        data = citations;
        return handle(0, citations.length - 1);
    }

    public int handle(int left, int right) {
        if (left == right) {
            return left;
        }
        int middle = (left + right) / 2;
        if (data[middle] >= data.length - middle) {
            right = middle - 1;
        } else {
            left = middle + 1;
        }
        return handle(left, right);
    }


    public int hIndex2(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}
