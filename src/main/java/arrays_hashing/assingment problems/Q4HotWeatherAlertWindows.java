public class Q4HotWeatherAlertWindows {
    static int countAlerts(int[] readings, int k, int threshold) {
        int windowSum = 0;
        int count = 0;

        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        if (windowSum >= k * threshold) count++;

        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i];
            windowSum -= readings[i - k];

            if (windowSum >= k * threshold) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countAlerts(readings, 3, 4));
    }
}
