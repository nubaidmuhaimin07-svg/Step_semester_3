import java.util.HashMap;
import java.util.Map;

public class Q3MostPopularCanteenOrder {
    static String[] mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = counts.get(bestItem);

        for (String item : orders) {
            int count = counts.get(item);
            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new String[]{bestItem, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};

        String[] first = mostPopular(orders1);
        String[] second = mostPopular(orders2);

        System.out.println("(\"" + first[0] + "\", " + first[1] + ")");
        System.out.println("(\"" + second[0] + "\", " + second[1] + ")");
    }
}
