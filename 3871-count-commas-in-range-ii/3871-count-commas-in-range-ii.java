class Solution {
    public long countCommas(long n) {
        long ans = 0;
        for (long start = 1000; start <= n; start *= 1000) {
            ans += n - start + 1;
        }
        return ans;
    }
}