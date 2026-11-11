class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = (long) cost1;
        long c2 = (long) cost2;
        long cb = (long) costBoth;
        long n1 = (long) need1;
        long n2 = (long) need2;
        
        long ans = n1 * c1 + n2 * c2;
        
        long costK1 = n1 * cb + Math.max(0L, n2 - n1) * c2;
        ans = Math.min(ans, costK1);
        
        long costK2 = n2 * cb + Math.max(0L, n1 - n2) * c1;
        ans = Math.min(ans, costK2);
        
        return ans;
    }
}