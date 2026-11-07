import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });
        
        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i});
        }
        
        int[] ans = new int[tasks.length];
        int time = 0;
        
        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);
            
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] curr = busy.poll();
                free.offer(new int[]{curr[1], curr[2]});
            }
            
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] curr = busy.poll();
                    free.offer(new int[]{curr[1], curr[2]});
                }
            }
            
            int[] server = free.poll();
            ans[i] = server[1];
            busy.offer(new int[]{time + tasks[i], server[0], server[1]});
        }
        
        return ans;
    }
}