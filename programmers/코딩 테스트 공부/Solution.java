class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int leastAlp = 0;
        int leastCop = 0; 
        for (int[] problem : problems) {
            leastAlp = Math.max(leastAlp, problem[0]);
            leastCop = Math.max(leastCop, problem[1]);
        }
        alp = Math.min(leastAlp, alp);
        cop = Math.min(leastCop, cop);

        int[][] dp = new int[leastAlp + 2][leastCop + 2];
        for (int startA = 0; startA < leastAlp + 2; startA++) {
            for (int startC = 0; startC < leastCop + 2; startC++) {
                if (startA > alp || startC > cop) {
                    dp[startA][startC] = Integer.MAX_VALUE;
                }
            }
        }
        for (int currAlp = alp; currAlp <= leastAlp; currAlp++) {
            for (int currCop = cop; currCop <= leastCop; currCop++) {
                dp[currAlp + 1][currCop] = Math.min(dp[currAlp + 1][currCop], dp[currAlp][currCop] + 1);
                dp[currAlp][currCop + 1] = Math.min(dp[currAlp][currCop + 1], dp[currAlp][currCop] + 1);

                for (int[] problem : problems) {
                    if (problem[0] > currAlp || problem[1] > currCop) {
                        continue;
                    }
                    int nextAlp = (currAlp + problem[2] > leastAlp)? leastAlp : currAlp + problem[2];
                    int nextCop = (currCop + problem[3] > leastCop)? leastCop : currCop + problem[3];
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[currAlp][currCop] + problem[4]);
                }
            }
        }

        
        return dp[leastAlp][leastCop];
    }
}