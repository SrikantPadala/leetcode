package backtracking

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order. The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 *
 * Output: [[2,2,3],[7]]
 *
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 */

fun main() {
    println(combinationSum(intArrayOf(2,3,6,7), 7))
    println(combinationSumDp(intArrayOf(2,3,6,7), 7))
}

// https://www.youtube.com/watch?v=GBKI9VSKdGg
/**
 * n = number of candidates
 * T = target
 * m = smallest candidate
 * K = number of valid combinations returned
 *
 * Time complexity: O(n^{T/m} + K * T/m)
 * Space complexity: O(T/m) for the recursion stack and current combination.
 */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()

    fun dfs(i: Int, total: Int)  {
        if (total == target) {
            result.add(currentCombination.toList())
            return
        }
        if (i > candidates.lastIndex || total > target) return
        // include
        currentCombination.add(candidates[i])
        dfs(i, total + candidates[i]) // i -> since repetition is allowed
        // exclude
        currentCombination.removeLast()
        dfs(i + 1, total)
    }

    dfs(0, 0)
    return result
}

/**
 * The idea is to build combinations for each sum from 0 up to target, reusing previously computed combinations.
 */
fun combinationSumDp(candidates: IntArray, target: Int): List<List<Int>> {
    val dp = Array(target + 1) { mutableListOf<List<Int>>() }
    dp[0].add(emptyList())

    for (num in candidates) {
        for (sum in num..target) {
            for (combination in dp[sum - num]) {
                dp[sum].add(combination + num)
            }
        }
    }

    return dp[target]
}