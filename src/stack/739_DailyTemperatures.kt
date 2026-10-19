package stack

import java.util.*

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
 * is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 *
 * Output: [1,1,4,2,1,1,0,0]
 */
fun main() {
    val res = dailyTemperatures(intArrayOf(73,74,75,71,69,72,76,73))
    println(res.contentToString())
}

/**
 * Time complexity: O(n) when n is length of array
 *
 * Space complexity: O(n) the maximum elements the stack can have, if the numbers are in decreasing order
 */
fun dailyTemperatures(temperatures: IntArray): IntArray {
    val res = IntArray(temperatures.size) //default is 0
    val stk = Stack<Int>() // decreasing stack
    for (i in temperatures.indices) {
        while (stk.isNotEmpty() && temperatures[stk.peek()] < temperatures[i]) {
            val idx = stk.pop()
            res[idx] = i - idx
        }
        stk.push(i)
    }
    return res
}