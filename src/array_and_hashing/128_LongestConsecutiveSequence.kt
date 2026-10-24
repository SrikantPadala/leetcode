package array_and_hashing

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 * Example 1:
 * Input: nums = `[100,4,200,1,3,2]`
 * Output: 4
 * Explanation: The longest consecutive elements sequence is `[1, 2, 3, 4]`. Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = `[0,3,7,2,5,8,4,6,0,1]`
 * Output: 9
 *
 * Example 3:
 * Input: nums = `[1,0,1,2]`
 * Output: 3
 */

fun main() {
    val array = intArrayOf(100,4,200,1,3,2)
    // simple solution is to sort and then check sequence
    // optimized is think in terms of number line. Start at the beginning of a sequence
    longestConsecutive(array)
}

// 1,2,3,4     100    200
/**
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
fun longestConsecutive(nums: IntArray): Int {
    val set = nums.toHashSet()
    var longest = 0

    for (num in set) {
        if (!set.contains(num - 1)) { // doesn't have a left neighbor
            var length = 1
            while (set.contains(num + length)) {
                length++
            }
            longest = maxOf(longest, length)
        }
    }
    return longest
}