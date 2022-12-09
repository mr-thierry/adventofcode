import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day09 {

    private val day = "Day09"

    private val arraySize = 2000

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(13)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(5981)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(1)
    }

    @Test
    fun testSolution2b() {
        assertThat(solution2(readInput("${day}_test_b"))).isEqualTo(36)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(2352)
    }

    private fun solution1(input: List<String>): Int {
        val steps = parseSteps(input)

        val locationVisited = Array(arraySize) { Array(arraySize) { false } }
        walkSolution1(steps, locationVisited)

        return locationVisited.sumOf { rows -> rows.count { it } }
    }

    private fun solution2(input: List<String>): Int {
        val steps = parseSteps(input)

        val locationVisited = Array(arraySize) { Array(arraySize) { false } }
        walkSolution2(steps, locationVisited)

        return locationVisited.sumOf { rows -> rows.count { it } }
    }

    private fun walkSolution1(steps: List<Step>, locationVisited: Array<Array<Boolean>>) {
        var headPosition = Coordinate(arraySize / 2, arraySize / 2)
        var tailPosition = Coordinate(arraySize / 2, arraySize / 2)

        locationVisited[tailPosition.x][tailPosition.y] = true
        steps.forEach { step ->
            for (i in 0 until step.count) {
                headPosition = headPosition.moveOneCount(step)
                if (!tailPosition.isAdjacent(headPosition)) {
                    tailPosition = tailPosition.moveToCatch(headPosition)
                    locationVisited[tailPosition.y][tailPosition.x] = true

                    if (!tailPosition.isAdjacent(headPosition)) {
                        error("should not be here")
                    }
                }
            }
        }
    }

    private fun walkSolution2(steps: List<Step>, locationVisited: Array<Array<Boolean>>) {
        var headPosition = Coordinate(arraySize / 2, arraySize / 2)
        val tails = Array(9) { Coordinate(arraySize / 2, arraySize / 2) }
        tails[tails.size - 1] = tails[tails.size - 1].copy(isTail = true)

        locationVisited[headPosition.x][headPosition.y] = true
        steps.forEach { step ->
            for (stepIdx in 0 until step.count) {
                headPosition = headPosition.moveOneCount(step)

                tails[0] = moveRope(headPosition, tails[0], locationVisited)

                for (i in 1 until tails.count()) {
                    tails[i] = moveRope(tails[i - 1], tails[i], locationVisited)
                }
            }
        }
    }

    private fun moveRope(
        headPosition: Coordinate,
        tailPosition: Coordinate,
        locationVisited: Array<Array<Boolean>>
    ): Coordinate {
        if (!tailPosition.isAdjacent(headPosition)) {
            val newTailPosition = tailPosition.moveToCatch(headPosition)

            if (tailPosition.isTail) {
                locationVisited[newTailPosition.y][newTailPosition.x] = true
            }

            if (!newTailPosition.isAdjacent(headPosition)) {
                error("should not be here")
            }
            return newTailPosition
        }

        return tailPosition
    }

    private fun parseSteps(input: List<String>): List<Step> {
        return input.map {
            val strs = it.split(" ")
            Step(Direction.from(strs[0]), strs[1].toInt())
        }
    }

    private data class Step(val direction: Direction, val count: Int)
    private data class Coordinate(val x: Int, val y: Int, val isTail: Boolean = false)

    private fun Coordinate.moveOneCount(step: Step): Coordinate {
        return when (step.direction) {
            Direction.LEFT -> Coordinate(x - 1, y)
            Direction.UP -> Coordinate(x, y + 1)
            Direction.RIGHT -> Coordinate(x + 1, y)
            Direction.DOWN -> Coordinate(x, y - 1)
        }
    }

    private fun Coordinate.isAdjacent(other: Coordinate): Boolean {
        return (x - 1 == other.x || x == other.x || x + 1 == other.x) && (y - 1 == other.y || y == other.y || y + 1 == other.y)
    }

    private fun Coordinate.moveToCatch(other: Coordinate): Coordinate {
        when {
            x == other.x -> {
                return when {
                    y < other.y -> Coordinate(x, y + 1, isTail)
                    else -> Coordinate(x, y - 1, isTail)
                }
            }

            y == other.y -> {
                return when {
                    x < other.x -> Coordinate(x + 1, y, isTail)
                    else -> Coordinate(x - 1, y, isTail)
                }
            }

            else -> {
                when {
                    x < other.x -> {
                        return if (y < other.y) {
                            Coordinate(x + 1, y + 1, isTail)
                        } else {
                            Coordinate(x + 1, y - 1, isTail)
                        }
                    }

                    else -> {
                        return if (y < other.y) {
                            Coordinate(x - 1, y + 1, isTail)
                        } else {
                            Coordinate(x - 1, y - 1, isTail)
                        }
                    }
                }
            }
        }
    }

    enum class Direction {
        LEFT, UP, RIGHT, DOWN;

        companion object {
            fun from(s: String): Direction {
                return Direction.values().first { it.name.substring(0, 1) == s }
            }
        }
    }
}




