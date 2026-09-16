package array_and_hashing

fun main() {
    println(productExceptSelf(intArrayOf(1,2,3,4)).contentToString())
    println(productExceptSelf(intArrayOf(-1,1,0,-3,3)).contentToString())
}

/*
    Time complexity: O(n)
    Space complexity: O(1) except result output array
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