import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day05 {

    private val day = "Day05"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"), 3)).isEqualTo("CMZ")
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day), 9)).isEqualTo("RTGWZTHLD")
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"), 3)).isEqualTo("MCD")
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day), 9)).isEqualTo("STHGRZZFR")
    }

    private fun solution1(input: List<String>, columnCount: Int): String {
        val crates = input.readCrates(columnCount)
        val moves = input.readMoves()

        executeMovesCrateMover9000(crates, moves)

        return crates.joinToString(separator = "") { it.first() }
    }

    private fun solution2(input: List<String>, columnCount: Int): String {
        val crates = input.readCrates(columnCount)
        val moves = input.readMoves()

        executeMovesCrateMover9001(crates, moves)

        return crates.joinToString(separator = "") { it.first() }
    }

    private fun List<String>.readCrates(columnCount: Int): MutableList<MutableList<String>> {
        val crates = mutableListOf<MutableList<String>>().apply {
            for (i in 0 until columnCount) {
                add(mutableListOf())
            }
        }

        forEach { string: String ->
            if (!string.contains("[")) return@forEach

            val chunk = string.chunked(4)
            chunk.forEachIndexed { index, s: String ->
                if (s.isNotBlank()) crates[index].add(
                    s.replace("[", "")
                        .replace("]", "")
                        .trim()
                )
            }
        }

        return crates
    }

    private fun List<String>.readMoves(): List<Move> {
        val moves = mutableListOf<Move>()

        forEach { string: String ->
            if (string.contains("move")) {
                val chunk = string.split(" ")
                moves.add(Move(chunk[1].toInt(), chunk[3].toInt(), chunk[5].toInt()))
            }
        }

        return moves
    }

    private fun executeMovesCrateMover9000(crates: MutableList<MutableList<String>>, moves: List<Move>) {
        moves.forEach { move ->
            val columnStart = crates[move.start - 1]
            val columnEnd = crates[move.end - 1]
            for (i in 0 until move.quantity) {
                columnEnd.add(0, columnStart.removeFirst())
            }
        }
    }

    private fun executeMovesCrateMover9001(crates: MutableList<MutableList<String>>, moves: List<Move>) {
        moves.forEach { move ->
            val columnStart = crates[move.start - 1]
            val columnEnd = crates[move.end - 1]

            val tempList = mutableListOf<String>()
            for (i in 0 until move.quantity) {
                tempList.add(columnStart.removeFirst())
            }

            columnEnd.addAll( 0, tempList)
        }

        println(crates)
    }

    data class Move(val quantity: Int, val start: Int, val end: Int)
}



