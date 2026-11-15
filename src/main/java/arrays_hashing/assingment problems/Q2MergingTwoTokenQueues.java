import java.util.Arrays;

public class Q2MergingTwoTokenQueues {
    static int[] mergeTokens(int[] counterA, int[] counterB) {
        int[] result = new int[counterA.length + counterB.length];
        int i = 0, j = 0, k = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result[k++] = counterA[i++];
            } else {
                result[k++] = counterB[j++];
            }
        }

        while (i < counterA.length) result[k++] = counterA[i++];
        while (j < counterB.length) result[k++] = counterB[j++];

        return result;
    }

    public static void main(String[] args) {
        int[] a = {3, 8, 15, 20};
        int[] b = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(a, b)));

        int[] empty = {};
        int[] other = {4, 9};
        System.out.println(Arrays.toString(mergeTokens(empty, other)));
    }
}
