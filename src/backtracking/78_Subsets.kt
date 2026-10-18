package backtracking

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 *
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
fun main() {
    println(subsets(intArrayOf(1,2,3)))
    println(subsetsBitmask(intArrayOf(1,2,3)))
}

// https://www.youtube.com/watch?v=REOH22Xwdkk

/**
 * Time Complexity : O(2^n) for generating every subset and O(k) to insert every subset in another data structure
 * if the average length of every subset is k. Overall O(k * 2^n).
 * Space Complexity : O(2^n * k) to store every subset of average length k.
 * Auxiliary space is O(n) if n is the depth of the recursion tree.
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val subset = mutableListOf<Int>()

    fun dfs(i: Int) {
        if (i >= nums.size) {
            result.add(subset.toList())
            return
        }
        // decision to include nums[i]
        subset.add(nums[i])
        dfs(i + 1)
        // decision to exclude nums[i]
        subset.removeLast()
        dfs(i + 1)
    }
    dfs(0)
    return result
}

/**
 * Time Complexity : O(2^n) for generating every subset and O(n) to iterate over the nums. Overall O(n * 2^n).
 *
 * Space Complexity : O(2^n * k) to store every subset of average length k.
 */
fun subsetsBitmask(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val n = nums.size
    for (mask in 0 until (1 shl n)) {
        val subset = mutableListOf<Int>()
        for (i in 0 until n) {
            if (mask and (1 shl i) != 0) {
                subset.add(nums[i])
            }
        }
        result.add(subset.toList())
    }
    return result
}