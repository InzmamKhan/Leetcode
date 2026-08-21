class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long target, int[] coins) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n;

        for (int i = 1; i < totalSubsets; i++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) {
                continue;
            }

            if (bitCount % 2 == 1) {
                count += target / currentLcm;
            } else {
                count -= target / currentLcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}