### DAA Assignment4 - Report and Defense (50 points)

##### Problem 1 - Counting Bits **(5 points)**.
...

##### Problem 2 - Jump Game **(5 points)**.
...

##### Problem 3 - Min Cost Climbing Stairs **(5 points)**.
...

##### Problem 4 - Longest Increasing Subsequence **(5 points)**.
...

##### Problem 5 - Longest Common Subsequence **(5 points)**.
...

##### Problem 6 - Edit Distance **(5 points)**.
...

##### Problem 7 - Longest Palindromic Subsequence **(5 points)**.
...

##### Problem 8 - Palindromic Substrings **(5 points)**.
...

##### Problem 9 - Maximum Subarray **(5 points)**.
...

##### Problem 10 - House Robber **(5 points)**.
...



DAA Assignment 4 — Dynamic Programming & Greedy Algorithms (50 Points)Course: Design and Analysis of Algorithms (DAA)Assignment: Assignment 4 — Report and DefenseLanguage: Java 17📌 Executive SummaryThis report presents formal solutions, detailed dynamic programming/greedy state formulations, complexity analysis, and defense preparation for 10 core algorithmic problems.📊 Summary Table#Problem NameTechniqueTime ComplexitySpace Complexity01Counting BitsBit Manipulation + DP$O(n)$$O(n)$02Jump Game IIGreedy Choice$O(n)$$O(1)$03Min Cost Climbing Stairs1D Dynamic Programming$O(n)$$O(1)$04Longest Increasing SubsequencePatience Sorting + Binary Search$O(n \log n)$$O(n)$05Longest Common Subsequence2D Dynamic Programming$O(m \times n)$$O(m \times n)$06Edit Distance2D Dynamic Programming (Wagner-Fischer)$O(m \times n)$$O(m \times n)$07Longest Palindromic SubsequenceInterval Dynamic Programming$O(n^2)$$O(n^2)$08Palindromic SubstringsTwo-Pointer (Expand Around Center)$O(n^2)$$O(1)$09Maximum SubarrayKadane's Algorithm$O(n)$$O(1)$10House RobberState-Reduced Dynamic Programming$O(n)$$O(1)$📝 Detailed Problem AnalysisProblem 1 - Counting Bits (5 points)Problem Statement: Given an integer $n$, return an array ans of length $n + 1$ such that ans[i] is the number of $1$'s in the binary representation of $i$.Algorithm & Formulation:State Definition: $dp[i]$ represents the number of set bits in integer $i$.Transition: Right-shifting an integer $i$ by $1$ (i >> 1) removes its least significant bit. The number of set bits in $i$ is equal to $dp[i \gg 1]$ plus $1$ if $i$ is odd (i & 1).Recurrence: $dp[i] = dp[i \gg 1] + (i \ \& \ 1)$Complexities:Time Complexity: $O(n)$ — Single pass loop with $O(1)$ bitwise operations per step.Space Complexity: $O(n)$ auxiliary array for result.public class Problem01 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
Problem 2 - Jump Game II (5 points)Problem Statement: Given a 0-indexed array nums, return the minimum number of jumps required to reach nums[n - 1].Algorithm & Formulation:Strategy: Implicit BFS / Greedy choice.Mechanism: Maintain farthest (maximum reach from any index in current window) and currentEnd (boundary of current jump level). When loop index reaches currentEnd, increment jump count and update currentEnd = farthest.Complexities:Time Complexity: $O(n)$ — Single linear sweep stopping at $n-2$.Space Complexity: $O(1)$ — Constant auxiliary memory.public class Problem02 {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        
        return jumps;
    }
}
Problem 3 - Min Cost Climbing Stairs (5 points)Problem Statement: Determine the minimum total cost to reach past the last step starting from index 0 or index 1.Algorithm & Formulation:State Definition: $dp[i]$ is the minimum total cost to reach step $i$.Recurrence: $dp[i] = cost[i] + \min(dp[i-1], dp[i-2])$Space Optimization: We only need the previous two computed values (first and second).Complexities:Time Complexity: $O(n)$ — Single traversal of input array.Space Complexity: $O(1)$ — Optimized down from $O(n)$.public class Problem03 {
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
Problem 4 - Longest Increasing Subsequence (5 points)Problem Statement: Find the length of the longest strictly increasing subsequence in an array nums.Algorithm & Formulation:Strategy: Patience Sorting + Binary Search.State Definition: $tails[k]$ stores the smallest ending value of all increasing subsequences of length $k + 1$.Transition: For each element $x \in nums$, binary search for its insertion position in $tails[0...size-1]$. Replace or extend tails.Complexities:Time Complexity: $O(n \log n)$ — Binary search $O(\log n)$ for each element.Space Complexity: $O(n)$ — Storage for tails array.public class Problem04 {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int x : nums) {
            int i = 0, j = size;
            while (i < j) {
                int mid = (i + j) / 2;
                if (tails[mid] < x) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = x;
            if (i == size) {
                size++;
            }
        }
        
        return size;
    }
}
Problem 5 - Longest Common Subsequence (5 points)Problem Statement: Compute the length of the longest common subsequence shared between two strings text1 and text2.Algorithm & Formulation:State Definition: $dp[i][j]$ is the LCS length between prefix $text1[0...i-1]$ and $text2[0...j-1]$.Recurrence:
$$dp[i][j] = \begin{cases} dp[i-1][j-1] + 1 & \text{if } text1[i-1] == text2[j-1] \\ \max(dp[i-1][j], dp[i][j-1]) & \text{otherwise} \end{cases}$$Complexities:Time Complexity: $O(m \times n)$ — Matrix evaluation of dimension $m \times n$.Space Complexity: $O(m \times n)$ — DP table allocation.public class Problem05 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}
Problem 6 - Edit Distance (5 points)Problem Statement: Calculate the minimum operations (Insert, Delete, Replace) required to transform word1 to word2.Algorithm & Formulation:State Definition: $dp[i][j]$ = edit distance between prefix $word1[0...i-1]$ and $word2[0...j-1]$.Recurrence:
$$dp[i][j] = \begin{cases} dp[i-1][j-1] & \text{if } word1[i-1] == word2[j-1] \\ 1 + \min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) & \text{otherwise} \end{cases}$$Complexities:Time Complexity: $O(m \times n)$ — Grid traversal.Space Complexity: $O(m \times n)$ — 2D DP matrix.public class Problem06 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }
}
Problem 7 - Longest Palindromic Subsequence (5 points)Problem Statement: Return the length of the longest palindromic subsequence in string s.Algorithm & Formulation:State Definition: $dp[i][j]$ is the longest palindromic subsequence length within substring $s[i...j]$.Base Case: $dp[i][i] = 1$Recurrence:
$$dp[i][j] = \begin{cases} dp[i+1][j-1] + 2 & \text{if } s[i] == s[j] \\ \max(dp[i+1][j], dp[i][j-1]) & \text{otherwise} \end{cases}$$Complexities:Time Complexity: $O(n^2)$ — Nested interval loops.Space Complexity: $O(n^2)$ — 2D matrix.public class Problem07 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
Problem 8 - Palindromic Substrings (5 points)Problem Statement: Count all contiguous palindromic substrings in string s.Algorithm & Formulation:Strategy: Expand Around Center.Mechanism: Every character index $i$ serves as center for odd palindromes (i, i), and adjacent pairs (i, i+1) for even palindromes.Complexities:Time Complexity: $O(n^2)$ — $2n-1$ center points expanded in $O(n)$ bounds.Space Complexity: $O(1)$ — No additional array dynamic memory needed.public class Problem08 {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expandAroundCenter(s, i, i);     // Odd length
            count += expandAroundCenter(s, i, i + 1); // Even length
        }
        return count;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}
