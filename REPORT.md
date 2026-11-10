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



Problem 1: Counting BitsIntuition & Approach:
To count set bits ($1$s) for each integer $i$ from $0$ to $n$ in $O(n)$ time, we use Dynamic Programming with bit manipulation. Shifting an integer right by $1$ bit (i >> 1) removes its least significant bit. The number of set bits in $i$ equals the number of set bits in i >> 1 plus $1$ if $i$ is odd (i & 1).State & Recurrence Relation:State: dp[i] = total number of set bits ($1$s) in the binary representation of integer $i$.Base Case: dp[0] = 0Recurrence: dp[i] = dp[i >> 1] + (i & 1)Complexity:Time Complexity: $\mathcal{O}(n)$ — single-pass array loop, constant time $\mathcal{O}(1)$ operations per element.Space Complexity: $\mathcal{O}(n)$ — output array of size $n+1$.Javapublic class Problem01 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
Problem 2: Jump Game IIIntuition & Approach:
We use a Greedy Algorithm (implicit BFS range expansion). At any step, we track the maximum distance reachable from the current window (farthest). When we iterate up to currentEnd (the boundary of our previous jump), we are forced to commit to a new jump, updating currentEnd = farthest.State & Strategy:Variables: jumps (total jump count), currentEnd (max index reachable with current jump count), farthest (max index reachable with next jump).Loop Condition: Iterate from index $0$ to $n-2$. (We don't need to jump from $n-1$).Complexity:Time Complexity: $\mathcal{O}(n)$ — single pass through array.Space Complexity: $\mathcal{O}(1)$ — constant memory.Javapublic class Problem02 {
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
Problem 3: Min Cost Climbing StairsIntuition & Approach:
At step $i$, we could have arrived either from step $i-1$ or step $i-2$. To minimize the total cost, we choose the path with the minimum cumulative cost. We optimize space by keeping track of only the last two step costs.State & Recurrence Relation:State: dp[i] = minimum cost to reach step $i$.Base Cases: dp[0] = cost[0], dp[1] = cost[1].Recurrence: dp[i] = cost[i] + min(dp[i - 1], dp[i - 2])Complexity:Time Complexity: $\mathcal{O}(n)$ — single pass over the cost array.Space Complexity: $\mathcal{O}(1)$ — space optimized using two scalar variables (first, second).Javapublic class Problem03 {
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
Problem 4: Longest Increasing Subsequence (LIS)Intuition & Approach:
We use Patience Sorting combined with Binary Search (tails array approach). tails[k] stores the smallest tail value among all increasing subsequences of length $k + 1$. For each element $x$ in nums, if $x$ is larger than all elements in tails, we append it; otherwise, we overwrite the first element in tails that is $\ge x$ using binary search.State & Recurrence Relation:State: tails[i] = minimum end element of an increasing subsequence of length $i+1$.Transition: Binary search position idx for $x$ in tails[0...size-1]. Update tails[idx] = x. If idx == size, increment size.Complexity:Time Complexity: $\mathcal{O}(n \log n)$ — binary search ($\mathcal{O}(\log n)$) performed for each of the $n$ elements.Space Complexity: $\mathcal{O}(n)$ — auxiliary array tails.Javapublic class Problem04 {
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
Problem 5: Longest Common Subsequence (LCS)Intuition & Approach:
We build a 2D DP matrix to compare characters of text1 and text2 position by position. If characters match, we add $1$ to the diagonal value (dp[i-1][j-1]). If they do not match, we take the maximum between excluding current character of text1 (dp[i-1][j]) or text2 (dp[i][j-1]).State & Recurrence Relation:State: dp[i][j] = length of LCS of prefix text1[0...i-1] and prefix text2[0...j-1].Base Cases: dp[0][j] = 0, dp[i][0] = 0.Recurrence:$$\text{dp}[i][j] = \begin{cases} \text{dp}[i-1][j-1] + 1 & \text{if } \text{text1}[i-1] == \text{text2}[j-1] \\ \max(\text{dp}[i-1][j], \text{dp}[i][j-1]) & \text{otherwise} \end{cases}$$Complexity:Time Complexity: $\mathcal{O}(m \times n)$ — filling an $m \times n$ table, where $m = \text{length}(text1)$ and $n = \text{length}(text2)$.Space Complexity: $\mathcal{O}(m \times n)$ — 2D DP grid.Javapublic class Problem05 {
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
Problem 6: Edit Distance (Wagner-Fischer Algorithm)Intuition & Approach:
To convert word1 to word2, we dynamically determine the minimum cost of operations (Insertion, Deletion, Substitution).State & Recurrence Relation:State: dp[i][j] = minimum edit distance between word1[0...i-1] and word2[0...j-1].Base Cases: dp[i][0] = i (deletions), dp[0][j] = j (insertions).Recurrence:$$\text{dp}[i][j] = \begin{cases} \text{dp}[i-1][j-1] & \text{if } \text{word1}[i-1] == \text{word2}[j-1] \\ 1 + \min(\text{dp}[i-1][j-1], \text{dp}[i-1][j], \text{dp}[i][j-1]) & \text{otherwise} \end{cases}$$(where operations correspond to Replace, Delete, Insert respectively).Complexity:Time Complexity: $\mathcal{O}(m \times n)$ — filling an $m \times n$ matrix.Space Complexity: $\mathcal{O}(m \times n)$ — 2D DP table.Javapublic class Problem06 {
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
Problem 7: Longest Palindromic SubsequenceIntuition & Approach:
We evaluate substrings s[i...j] from shorter to longer lengths. If boundary characters match (s[i] == s[j]), we add $2$ to the result of inner substring s[i+1...j-1]. If not, we take the maximum by dropping either the leftmost or rightmost character.State & Recurrence Relation:State: dp[i][j] = length of longest palindromic subsequence in substring s[i...j].Base Cases: dp[i][i] = 1 for all $0 \le i < n$.Recurrence:$$\text{dp}[i][j] = \begin{cases} \text{dp}[i+1][j-1] + 2 & \text{if } s[i] == s[j] \\ \max(\text{dp}[i+1][j], \text{dp}[i][j-1]) & \text{otherwise} \end{cases}$$Complexity:Time Complexity: $\mathcal{O}(n^2)$ — double nested loop processing all substring intervals.Space Complexity: $\mathcal{O}(n^2)$ — 2D matrix storage.Javapublic class Problem07 {
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
Problem 8: Palindromic SubstringsIntuition & Approach:
Instead of $\mathcal{O}(n^3)$ brute force, we use the Expand Around Center strategy. Every character index can serve as the center of an odd-length palindrome (i, i), and every adjacent pair can serve as the center of an even-length palindrome (i, i+1).Algorithm Mechanism:For each position, expand outward matching left and right pointers while characters match.Increment valid substring count on every successful character match.Complexity:Time Complexity: $\mathcal{O}(n^2)$ — $2n - 1$ centers, expanding up to length $n$.Space Complexity: $\mathcal{O}(1)$ — constant auxiliary space.Javapublic class Problem08 {
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
Problem 9: Maximum Subarray (Kadane's Algorithm)Intuition & Approach:
We use Kadane's Algorithm. At each index $i$, we decide whether to extend the existing running sum (currentSum + nums[i]) or start a brand new contiguous subarray starting at nums[i].State & Recurrence Relation:State: currentSum = maximum sum of contiguous subarray ending at index $i$.Transition: currentSum = max(nums[i], currentSum + nums[i])Global Answer: maxSum = max(maxSum, currentSum)Complexity:Time Complexity: $\mathcal{O}(n)$ — single pass.Space Complexity: $\mathcal{O}(1)$ — constant memory.Javapublic class Problem09 {
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
Problem 10: House RobberIntuition & Approach:
At house $i$, we face a choice: either rob house $i$ (adding its value to max money robbed up to $i-2$) or skip house $i$ (keeping max money robbed up to $i-1$). We maintain two state variables to optimize space to $\mathcal{O}(1)$.State & Recurrence Relation:State: dp[i] = maximum money robbed up to house index $i$.Recurrence: dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])Complexity:Time Complexity: $\mathcal{O}(n)$ — single linear scan.Space Complexity: $\mathcal{O}(1)$ — space optimized via two variables (prev1, prev2).Javapublic class Problem10 {
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

