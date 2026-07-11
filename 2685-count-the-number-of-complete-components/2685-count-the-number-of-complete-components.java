import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = bfs(i, adj, visited);
                int vertexCount = counts[0];
                int totalEdgesSum = counts[1];
                
                if (totalEdgesSum == vertexCount * (vertexCount - 1)) {
                    completeComponentsCount++;
                }
            }
        }
        
        return completeComponentsCount;
    }
    
    private int[] bfs(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        int vertexCount = 0;
        int totalEdgesSum = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            vertexCount++;
            totalEdgesSum += adj.get(curr).size(); 
            
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return new int[]{vertexCount, totalEdgesSum};
    }
}