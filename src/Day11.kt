import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day11 {

    private val day = "Day11"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(13140)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(13440)
    }

    @Test
    fun testSolution2() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(13140)
    }

    @Test
    fun solution2() {
        assertThat(solution1(readInput(day))).isEqualTo(13440)
    }

    private fun solution1(input: List<String>): Int {
        TODO()
    }

    private fun solution2(input: List<String>): List<String> {
        TODO()
    }

}





