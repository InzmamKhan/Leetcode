class Solution {
    public int largestAltitude(int[] gain) {
        int currentAlt = 0;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<gain.length; i++){
            currentAlt += gain[i];
            if(max < currentAlt){
                max = currentAlt;
            }
        }
        if(max < 0){
            return 0;
        }
        return max;
    }
}