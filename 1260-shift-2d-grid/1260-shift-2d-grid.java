import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        k = k % total;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>());
        }
        
        for (int i = 0; i < total; i++) {
            int oldIndex = (i - k + total) % total;
            int r = oldIndex / n;
            int c = oldIndex % n;
            
            int targetRow = i / n;
            result.get(targetRow).add(grid[r][c]);
        }
        
        return result;
    }
}