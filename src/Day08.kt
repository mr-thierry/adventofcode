import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day08 {

    private val day = "Day08"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(95437)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(1307902)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(24933642)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(7068748)
    }

    private fun solution1(input: List<String>): Int {
        TODO()
    }

    private fun solution2(input: List<String>): Int {
        TODO()
    }
}




