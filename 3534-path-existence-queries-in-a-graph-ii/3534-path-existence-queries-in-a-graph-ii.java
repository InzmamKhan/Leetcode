import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] sortedUnique = Arrays.stream(nums).distinct().sorted().toArray();
        int m = sortedUnique.length;
        
        int LOG = 20;
        int[][] up = new int[m][LOG];
        
        for (int i = 0; i < m; i++) {
            int target = sortedUnique[i] + maxDiff;
            int idx = Arrays.binarySearch(sortedUnique, target);
            if (idx < 0) {
                idx = -idx - 2;
            }
            up[i][0] = idx;
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < m; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        
        int [] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            int valU = nums[u];
            int valV = nums[v];
            
            if (Math.abs(valU - valV) <= maxDiff) {
                ans[q] = 1;
                continue;
            }
            
            int idxA = Arrays.binarySearch(sortedUnique, Math.min(valU, valV));
            int idxB = Arrays.binarySearch(sortedUnique, Math.max(valU, valV));
            
            int steps = 0;
            int curr = idxA;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < idxB) {
                    curr = up[curr][j];
                    steps += (1 << j);
                }
            }
            
            curr = up[curr][0];
            steps++;
            
            if (curr >= idxB) {
                ans[q] = steps;
            } else {
                ans[q] = -1;
            }
        }
        
        return ans;
    }
}