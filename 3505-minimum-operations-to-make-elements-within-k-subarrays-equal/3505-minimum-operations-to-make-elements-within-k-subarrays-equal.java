import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                vals[m++] = sorted[i];
            }
        }
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(vals, 0, m, nums[i]) + 1;
        }

        long[] cnt = new long[m + 1];
        long[] sum = new long[m + 1];
        int top = Integer.highestOneBit(m);
        long[] cost = new long[n - x + 1];
        long total = 0;
        int need = (x + 1) / 2;

        for (int i = 0; i < n; i++) {
            for (int p = rank[i]; p <= m; p += p & -p) {
                cnt[p]++;
                sum[p] += nums[i];
            }
            total += nums[i];
            if (i >= x) {
                int old = nums[i - x];
                for (int p = rank[i - x]; p <= m; p += p & -p) {
                    cnt[p]--;
                    sum[p] -= old;
                }
                total -= old;
            }
            if (i >= x - 1) {
                int pos = 0;
                long rem = need;
                for (int pw = top; pw > 0; pw >>= 1) {
                    if (pos + pw <= m && cnt[pos + pw] < rem) {
                        pos += pw;
                        rem -= cnt[pos];
                    }
                }
                int idx = pos + 1;
                long c = 0, s = 0;
                for (int p = idx; p > 0; p -= p & -p) {
                    c += cnt[p];
                    s += sum[p];
                }
                long med = vals[idx - 1];
                cost[i - x + 1] = med * c - s + (total - s) - med * (x - c);
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = x; i <= n; i++) {
                long best = cur[i - 1];
                if (prev[i - x] < INF) {
                    best = Math.min(best, prev[i - x] + cost[i - x]);
                }
                cur[i] = best;
            }
            prev = cur;
        }
        return prev[n];
    }
}