package stack

import java.util.*

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Input: heights = [2,1,5,6,2,3]
 *
 * Output: 10
 */
fun main() {
    println(largestRectangleArea(intArrayOf(2, 1, 5, 6, 2, 3)))
    println(largestRectangleArea(intArrayOf(0)))
}

/**
 * Naive Approach : By Finding Max Area of Rectangles all Heights - O(n^2) Time and O(1)
 * The idea is to fix each bar as the height of the rectangle and expand towards the left and right while the
 * bars are at least as tall as the current bar. For every valid step, we keep adding the current bar’s height
 * to the area. By doing this for all bars and keeping track of the maximum value.
 */
fun getMaxArea(arr: IntArray): Int {
    var res = 0
    val n = arr.size

    for (i in 0 until n) {
        var curr = arr[i]
        // Traverse left while we have a greater height bar
        run {
            var j = i - 1
            while (j >= 0 && arr[j] >= arr[i]) {
                curr += arr[i]
                j--
            }
        }
        // Traverse right while we have a greater height bar
        var j = i + 1
        while (j < n && arr[j] >= arr[i]) {
            curr += arr[i]
            j++
        }

        res = maxOf(res, curr)
    }
    return res
}

/**
 * The idea is to maintain a stack of bar indices in increasing height order. When a shorter bar is encountered,
 * it means the bar at the top of the stack cannot extend further to the right. We pop it, and using the current index
 * as the right boundary and the new top of the stack as the left boundary,
 * compute the area with the popped bar’s height as the smallest height.
 */
/**
 * Time complexity: O(n)
 * Space complexity; O(n)
 */
fun largestRectangleArea(heights: IntArray): Int {
    val indices = Stack<Int>()
    var i = 0; var maxArea = 0

    fun updateMaxArea() {
        val top: Int = indices.pop()
        maxArea = maxOf(
            maxArea,
            (if (indices.isEmpty()) i else (i - 1 - indices.peek())) * heights[top]
        )
    }

    while (i < heights.size) {
        if (indices.isEmpty() || heights[indices.peek()] <= heights[i]) {
            indices.push(i++)
        } else {
            updateMaxArea()
        }
    }
    while (!indices.isEmpty()) {
        updateMaxArea()
    }
    return maxArea
}