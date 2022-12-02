fun main() {
    val input = readInput("Day02")
    solution1(input)
    solution2(input)
}

fun solution1(input: List<String>) {
    var score = 0
    input.forEach {
        val opponent = it.first()
        val us = it.last()
        score += calculateScoreSolution1(opponent, us)
    }
    println("Result 1:$score")
}

fun solution2(input: List<String>) {
    var score = 0
    input.forEach {
        val opponent = it.first()
        val us = it.last()
        score += calculateScoreSolution2(opponent, us)
    }
    println("Result 2:$score")
}

private const val WIN = 6
private const val DRAW = 3
private const val LOOSE = 0

private const val SCORE_WITH_ROCK = 1
private const val SCORE_WITH_PAPER = 2
private const val SCORE_WITH_SCISSOR = 3

fun calculateScoreSolution1(opponent: Char, us: Char): Int {
    when (opponent) {
        'A' -> {
            return when (us) {
                'X' -> DRAW + SCORE_WITH_ROCK
                'Y' -> WIN + SCORE_WITH_PAPER
                else -> LOOSE + SCORE_WITH_SCISSOR
            }
        }

        'B' -> {
            return when (us) {
                'X' -> LOOSE + SCORE_WITH_ROCK
                'Y' -> DRAW + SCORE_WITH_PAPER
                else -> WIN + SCORE_WITH_SCISSOR
            }
        }

        else -> {
            return when (us) {
                'X' -> WIN + SCORE_WITH_ROCK
                'Y' -> LOOSE + SCORE_WITH_PAPER
                else -> DRAW + SCORE_WITH_SCISSOR
            }
        }
    }
}

fun calculateScoreSolution2(opponent: Char, us: Char): Int {
    when (opponent) {
        'A' -> { //ROCK
            return when (us) {
                'X' -> LOOSE + SCORE_WITH_SCISSOR
                'Y' -> DRAW + SCORE_WITH_ROCK
                else -> WIN + SCORE_WITH_PAPER
            }
        }

        'B' -> { //PAPER
            return when (us) {
                'X' -> LOOSE + SCORE_WITH_ROCK
                'Y' -> DRAW + SCORE_WITH_PAPER
                else -> WIN + SCORE_WITH_SCISSOR
            }
        }

        else -> { //SCISSOR
            return when (us) {
                'X' -> LOOSE + SCORE_WITH_PAPER
                'Y' -> DRAW + SCORE_WITH_SCISSOR
                else -> WIN + SCORE_WITH_ROCK
            }
        }
    }
}