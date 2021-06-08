package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game {
    return object : Game {
        val board = createGameBoard<Int?>(4)

        override fun initialize() {
            board.initBoard(initializer)
        }

        override fun canMove(): Boolean = board.any { it == null }

        override fun hasWon(): Boolean {
            val cells = board.getAllCells()

            for (i in 1..15) {
                if (board[cells.elementAt(i - 1)] != i) return false
            }

            return true
        }

        override fun processMove(direction: Direction) {
            val nullCell = board.filter { it == null }.first()

            with(board) {
                when (direction) {
                    Direction.UP -> {
                        if (getCellOrNull(nullCell.i + 1, nullCell.j) != null) {
                            val t = get(getCell(nullCell.i + 1, nullCell.j))
                            set(nullCell, t)
                            set(getCell(nullCell.i + 1, nullCell.j), null)
                        }
                    }
                    Direction.DOWN -> {
                        if (getCellOrNull(nullCell.i - 1, nullCell.j) != null) {
                            val t = get(getCell(nullCell.i - 1, nullCell.j))
                            set(nullCell, t)
                            set(getCell(nullCell.i - 1, nullCell.j), null)
                        }
                    }
                    Direction.RIGHT -> {
                        if (getCellOrNull(nullCell.i, nullCell.j - 1) != null) {
                            val t = get(getCell(nullCell.i, nullCell.j - 1))
                            set(nullCell, t)
                            set(getCell(nullCell.i, nullCell.j - 1), null)
                        }
                    }
                    Direction.LEFT -> {
                        if (getCellOrNull(nullCell.i, nullCell.j + 1) != null) {
                            val t = get(getCell(nullCell.i, nullCell.j + 1))
                            set(nullCell, t)
                            set(getCell(nullCell.i, nullCell.j + 1), null)
                        }
                    }
                }
            }
        }

        override fun get(i: Int, j: Int): Int? = board[board.getCell(i, j)]

        fun GameBoard<Int?>.initBoard(initializer: GameOfFifteenInitializer) {
            val allCells = this.getAllCells()
            val p = initializer.initialPermutation

            for (i in allCells.indices) {
                try {
                    this[allCells.elementAt(i)] = p[i]
                } catch (e: Exception) {
                    this[allCells.elementAt(i)] = null
                }
            }
        }
    }
}
