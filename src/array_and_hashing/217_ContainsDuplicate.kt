package array_and_hashing
/*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true
Explanation:
The element 1 occurs at the indices 0 and 3.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 6)

    println(containsDuplicate(nums))
}

/*
    Time complexity: O(n)
    Space complexity: O(n)
 */
fun containsDuplicate(nums: IntArray): Boolean {
    val hashSet = HashSet<Int>()
    nums.forEach {
        if (!hashSet.add(it))
            return true
    }
    return false
}

