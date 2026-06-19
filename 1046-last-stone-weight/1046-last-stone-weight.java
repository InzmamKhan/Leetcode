import java.util.Arrays;

class Solution {
    public int lastStoneWeight(int[] stones) {

        if(stones.length == 0){
            return 0;
        }
        if(stones.length == 1){
            return stones[0];
        }

        Arrays.sort(stones);
        int stoneOne = stones[stones.length - 1];
        int stoneTwo = stones[stones.length - 2];

        while(stoneOne != 0 && stoneTwo != 0){
            stones[stones.length - 1] = Math.abs(stoneOne - stoneTwo);
            stones[stones.length - 2] = 0;
            Arrays.sort(stones);

            stoneOne = stones[stones.length - 1];
            stoneTwo = stones[stones.length - 2];
        }

        return stones[stones.length - 1];
    }
}