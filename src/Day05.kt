import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day05 {

    private val day = "Day05"

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
        TODO()
    }

    private fun solution2(input: List<String>): Int {
        TODO()
    }

}

