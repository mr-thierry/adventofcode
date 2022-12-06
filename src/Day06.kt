import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day06 {

    private val day = "Day06"

    @Test
    fun testSolution1() {
        val strings = readInput("${day}_test")
        assertThat(solution1(strings[0])).isEqualTo(7)
        assertThat(solution1(strings[1])).isEqualTo(5)
        assertThat(solution1(strings[2])).isEqualTo(6)
        assertThat(solution1(strings[3])).isEqualTo(10)
        assertThat(solution1(strings[4])).isEqualTo(11)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day)[0])).isEqualTo(1779)
    }

    @Test
    fun testSolution2() {
        val strings = readInput("${day}_test")
        assertThat(solution2(strings[0])).isEqualTo(19)
        assertThat(solution2(strings[1])).isEqualTo(23)
        assertThat(solution2(strings[2])).isEqualTo(23)
        assertThat(solution2(strings[3])).isEqualTo(29)
        assertThat(solution2(strings[4])).isEqualTo(26)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day)[0])).isEqualTo(2635)
    }

    private fun solution1(input: String): Int {
        return findStartOfStream(input, matchCount = 4)

    }

    private fun solution2(input: String): Int {
        return findStartOfStream(input, matchCount = 14)
    }

    private fun findStartOfStream(input: String, matchCount: Int): Int {
        var index = 0
        input.toCharArray().toList().windowed(matchCount) { list ->
            if (index == 0 && list.distinct().size == matchCount) {
                index = input.indexOf(list.joinToString(""))
            }
        }

        return index + matchCount
    }

}



