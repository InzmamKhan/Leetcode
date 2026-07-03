import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            inDegree[e[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] next : adj[u]) {
                inDegree[next[0]]--;
                if (inDegree[next[0]] == 0) {
                    q.add(next[0]);
                }
            }
        }

        TreeSet<Integer> costSet = new TreeSet<>();
        for (int[] e : edges) {
            costSet.add(e[2]);
        }
        List<Integer> distinctCosts = new ArrayList<>(costSet);

        int low = 0, high = distinctCosts.size() - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int targetCost = distinctCosts.get(mid);
            if (isValid(targetCost, n, topo, adj, online, k)) {
                ans = targetCost;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean isValid(int minCost, int n, List<Integer> topo, List<int[]>[] adj, boolean[] online, long k) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) continue;
            for (int[] next : adj[u]) {
                int v = next[0];
                int cost = next[1];
                if (cost < minCost) continue;
                if (v != n - 1 && !online[v]) continue;
                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}