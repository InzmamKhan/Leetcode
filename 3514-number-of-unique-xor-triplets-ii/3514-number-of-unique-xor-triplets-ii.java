class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        int uniqueCount = 0;
        for (int x : nums) {
            if (!present[x]) {
                present[x] = true;
                uniqueCount++;
            }
        }

        int[] u = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (present[i]) {
                u[idx++] = i;
            }
        }

        boolean[] has2 = new boolean[2048];
        for (int i = 0; i < uniqueCount; i++) {
            for (int j = i; j < uniqueCount; j++) {
                has2[u[i] ^ u[j]] = true;
            }
        }

        boolean[] has3 = new boolean[2048];
        for (int x = 0; x < 2048; x++) {
            if (has2[x]) {
                for (int c : u) {
                    has3[x ^ c] = true;
                }
            }
        }

        int count = 0;
        for (boolean b : has3) {
            if (b) {
                count++;
            }
        }

        return count;
    }
}