class Solution {
    private List<Integer>[] g;
    private int[] tin, tout, depth, bit;
    private int[][] pa;
    private int idx = 0;
    private char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        chars = s.toCharArray();
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        int log = 32 - Integer.numberOfLeadingZeros(n);
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        bit = new int[n];
        pa = new int[n][log];

        dfs(0, -1, 0);

        BinaryIndexedTree tree = new BinaryIndexedTree(2 * n + 1);
        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (chars[i] - 'a');
            tree.update(tin[i] + 1, bit[i]);
            tree.update(tout[i] + 1, bit[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int y = 1 << (c - 'a');
                if (bit[u] != y) {
                    int delta = bit[u] ^ y;
                    tree.update(tin[u] + 1, delta);
                    tree.update(tout[u] + 1, delta);
                    bit[u] = y;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);
                int mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    private void dfs(int u, int p, int d) {
        tin[u] = idx++;
        depth[u] = d;
        pa[u][0] = p;
        for (int i = 1; i < pa[0].length; i++) {
            pa[u][i] = pa[u][i - 1] == -1 ? -1 : pa[pa[u][i - 1]][i - 1];
        }
        for (int v : g[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        tout[u] = idx++;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }
        for (int i = pa[0].length - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = pa[u][i];
            }
        }
        if (u == v) return u;
        for (int i = pa[0].length - 1; i >= 0; i--) {
            if (pa[u][i] != pa[v][i]) {
                u = pa[u][i];
                v = pa[v][i];
            }
        }
        return pa[u][0];
    }

    class BinaryIndexedTree {
        int n;
        int[] tree;

        BinaryIndexedTree(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void update(int i, int delta) {
            while (i <= n) {
                tree[i] ^= delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int res = 0;
            while (i > 0) {
                res ^= tree[i];
                i -= i & -i;
            }
            return res;
        }
    }
}