package two_pointers

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
 * all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
 * letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 */
fun main() {
    println(isPalindrome("A man, a plan, a canal: Panama"))
}

/**
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
fun isPalindrome(s: String): Boolean {
    var i = 0
    var j = s.lastIndex
    while (i < j) {
        when {
            !s[i].isLetterOrDigit() -> i++
            !s[j].isLetterOrDigit() -> j--
            !s[i].equals(s[j], true) -> return false
            else -> {
                i++
                j--
            }
        }
    }
    return true
}