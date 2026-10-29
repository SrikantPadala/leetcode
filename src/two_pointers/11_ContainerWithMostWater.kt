package two_pointers

/**
 * You are given an integer array height of length n. There are n vertical lines drawn
 * such that the two endpoints of the ith line are `(i, 0)` and `(i, height[i])`.
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 */
fun main() {
    println(maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}

/**
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
fun maxArea(height: IntArray): Int {
    var maxArea = 0
    var start = 0; var end = height.lastIndex
    while (start < end) {
        val h = minOf(height[start], height[end])
        maxArea = maxOf(maxArea, h * (end - start))
        // any height[start] <= h after index `start` will have lesser area than the current computed area
        while (height[start] <= h && start < end) start++
        while (height[end] <= h && start < end) end--
    }
    return maxArea
}