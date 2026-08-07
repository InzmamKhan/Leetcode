import java.util.Arrays;

class Solution {
    private static final int[][] MIN_DIGITS = new int[60][40];

    static {
        for (int i = 0; i < 60; i++) {
            Arrays.fill(MIN_DIGITS[i], 1000);
        }
        MIN_DIGITS[0][0] = 0;
        for (int c2 = 0; c2 < 60; c2++) {
            for (int c3 = 0; c3 < 40; c3++) {
                if (c2 == 0 && c3 == 0) continue;
                int res = 1000;
                res = Math.min(res, 1 + getMin(c2 - 1, c3));
                res = Math.min(res, 1 + getMin(c2, c3 - 1));
                res = Math.min(res, 1 + getMin(c2 - 2, c3));
                res = Math.min(res, 1 + getMin(c2 - 1, c3 - 1));
                res = Math.min(res, 1 + getMin(c2 - 3, c3));
                res = Math.min(res, 1 + getMin(c2, c3 - 2));
                MIN_DIGITS[c2][c3] = res;
            }
        }
    }

    private static int getMin(int c2, int c3) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        return MIN_DIGITS[c2][c3];
    }

    public String smallestNumber(String num, long t) {
        long temp = t;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (temp % p == 0) temp /= p;
        }
        if (temp > 1) return "-1";

        int n = num.length();
        int[] cntT = getFactors(t);

        int zeroIdx = num.indexOf('0');
        if (zeroIdx != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(num.substring(0, zeroIdx));
            sb.append((char) (num.charAt(zeroIdx) + 1));
            while (sb.length() < n) {
                sb.append('1');
            }
            num = sb.toString();
        }

        int[][] prefCnt = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            int[] f = getFactors(d);
            for (int j = 0; j < 4; j++) {
                prefCnt[i + 1][j] = prefCnt[i][j] + f[j];
            }
        }

        if (isSufficient(prefCnt[n], cntT)) {
            return num;
        }

        for (int i = n - 1; i >= 0; i--) {
            int curDigit = num.charAt(i) - '0';
            for (int d = curDigit + 1; d <= 9; d++) {
                int[] need = new int[4];
                int[] fD = getFactors(d);
                for (int j = 0; j < 4; j++) {
                    need[j] = Math.max(0, cntT[j] - prefCnt[i][j] - fD[j]);
                }

                int remLen = n - 1 - i;
                if (canForm(need, remLen)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + d));
                    sb.append(fillSuffix(need, remLen));
                    return sb.toString();
                }
            }
        }

        for (int totalLen = n + 1; ; totalLen++) {
            int remLen = totalLen;
            int[] need = cntT.clone();
            if (canForm(need, remLen)) {
                return fillSuffix(need, remLen);
            }
        }
    }

    private int[] getFactors(long x) {
        int[] f = new int[4];
        if (x <= 0) return f;
        while (x % 2 == 0) { f[0]++; x /= 2; }
        while (x % 3 == 0) { f[1]++; x /= 3; }
        while (x % 5 == 0) { f[2]++; x /= 5; }
        while (x % 7 == 0) { f[3]++; x /= 7; }
        return f;
    }

    private boolean isSufficient(int[] cur, int[] target) {
        for (int j = 0; j < 4; j++) {
            if (cur[j] < target[j]) return false;
        }
        return true;
    }

    private boolean canForm(int[] need, int len) {
        int minDigits = need[2] + need[3] + getMin(need[0], need[1]);
        return minDigits <= len;
    }

    private String fillSuffix(int[] need, int len) {
        StringBuilder sb = new StringBuilder();
        int c2 = need[0], c3 = need[1], c5 = need[2], c7 = need[3];

        for (int pos = 0; pos < len; pos++) {
            int remLen = len - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int[] f = getFactors(d);
                int nc2 = Math.max(0, c2 - f[0]);
                int nc3 = Math.max(0, c3 - f[1]);
                int nc5 = Math.max(0, c5 - f[2]);
                int nc7 = Math.max(0, c7 - f[3]);

                if (nc5 + nc7 + getMin(nc2, nc3) <= remLen) {
                    sb.append((char) ('0' + d));
                    c2 = nc2;
                    c3 = nc3;
                    c5 = nc5;
                    c7 = nc7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}