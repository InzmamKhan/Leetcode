import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int greedCounter = 0;
        int sizeCounter = 0;

        while (greedCounter < g.length && sizeCounter < s.length) {
            if (g[greedCounter] <= s[sizeCounter]) {
                greedCounter++; 
            }
            sizeCounter++; 
        }
        return greedCounter; 
    }
}