import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0) {
            return n - 1;
        }

        int[][] allRestrictions = new int[restrictions.length + 2][2];
        System.arraycopy(restrictions, 0, allRestrictions, 0, restrictions.length);
        allRestrictions[restrictions.length] = new int[]{1, 0};
        allRestrictions[restrictions.length + 1] = new int[]{n, n - 1};

        Arrays.sort(allRestrictions, (a, b) -> Integer.compare(a[0], b[0]));

        int m = allRestrictions.length;

        for (int i = 1; i < m; i++) {
            int dist = allRestrictions[i][0] - allRestrictions[i - 1][0];
            allRestrictions[i][1] = Math.min(allRestrictions[i][1], allRestrictions[i - 1][1] + dist);
        }

        for (int i = m - 2; i >= 0; i--) {
            int dist = allRestrictions[i + 1][0] - allRestrictions[i][0];
            allRestrictions[i][1] = Math.min(allRestrictions[i][1], allRestrictions[i + 1][1] + dist);
        }

        int maxResult = 0;
        for (int i = 0; i < m - 1; i++) {
            int b1 = allRestrictions[i][0];
            int h1 = allRestrictions[i][1];
            int b2 = allRestrictions[i + 1][0];
            int h2 = allRestrictions[i + 1][1];

            int peak = ((b2 - b1) + h1 + h2) / 2;
            maxResult = Math.max(maxResult, peak);
        }

        return maxResult;
    }
}