Problem 9 - Maximum Subarray (5 points)Problem Statement: Find the contiguous subarray with the largest sum and return its value.Algorithm & Formulation:Strategy: Kadane’s Algorithm.Transition: $currentSum_i = \max(nums[i], currentSum_{i-1} + nums[i])$.Complexities:Time Complexity: $O(n)$ — Single pass scan.Space Complexity: $O(1)$ — Primitive counters.public class Problem09 {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}
Problem 10 - House Robber (5 points)Problem Statement: Return the maximum money robbed without robbing two adjacent houses.Algorithm & Formulation:State Definition: $dp[i]$ is maximum profit up to house $i$.Recurrence: $dp[i] = \max(dp[i-1], dp[i-2] + nums[i])$.Space Optimization: Maintain values via two variables prev1 and prev2.Complexities:Time Complexity: $O(n)$ — One loop pass.Space Complexity: $O(1)$ — In-place variable swaps.public class Problem10 {
    public int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;
        
        for (int num : nums) {
            int temp = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = temp;
        }
        
        return prev1;
    }
}
🛡️ Defense & Examination Q&AWhat is the difference between DP (Dynamic Programming) and Greedy strategy?DP evaluates all sub-choices and combines optimal solutions of overlapping subproblems.Greedy makes the locally optimal choice at each step without reconsidering past decisions.Why does LIS run in $O(n \log n)$ instead of $O(n^2)$?By replacing the linear lookup with binary search over the monotonic tails array (patience sorting), we reduce lookup overhead per step from $O(n)$ to $O(\log n)$.How does space reduction work in House Robber & Min Cost Climbing Stairs?The DP recurrence relation only depends on $dp[i-1]$ and $dp[i-2]$. Storing the entire table is redundant; keeping two scalar pointers drops auxiliary space to $O(1)$.
