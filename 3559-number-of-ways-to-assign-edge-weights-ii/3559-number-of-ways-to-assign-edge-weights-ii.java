import java.util.*;

public class Solution {
    private int[] depth;
    private int[][] up;
    private List<List<Integer>> adj;
    private static final int MOD = 1000000007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        depth = new int[n + 1];
        int LOG = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[n + 1][LOG];

        dfs(1, 1, 0);

        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = getLCA(u, v, LOG);
            int pathLength = depth[u] + depth[v] - 2 * depth[lca];

            if (pathLength == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow2[pathLength - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        up[node][0] = parent;
        for (int i = 1; i < up[node].length; i++) {
            up[node][i] = up[up[node][i - 1]][i - 1];
        }
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, d + 1);
            }
        }
    }

    private int getLCA(int u, int v, int LOG) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
}