package array_and_hashing

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 */
fun main() {
    val board = arrayOf(
        charArrayOf('5','3','.','.','7','.','.','.','.'),
        charArrayOf('6','.','.','1','9','5','.','.','.'),
        charArrayOf('.','9','8','.','.','.','.','6','.'),
        charArrayOf('8','.','.','.','6','.','.','.','3'),
        charArrayOf('4','.','.','8','.','3','.','.','1'),
        charArrayOf('7','.','.','.','2','.','.','.','6'),
        charArrayOf('.','6','.','.','.','.','2','8','.'),
        charArrayOf('.','.','.','4','1','9','.','.','5'),
        charArrayOf('.','.','.','.','8','.','.','7','9')
    )
    println(isValidSudoku(board))
    println(isValidSudokuLessCode(board))
}

fun isValidSudokuLessCode(board: Array<CharArray>): Boolean {
    fun getSubBox(r: Int, c: Int): Int {
        return r/3 * 3 + c/3
    }

    val rowCounts = Array(9) { BooleanArray(10) { false } }
    val colCounts = Array(9) { BooleanArray(10) { false } }
    val subBoxCounts = Array(9) { BooleanArray(10) { false } }

    for (r in 0 until 9) {
        for (c in 0 until 9) {
            if (!board[r][c].isDigit()) continue

            val num = board[r][c].digitToInt()
            if (rowCounts[r][num]) return false
            rowCounts[r][num] = true

            if (colCounts[c][num]) return false
            colCounts[c][num] = true

            val subBox = getSubBox(r, c)
            if (subBoxCounts[subBox][num]) return false
            subBoxCounts[subBox][num] = true
        }
    }
    return true
}

/**
 * Time complexity: O(9*9) -> O(1)
 * Space complexity: O(10) -> O(1)
 */
fun isValidSudoku(board: Array<CharArray>): Boolean {
    fun isInvalid(r: Int, c: Int, freq: BooleanArray): Boolean {
        if (board[r][c] != '.') {
            val idx = board[r][c] - '0'
            if (freq[idx]) return true
            freq[idx] = true
        }
        return false
    }

    fun isValidRow(r: Int): Boolean {
        val freq = BooleanArray(10) { false }
        for (c in 0 until 9) {
            if (isInvalid(r, c, freq)) return false
        }
        return true
    }

    fun isValidColumn(c: Int): Boolean {
        val freq = BooleanArray(10) { false }
        for (r in 0 until 9) {
            if (isInvalid(r, c, freq)) return false
        }
        return true
    }

    fun isValidSubGrid(rs: Int, cs: Int): Boolean {
        val freq = BooleanArray(10) { false }
        for (r in rs until rs + 3) {
            for (c in cs until cs + 3) {
                if (isInvalid(r, c, freq)) return false
            }
        }
        return true
    }

    for (r in 0 until 9) {
        if (!isValidRow(r)) return false
    }

    for (c in 0 until 9) {
        if (!isValidColumn(c)) return false
    }

    for (rs in intArrayOf(0,3,6)) {
        for (cs in intArrayOf(0,3,6)) {
            if (!isValidSubGrid(rs, cs)) return false
        }
    }
    return true
}