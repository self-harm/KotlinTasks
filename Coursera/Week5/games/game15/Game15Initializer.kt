package games.gameOfFifteen

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val range = 1..15
        var list = mutableListOf<Int>()

        for (i in range) list.add(i)
        list = list.shuffled() as MutableList<Int>

        if (!isEven(list)) {
            val value = list.first()
            list[0] = list.last()
            list[14] = value
            return@lazy list
        }

        return@lazy list
    }
}

