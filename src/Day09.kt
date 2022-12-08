import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day09 {

    private val day = "Day09"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(0)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(0)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(0)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(0)
    }

    private fun solution1(input: List<String>): Int {
        TODO()
    }

    private fun solution2(input: List<String>): Int {
        TODO()
    }
}




