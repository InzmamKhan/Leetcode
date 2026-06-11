import java.util.*;

class Solution {
    private int maxDepth = 0;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        maxDepth = 0;
        findMaxDepth(1, 0, 0, adj);

        long MOD = 1000000007;
        long ans = 1;
        long base = 2;
        int exp = maxDepth - 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }

        return (int) ans;
    }

    private void findMaxDepth(int node, int parent, int depth, List<List<Integer>> adj) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                findMaxDepth(neighbor, node, depth + 1, adj);
            }
        }
    }
}