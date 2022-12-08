import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day08 {

    private val day = "Day08"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(21)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(1787)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(8)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(440640)
    }

    private fun solution1(input: List<String>): Int {
        val trees = buildForest(input)

        return findVisibleTrees(trees)
    }

    private fun solution2(input: List<String>): Int {
        val trees = buildForest(input)

        return findMaxScenicScore(trees)
    }

    private fun buildForest(input: List<String>): List<List<Int>> {
        val trees = mutableListOf<MutableList<Int>>()

        input.forEachIndexed { i, s ->
            s.toCharArray().forEachIndexed { j, c ->
                if (j == 0) {
                    trees.add(mutableListOf())
                }
                trees[i].add(c.toString().toInt())
            }
        }

        return trees
    }

    private fun findVisibleTrees(trees: List<List<Int>>): Int {
        var count = 0
        trees.forEachIndexed { rowIdx, rows ->
            rows.forEachIndexed { colIdx, height ->
                if (rowIdx == 0) {
                    count++
                } else if (rowIdx == trees.size - 1) {
                    count++
                } else if (colIdx == 0) {
                    count++
                } else if (colIdx == rows.size - 1) {
                    count++
                } else if (left(trees, rowIdx, colIdx).all { it < height }) {
                    count++
                } else if (right(trees, rowIdx, colIdx).all { it < height }) {
                    count++
                } else if (top(trees, rowIdx, colIdx).all { it < height }) {
                    count++
                } else if (bottom(trees, rowIdx, colIdx).all { it < height }) {
                    count++
                }
            }
        }

        return count
    }

    private fun findMaxScenicScore(trees: List<List<Int>>): Int {
        var max = 0

        trees.forEachIndexed { rowIdx, rows ->
            rows.forEachIndexed { colIdx, height ->
                if (rowIdx == 0 || rowIdx == trees.size - 1 || colIdx == 0 || colIdx == rows.size - 1) {
                    //ignore
                } else {
                    val scoreLeft = left(trees, rowIdx, colIdx).reversed().score(height)
                    val scoreTop = top(trees, rowIdx, colIdx).reversed().score(height)
                    val scoreRight = right(trees, rowIdx, colIdx).score(height)
                    val scoreBottom = bottom(trees, rowIdx, colIdx).score(height)

                    val totalScore = scoreLeft * scoreTop * scoreRight * scoreBottom
                    if (max < totalScore) {
                        max = totalScore
                    }
                }

            }
        }

        return max
    }

    private fun List<Int>.score(height: Int): Int {
        val indexOfFirstBlockedView = indexOfFirst { it >= height }
        val result = if (indexOfFirstBlockedView == -1) size else indexOfFirstBlockedView + 1
        return result
    }

    private fun left(trees: List<List<Int>>, rowIdx: Int, colIdx: Int) = trees[rowIdx].subList(0, colIdx)

    private fun top(trees: List<List<Int>>, rowIdx: Int, colIdx: Int): List<Int> =
        trees.map { col -> col[colIdx] }.subList(0, rowIdx)

    private fun right(trees: List<List<Int>>, rowIdx: Int, colIdx: Int) =
        trees[rowIdx].subList(colIdx + 1, trees[rowIdx].size)

    private fun bottom(trees: List<List<Int>>, rowIdx: Int, colIdx: Int) =
        trees.map { col -> col[colIdx] }.subList(rowIdx + 1, trees.size)

}




