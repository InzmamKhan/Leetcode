import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        
        int startCost = grid.get(0).get(0);
        dist[0][0] = startCost;
        pq.offer(new int[]{0, 0, startCost});
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];

            if (r == m - 1 && c == n - 1) {
                return health - cost >= 1;
            }
            
            if (cost > dist[r][c]) continue;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dRow[i];
                int nc = c + dCol[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextCost = cost + grid.get(nr).get(nc);
                    
                    if (nextCost < dist[nr][nc]) {
                        dist[nr][nc] = nextCost;
                        pq.offer(new int[]{nr, nc, nextCost});
                    }
                }
            }
        }
        
        return health - dist[m - 1][n - 1] >= 1;
    }
}