package stack

import java.util.Stack

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Example 4:
 * Input: s = "([])"
 * Output: true
 *
 * Example 5:
 * Input: s = "([)]"
 * Output: false
 */

fun main() {
    println(isValid("()[]{}"))
    println(isValid("[(])"))
}

/**
 * Time complexity: O(n) when n is length of string
 *
 * Space complexity: O(n) the maximum elements the stack can have, if all are opening brackets
 */
fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    if (s.length % 2 != 0) return false

    for (ch in s) {
        when {
            ch == '(' || ch == '{' || ch == '[' -> stack.push(ch)
            stack.isNotEmpty() -> {
                when {
                    ch == ')' && stack.peek() != '(' -> return false
                    ch == '}' && stack.peek() != '{' -> return false
                    ch == ']' && stack.peek() != '[' -> return false
                }
                stack.pop()
            }

            else -> return false
        }
    }
    return stack.isEmpty()
}