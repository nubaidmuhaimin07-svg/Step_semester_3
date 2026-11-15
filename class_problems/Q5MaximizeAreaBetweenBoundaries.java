public class Q5MaximizeAreaBetweenBoundaries {
    static int maxContainerArea(int[] h) {
        int left = 0, right = h.length - 1, best = 0;
        while (left < right) {
            int area = Math.min(h[left], h[right]) * (right - left);
            best = Math.max(best, area);
            if (h[left] < h[right]) left++;
            else right--;
        }
        return best;
    }
    static int bruteForce(int[] h) {
        int best = 0;
        for (int i=0; i<h.length; i++)
            for (int j=i+1; j<h.length; j++)
                best = Math.max(best, Math.min(h[i],h[j]) * (j-i));
        return best;
    }
    public static void main(String[] args) {
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxContainerArea(heights));
    }
}
