public class Q5ContestLeaderboard {
    static class Entry {
        String name;
        int marks;
        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    static Entry[] leaderboard(Entry[] entries) {
        int[] count = new int[101];
        for (Entry entry : entries) count[100 - entry.marks]++;
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];

        Entry[] result = new Entry[entries.length];
        for (int i = entries.length - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            result[--count[key]] = entries[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i].name + " " + result[i].marks);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
}
