import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        int iceCreams = 0;
        for(int i=0; i<costs.length; i++){
            if(costs[i] <= coins){
                coins -= costs[i];
                iceCreams++;
            }
            else{
                break;
            }
        }
        return iceCreams;
    }
}