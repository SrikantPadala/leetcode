package graph

/**
 * Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */

fun main() {
    val grid: Array<CharArray> = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )

    print(numIslandsModificationNotAllowed(grid))
}

/**
 * Time complexity: O(m*n)
 * Space complexity: O(m*n)
 */
fun numIslandsModificationNotAllowed(grid: Array<CharArray>): Int {
    val diffX = intArrayOf(-1,0,1,0)
    val diffY = intArrayOf(0,1,0,-1)

    val m = grid.size
    val n = grid[0].size
    val visited = Array<BooleanArray>(m) { BooleanArray(n) }
    var count = 0

    fun isSafe(x: Int, y: Int): Boolean {
        return (x in 0 until m) && (y in 0 until n)
    }

    fun dfs(row: Int, col: Int) {
        visited[row][col] = true
        for (d in 0 until 4) {
            val x = row + diffX[d]
            val y = col + diffY[d]
            if (isSafe(x, y) && grid[x][y] == '1' && !visited[x][y]) {
                dfs(x, y)
            }
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (grid[i][j] == '1' && !visited[i][j]) {
                dfs(i, j)
                count++
            }
        }
    }

    return count
}

fun numIslandsModificationAllowed(grid: Array<CharArray>): Int {
    val diffX = intArrayOf(-1,0,1,0)
    val diffY = intArrayOf(0,1,0,-1)

    val m = grid.size
    val n = grid[0].size
    var ch = '2'

    fun isSafe(x: Int, y: Int): Boolean {
        return x >= 0 && x < m && y >= 0 && y < n
    }

    fun dfs(row: Int, col: Int, ch: Char) {
        grid[row][col] = ch
        for (d in 0 until 4) {
            val x = row + diffX[d]
            val y = col + diffY[d]
            if (isSafe(x, y) && grid[x][y] == '1') {
                dfs(x, y, ch)
            }
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (grid[i][j] == '1') {
                dfs(i, j, ch++)
            }
        }
    }

    return ch - '0' - 2
}