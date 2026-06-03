import java.util.Arrays;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int minLandFinish = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minLandFinish = Math.min(minLandFinish, landStartTime[i] + landDuration[i]);
        }
        
        int optionAFinish = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            int waterStart = Math.max(minLandFinish, waterStartTime[j]);
            optionAFinish = Math.min(optionAFinish, waterStart + waterDuration[j]);
        }

        int minWaterFinish = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            minWaterFinish = Math.min(minWaterFinish, waterStartTime[j] + waterDuration[j]);
        }

        int optionBFinish = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int landStart = Math.max(minWaterFinish, landStartTime[i]);
            optionBFinish = Math.min(optionBFinish, landStart + landDuration[i]);
        }

        return Math.min(optionAFinish, optionBFinish);
    }
}