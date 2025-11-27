package array_and_hashing

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