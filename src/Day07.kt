import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day07 {

    private val day = "Day07"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput(day)[0])).isEqualTo("")
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day)[0])).isEqualTo("")
    }

    @Test
    fun testSolution2() {
        val strings = readInput("${day}_test")
        assertThat(solution2(strings[0])).isEqualTo(19)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day)[0])).isEqualTo("")
    }

    private fun solution1(input: String): Int {
        TODO()
    }

    private fun solution2(input: String): Int {
        TODO()
    }

}



