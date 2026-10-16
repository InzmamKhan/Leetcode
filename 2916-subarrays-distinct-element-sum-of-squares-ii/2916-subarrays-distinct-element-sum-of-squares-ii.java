import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;
    
    class Node {
        long sum, sum_sq, lazy;
    }
    
    private Node[] tree;
    
    private void push(int node, int l, int r) {
        if (tree[node].lazy != 0) {
            long v = tree[node].lazy;
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, v);
            apply(2 * node + 1, mid + 1, r, v);
            tree[node].lazy = 0;
        }
    }
    
    private void apply(int node, int l, int r, long v) {
        long len = (r - l + 1);
        long current_sum = tree[node].sum;
        long current_sum_sq = tree[node].sum_sq;
        
        long term1 = (2 * (v % MOD) % MOD * (current_sum % MOD)) % MOD;
        long term2 = ((v % MOD * v % MOD) % MOD * (len % MOD)) % MOD;
        
        tree[node].sum_sq = (current_sum_sq + term1 + term2) % MOD;
        tree[node].sum = (current_sum + v * (len % MOD) % MOD) % MOD;
        tree[node].lazy = (tree[node].lazy + v) % MOD;
    }
    
    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, v);
        update(2 * node + 1, mid + 1, r, ql, qr, v);
        tree[node].sum = (tree[2 * node].sum + tree[2 * node + 1].sum) % MOD;
        tree[node].sum_sq = (tree[2 * node].sum_sq + tree[2 * node + 1].sum_sq) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }
        
        Map<Integer, Integer> lastOccur = new HashMap<>();
        long totalSum = 0;
        
        for (int j = 0; j < n; j++) {
            int prev = lastOccur.getOrDefault(nums[j], -1);
            lastOccur.put(nums[j], j);
            
            update(1, 0, n - 1, prev + 1, j, 1);
            totalSum = (totalSum + tree[1].sum_sq) % MOD;
        }
        
        return (int) totalSum;
    }
}