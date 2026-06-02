class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;
        
        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {

                int landFinish = landStartTime[i] + landDuration[i];
                int waterStart = Math.max(landFinish, waterStartTime[j]);
                int optionAFinish = waterStart + waterDuration[j];

                int waterFinish = waterStartTime[j] + waterDuration[j];
                int landStart = Math.max(waterFinish, landStartTime[i]);
                int optionBFinish = landStart + landDuration[i];

                int bestForThisPair = Math.min(optionAFinish, optionBFinish);

                minFinishTime = Math.min(minFinishTime, bestForThisPair);
            }
        }
        
        return minFinishTime;
    }
}