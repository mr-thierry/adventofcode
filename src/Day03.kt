import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day03 {

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("Day03_test"))).isEqualTo("157")
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput("Day03"))).isEqualTo("8202")
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("Day03_test"))).isEqualTo("70")
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput("Day03"))).isEqualTo("2864")
    }

    private fun solution1(input: List<String>): String {
        var total = 0

        input.forEach { str ->
            val compartmentsA = str.substring(0, str.length / 2).toCharArray()
            val compartmentsB = str.substring(str.length / 2, str.length).toCharArray()

            val duplicates = mutableListOf<Char>()
            compartmentsA.forEach {
                if (compartmentsB.contains(it) && !duplicates.contains(it)) {
                    duplicates.add(it)
                }
            }

            duplicates.forEach { char ->
                total += char.alphabetValueOf()
            }
        }

        return "$total"
    }

    private fun solution2(input: List<String>): String {
        var total = 0

        var index = 0
        while (index < input.size) {
            val group1 = input[index]
            val group2 = input[index + 1]
            val group3 = input[index + 2]

            total += findDuplicate(group1, group2, group3)
            index += 3
        }

        return "$total"
    }

    private fun findDuplicate(group1: String, group2: String, group3: String): Int {
        group1.forEach { char1 ->
            group2.forEach { char2 ->
                if (char1 == char2) {
                    group3.forEach { char3 ->
                        if (char1 == char3) {
                            return char1.alphabetValueOf()
                        }
                    }
                }
            }
        }
        error("Not found")
    }

    private fun Char.alphabetValueOf(): Int {
        var value: Int = lowercaseChar().code - 'a'.code + 1
        if (isUpperCase()) value += 26
        return value
    }
}

