class Solution {
    private static class Node {
        long cnt;
        long sum;
        Node(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private char[] digits;
    private Node[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n < 0) return 0;
        digits = Long.toString(n).toCharArray();
        int m = digits.length;
        memo = new Node[m][3][11][11];
        return dfs(0, 0, 10, 10, true).sum;
    }

    private Node dfs(int pos, int k, int a, int b, boolean tight) {
        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight && memo[pos][k][a][b] != null) {
            return memo[pos][k][a][b];
        }

        int limit = tight ? digits[pos] - '0' : 9;
        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nt = tight && d == limit;

            int nk = k;
            int na = a;
            int nb = b;
            int add = 0;

            if (k == 0) {
                if (d != 0) {
                    nk = 1;
                    nb = d;
                }
            } else if (k == 1) {
                nk = 2;
                na = b;
                nb = d;
            } else {
                if ((b > a && b > d) || (b < a && b < d)) {
                    add = 1;
                }
                na = b;
                nb = d;
            }

            Node child = dfs(pos + 1, nk, na, nb, nt);
            totalCnt += child.cnt;
            totalSum += child.sum + (long) add * child.cnt;
        }

        Node res = new Node(totalCnt, totalSum);
        if (!tight) {
            memo[pos][k][a][b] = res;
        }
        return res;
    }
}