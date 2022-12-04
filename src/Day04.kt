import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day04 {

    private val day = "Day04"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(2)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(453)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(4)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(919)
    }

    private fun solution1(input: List<String>): Int {
        var value = 0
        input.forEach {
            val pairs = it.split(",")

            val pair1 = pairs[0].split("-")
            val startPair1 = pair1[0].toInt()
            val endPair1 = pair1[1].toInt()

            val pair2 = pairs[1].split("-")
            val startPair2 = pair2[0].toInt()
            val endPair2 = pair2[1].toInt()

            if (startPair1 <= startPair2 && endPair1 >= endPair2) {
                value++
            } else if (startPair2 <= startPair1 && endPair2 >= endPair1) {
                value++
            }
        }

        return value
    }

    private fun solution2(input: List<String>): Int {
        var value = 0
        input.forEach {
            val pairs = it.split(",")

            val pair1 = pairs[0].split("-")
            val startPair1 = pair1[0].toInt()
            val endPair1 = pair1[1].toInt()

            val pair2 = pairs[1].split("-")
            val startPair2 = pair2[0].toInt()
            val endPair2 = pair2[1].toInt()

            if (endPair2 >= startPair1 && startPair2 <= endPair1) {
                value++
            }
        }

        return value
    }

}

