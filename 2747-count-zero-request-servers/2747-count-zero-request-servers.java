import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] q = new int[m][2];
        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        int[] count = new int[n + 1];
        int activeServers = 0;
        int left = 0, right = 0;
        int numLogs = logs.length;

        for (int i = 0; i < m; i++) {
            int time = q[i][0];
            int idx = q[i][1];
            int startTime = time - x;

            while (right < numLogs && logs[right][1] <= time) {
                int serverId = logs[right][0];
                if (count[serverId] == 0) {
                    activeServers++;
                }
                count[serverId]++;
                right++;
            }

            while (left < numLogs && logs[left][1] < startTime) {
                int serverId = logs[left][0];
                count[serverId]--;
                if (count[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            ans[idx] = n - activeServers;
        }

        return ans;
    }
}