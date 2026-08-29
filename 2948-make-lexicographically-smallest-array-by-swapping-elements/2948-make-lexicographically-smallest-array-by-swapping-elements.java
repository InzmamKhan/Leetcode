import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums[i];
            sortedNums[i][1] = i;
        }

        Arrays.sort(sortedNums, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            while (j + 1 < n && sortedNums[j + 1][0] - sortedNums[j][0] <= limit) {
                j++;
            }

            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(sortedNums[k][1]);
            }
            Collections.sort(indices);

            for (int k = i; k <= j; k++) {
                result[indices.get(k - i)] = sortedNums[k][0];
            }

            i = j + 1;
        }

        return result;
    }
}