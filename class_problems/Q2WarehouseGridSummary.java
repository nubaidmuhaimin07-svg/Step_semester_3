public class Q2WarehouseGridSummary {
    static void warehouseSummary(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            System.out.println("(0, no coordinate)");
            return;
        }
        int total = 0, max = grid[0][0], maxRow = 0, maxCol = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                total += grid[r][c];
                if (grid[r][c] > max) {
                    max = grid[r][c];
                    maxRow = r;
                    maxCol = c;
                }
            }
        }
        System.out.println("(" + total + ", (" + maxRow + ", " + maxCol + "))");
    }
    public static void main(String[] args) {
        int[][] grid = {{4,9,2},{7,1,6},{3,12,5}};
        warehouseSummary(grid);
    }
}
