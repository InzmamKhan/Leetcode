import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] distToThief = new int[n][n];
        for (int[] row : distToThief) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    distToThief[r][c] = 0;
                }
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && distToThief[nr][nc] == Integer.MAX_VALUE) {
                    distToThief[nr][nc] = distToThief[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[][] maxSafeness = new int[n][n];
        for (int[] row : maxSafeness) {
            Arrays.fill(row, -1);
        }

        maxHeap.offer(new int[]{distToThief[0][0], 0, 0});
        maxSafeness[0][0] = distToThief[0][0];

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int safe = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            if (safe < maxSafeness[r][c]) {
                continue;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    int nextSafe = Math.min(safe, distToThief[nr][nc]);
                    if (nextSafe > maxSafeness[nr][nc]) {
                        maxSafeness[nr][nc] = nextSafe;
                        maxHeap.offer(new int[]{nextSafe, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}