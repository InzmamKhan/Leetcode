class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        int startCount = 1;
        dist[0][startCount] = 0;
        pq.offer(new int[]{0, 0, startCount});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1], count = curr[2];

            if (u == n - 1) {
                return d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (int[] edge : g[u]) {
                int v = edge[0], w = edge[1];
                char nextChar = labels.charAt(v);
                char currChar = labels.charAt(u);
                
                int nextCount = 1;
                if (nextChar == currChar) {
                    nextCount = count + 1;
                }

                if (nextCount <= k) {
                    if (d + w < dist[v][nextCount]) {
                        dist[v][nextCount] = d + w;
                        pq.offer(new int[]{d + w, v, nextCount});
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, dist[n - 1][c]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}