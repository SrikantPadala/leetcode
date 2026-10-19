package stack

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * The valid operators are '+', '-', '*', and '/'.
 *
 * Each operand may be an integer or another expression.
 *
 * The division between two integers always truncates toward zero.
 *
 * There will not be any division by zero.
 *
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 *
 * Output: 22
 *
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 *
 * = ((10 * (6 / -132)) + 17) + 5
 *
 * = ((10 * 0) + 17) + 5
 *
 * = (0 + 17) + 5
 *
 * = 17 + 5
 *
 * = 22
 */
fun main() {
    println(evalRPN(arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+")))
}

/**
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
fun evalRPN(tokens: Array<String>): Int {
    val stack = ArrayDeque<Int>()
    for (token in tokens) {
        when (token) {
            "+", "-", "*", "/" -> {
                stack.addLast(eval(stack.removeLast(), stack.removeLast(), token))
            }
            else -> {
                stack.addLast(token.toInt())
            }
        }
    }
    return stack.last()
}

private fun eval(num2: Int, num1: Int, token: String): Int {
    return when (token) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> 0
    }
}