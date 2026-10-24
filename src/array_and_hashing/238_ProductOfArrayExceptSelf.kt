package array_and_hashing

/**
 * Given an integer array nums, return an array answer such that
 * `answer[i]` is equal to the product of all the elements of nums except `nums[i]`.
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example:
 * Input: nums = `[-1,1,0,-3,3]`
 * Output: `[0,0,9,0,0]`
 */
fun main() {
    println(productExceptSelf(intArrayOf(1,2,3,4)).contentToString())
    println(productExceptSelf(intArrayOf(-1,1,0,-3,3)).contentToString())
}

/**
 * Time complexity: O(n)
 * Space complexity: O(1) except result output array
 */
/*
    For each element, I need the product of everything before it and everything after it.
    I first store the prefix product in the result array.
    Then I traverse from right to left while maintaining a suffix product and multiply it into the result.
    This avoids division, handles zeroes naturally, and uses O(n) time with O(1) auxiliary space.
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val res = IntArray(nums.size) { 1 }

    var leftProduct = 1
    for (i in nums.indices) {
        res[i] = leftProduct
        leftProduct *= nums[i]
    }

    var rightProduct = 1
    for (i in nums.size-1 downTo 0) {
        res[i] *= rightProduct
        rightProduct *= nums[i]
    }
    return res
}