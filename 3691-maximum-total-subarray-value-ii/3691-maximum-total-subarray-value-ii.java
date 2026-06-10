import java.util.PriorityQueue;

class Solution {
    int[] maxTree;
    int[] minTree;
    int n;

    public long maxTotalValue(int[] nums, int k) {
        this.n = nums.length;
        maxTree = new int[4 * n];
        minTree = new int[4 * n];
        
        buildTree(nums, 0, 0, n - 1);
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            long val = queryMax(0, 0, n - 1, l, n - 1) - queryMin(0, 0, n - 1, l, n - 1);
            pq.offer(new long[]{val, l, n - 1});
        }
        
        long totalValue = 0;
        
        for (int i = 0; i < k; i++) {
            long[] current = pq.poll();
            totalValue += current[0];
            
            int l = (int) current[1];
            int r = (int) current[2];
            
            if (r > l) {
                long nextVal = queryMax(0, 0, n - 1, l, r - 1) - queryMin(0, 0, n - 1, l, r - 1);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }
        
        return totalValue;
    }

    private void buildTree(int[] nums, int node, int start, int end) {
        if (start == end) {
            maxTree[node] = nums[start];
            minTree[node] = nums[start];
            return;
        }
        int mid = start + (end - start) / 2;
        buildTree(nums, 2 * node + 1, start, mid);
        buildTree(nums, 2 * node + 2, mid + 1, end);
        maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
        minTree[node] = Math.min(minTree[2 * node + 1], minTree[2 * node + 2]);
    }

    private int queryMax(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MIN_VALUE;
        if (l <= start && end <= r) return maxTree[node];
        int mid = start + (end - start) / 2;
        return Math.max(queryMax(2 * node + 1, start, mid, l, r),
                        queryMax(2 * node + 2, mid + 1, end, l, r));
    }

    private int queryMin(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MAX_VALUE;
        if (l <= start && end <= r) return minTree[node];
        int mid = start + (end - start) / 2;
        return Math.min(queryMin(2 * node + 1, start, mid, l, r),
                        queryMin(2 * node + 2, mid + 1, end, l, r));
    }
}