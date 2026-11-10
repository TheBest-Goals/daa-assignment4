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



# DAA Assignment 4 — Report and Defense (50 points)

**Course:** Design and Analysis of Algorithms (DAA)  
**Language:** Java 17  

---

## 📊 Summary Table

| # | Problem Name | Algorithm | Time | Space | Source Code |
|---|---|---|---|---|---|
| 01 | Counting Bits | Bitwise + DP | $O(n)$ | $O(n)$ | `Problem01.java` |
| 02 | Jump Game II | Greedy / BFS | $O(n)$ | $O(1)$ | `Problem02.java` |
| 03 | Min Cost Climbing Stairs | 1D Dynamic Programming | $O(n)$ | $O(1)$ | `Problem03.java` |
| 04 | Longest Increasing Subsequence | Patience Sorting + Binary Search | $O(n \log n)$ | $O(n)$ | `Problem04.java` |
| 05 | Longest Common Subsequence | 2D Dynamic Programming | $O(m \times n)$ | $O(m \times n)$ | `Problem05.java` |
| 06 | Edit Distance | 2D Dynamic Programming | $O(m \times n)$ | $O(m \times n)$ | `Problem06.java` |
| 07 | Longest Palindromic Subsequence | Interval DP | $O(n^2)$ | $O(n^2)$ | `Problem07.java` |
| 08 | Palindromic Substrings | Expand Around Center | $O(n^2)$ | $O(1)$ | `Problem08.java` |
| 09 | Maximum Subarray | Kadane's Algorithm | $O(n)$ | $O(1)$ | `Problem09.java` |
| 10 | House Robber | Dynamic Programming | $O(n)$ | $O(1)$ | `Problem10.java` |

---

## 💻 Source Code

### Problem 1 - Counting Bits (5 pts)
```java
public class Problem01 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
