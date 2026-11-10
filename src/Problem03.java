public class Problem03 {
    public int minCostClimbingStairs(int[] cost) {
        int first = cost[0];
        int second = cost[1];
        
        for (int i = 2; i < cost.length; i++) {
            int current = cost[i] + Math.min(first, second);
            first = second;
            second = current;
        }
        
        return Math.min(first, second);
    }
}
