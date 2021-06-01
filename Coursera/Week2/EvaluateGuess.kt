data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    fun calculateRightPositions(): Int {
        var rightPosition = 0
        for (i in 0..3) {
            if (guess[i] == secret[i]) rightPosition++
        }
        return rightPosition
    }

    fun calculateWrongPositions(): Int {
        val guessList = mutableListOf<Char>()
        val secretList = mutableListOf<Char>()
        var wrongPosition = 0

        if (calculateRightPositions() == 4) return wrongPosition

        for (i in 0..3) {
            if (guess[i] != secret[i]) {
                guessList.add(guess[i])
                secretList.add(secret[i])
            }
        }

        for (i in 0 until guessList.size) {
            if (guessList[i] in secretList) wrongPosition++
            secretList.remove(guessList[i])
        }

        return wrongPosition
    }

    return Evaluation(calculateRightPositions(), calculateWrongPositions())
}
