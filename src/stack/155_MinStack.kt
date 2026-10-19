package stack

import java.util.*
/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 *
 * void push(int val) pushes the element val onto the stack.
 *
 * void pop() removes the element on the top of the stack.
 *
 * int top() gets the top element of the stack.
 *
 * int getMin() retrieves the minimum element in the stack.
 *
 * You must implement a solution with O(1) time complexity for each function.
*/

fun main() {
    val minStack = MinStack()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin()) // return -3
    minStack.pop()
    println(minStack.top()) // return 0
    println(minStack.getMin()) // return -2
}

class MinStack {
    data class Entry(val num: Int, val min: Int)
    val stack = Stack<Entry>()

    fun push(value: Int) {
        if (stack.isEmpty()) {
            stack.push(Entry(value, value))
        } else {
            stack.push(Entry(value, minOf(value, stack.peek().min)))
        }
    }

    fun pop() {
        stack.pop()
    }

    fun top(): Int {
        return stack.peek().num
    }

    fun getMin(): Int {
        return stack.peek().min
    }
}