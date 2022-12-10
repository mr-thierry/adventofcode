import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DayTemplate {

    private val day = "Day10"

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
        val actual = solution2(readInput("${day}_test"))
        assertThat(actual[0]).isEqualTo("##..##..##..##..##..##..##..##..##..##..")
        assertThat(actual[1]).isEqualTo("###...###...###...###...###...###...###.")
        assertThat(actual[2]).isEqualTo("####....####....####....####....####....")
        assertThat(actual[3]).isEqualTo("#####.....#####.....#####.....#####.....")
        assertThat(actual[4]).isEqualTo("######......######......######......####")
        assertThat(actual[5]).isEqualTo("#######.......#######.......#######.....")
    }

    @Test
    fun solution2() {
        val actual = solution2(readInput("$day"))
        actual.forEach {
            println(it)
        }
    }

    private fun solution1(input: List<String>): Int {
        val cycles = readCycles(input)

        var x = 1
        var sum = 0
        cycles.forEachIndexed { idx, cycle ->
            val tick = idx + 1
            if (tick == 20 || (tick - 20).mod(40) == 0) {
                println("tick:$tick x:$x value:${tick * x}")
                sum += (tick * x)
            }

            if (cycle.instruction == Instruction.ADDX) {
                x += cycle.value
            }
        }

        return sum
    }

    private fun solution2(input: List<String>): List<String> {
        val cycles = readCycles(input)
        val crt = Array(6) { Array(40) { " " } }
        var crtBeamPosition = 0
        var rowIdx = 0

        var spritePosition = 1

        cycles.forEachIndexed { idx, cycle ->
            if (crtBeamPosition == spritePosition - 1) {
                crt[rowIdx][spritePosition - 1] = "#"
            } else if (crtBeamPosition == spritePosition) {
                crt[rowIdx][spritePosition] = "#"
            } else if (crtBeamPosition == spritePosition + 1) {
                crt[rowIdx][spritePosition + 1] = "#"
            } else {
                crt[rowIdx][crtBeamPosition] = "."
            }

            if (cycle.instruction == Instruction.ADDX) {
                spritePosition += cycle.value
            }

            if ((idx + 1).mod(40) == 0) {
                //println(crt[rowIdx].joinToString(""))
                rowIdx++
                crtBeamPosition = 0
            } else {
                crtBeamPosition++
            }

        }

        return crt.map { it.joinToString("") }
    }

    private fun readCycles(input: List<String>): List<Cycle> {
        val result = mutableListOf<Cycle>()
        input.forEachIndexed { index, s ->
            val strs = s.split(" ")
            if (strs[0] == "noop") {
                result.add(Cycle(Instruction.NOOP, 0))
            } else {
                result.add(Cycle(Instruction.NOOP, 0))
                result.add(Cycle(Instruction.ADDX, strs[1].toInt()))
            }
        }
        return result
    }

    data class Cycle(val instruction: Instruction, val value: Int)

    enum class Instruction {
        NOOP, ADDX
    }
}





