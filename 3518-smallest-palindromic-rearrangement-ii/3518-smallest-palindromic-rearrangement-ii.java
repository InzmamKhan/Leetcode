import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int m = n / 2;
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int[] count = new int[26];
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            count[i] = freq[i] / 2;
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        BigInteger totalWays = factorial(m);
        for (int c : count) {
            if (c > 0) {
                totalWays = totalWays.divide(factorial(c));
            }
        }

        BigInteger K = BigInteger.valueOf(k);
        if (totalWays.compareTo(K) < 0) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        int rem = m;
        BigInteger currentWays = totalWays;

        for (int i = 0; i < m; i++) {
            for (int c = 0; c < 26; c++) {
                if (count[c] == 0) continue;

                BigInteger waysWithC = currentWays
                        .multiply(BigInteger.valueOf(count[c]))
                        .divide(BigInteger.valueOf(rem));

                if (K.compareTo(waysWithC) <= 0) {
                    leftHalf.append((char) ('a' + c));
                    count[c]--;
                    rem--;
                    currentWays = waysWithC;
                    break;
                } else {
                    K = K.subtract(waysWithC);
                }
            }
        }

        StringBuilder result = new StringBuilder(leftHalf);
        if (n % 2 != 0) {
            result.append(midChar);
        }
        for (int i = leftHalf.length() - 1; i >= 0; i--) {
            result.append(leftHalf.charAt(i));
        }

        return result.toString();
    }

    private BigInteger factorial(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}