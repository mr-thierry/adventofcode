import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day06 {

    private val day = "Day06"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo("")
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo("")
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo("")
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo("")
    }

    private fun solution1(input: List<String>): String {
        TODO()
    }

    private fun solution2(input: List<String>): String {
        TODO()
    }

}



