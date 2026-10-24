package array_and_hashing

/**
 * You are given an array of integers `nums` and an integer `target`,
 * return indices of the two numbers such that they add up to `target`.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: `nums = [2,7,11,15], target = 9`
 *
 * Output: `[0,1]`
 */
fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 6)

    println(twoSum(nums, 12).contentToString())
}

/*
    Time complexity: O(n)
    Space complexity: O(n)
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    nums.forEachIndexed { i, num ->
        val index = map[target - num] ?: -1
        if (index != -1) {
            return intArrayOf(i, index)
        }
        map[num] = i
    }
    return intArrayOf(-1,-1)
}