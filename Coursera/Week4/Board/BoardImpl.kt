package board

import board.Direction.*
import java.lang.IllegalArgumentException

fun createSquareBoard(width: Int): SquareBoard {

    return object : SquareBoard {
        val list = initBoard()
        override val width: Int
            get() = width

        fun initBoard(): Collection<Cell> {
            val list = mutableListOf<Cell>()

            for (i in 1..width) {
                for (j in 1..width) {
                    list += Cell(i, j)
                }
            }

            return list
        }

        override fun getCellOrNull(i: Int, j: Int): Cell? = list.find { it.i == i && it.j == j }

        override fun getCell(i: Int, j: Int): Cell = list.find { it.i == i && it.j == j }
                ?: throw IllegalArgumentException()

        override fun getAllCells(): Collection<Cell> {
            return list
        }

        override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
            var list = mutableListOf<Cell>()

            if (i <= width) {
                for (j in jRange) {
                    getCellOrNull(i, j)?.let { list.add(it) }
                }
            }

            return list

        }

        override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
            val list = mutableListOf<Cell>()

            if (j <= width) {
                for (i in iRange) {
                    getCellOrNull(i, j)?.let { list.add(it) }
                }
            }

            return list
        }

        override fun Cell.getNeighbour(direction: Direction): Cell? {
            var cell: Cell? = null

            when (direction) {
                UP -> if (i > 1) cell = getCellOrNull(i - 1, j)
                LEFT -> if (j > 1) cell = getCellOrNull(i, j - 1)
                RIGHT -> if (j < width) cell = getCellOrNull(i, j + 1)
                DOWN -> if (i < width) cell = getCellOrNull(i + 1, j)
            }

            return cell
        }
    }
}


fun <T> createGameBoard(width: Int): GameBoard<T> {
    val board = createSquareBoard(width)
    val allCells = board.getAllCells()

    return object : GameBoard<T> {
        val map = init()
        override val width: Int
            get() = board.width

        fun init(): MutableMap<Cell, T?> {
            val map = mutableMapOf<Cell, T?>()

         for (element in allCells){
             map[element] = null
         }

            return map
        }

        override fun getCellOrNull(i: Int, j: Int): Cell? {
            return board.getCellOrNull(i, j)
        }

        override fun getCell(i: Int, j: Int): Cell {
            return board.getCell(i, j)
        }

        override fun getAllCells(): Collection<Cell> {
            return board.getAllCells()
        }

        override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
            return board.getRow(i, jRange)
        }

        override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
            return board.getColumn(iRange, j)
        }

        //???????? how to call it from board
        override fun Cell.getNeighbour(direction: Direction): Cell? {
            var cell: Cell? = null

            when (direction) {
                UP -> if (i > 1) getCellOrNull(i - 1, j)
                LEFT -> if (j > 1) cell = getCellOrNull(i, j - 1)
                RIGHT -> if (j < width) cell = getCellOrNull(i, j + 1)
                DOWN -> if (i < width) cell = getCellOrNull(i + 1, j)
            }

            return cell
        }

        override fun get(cell: Cell): T? {
           return map[cell]
        }

        override fun set(cell: Cell, value: T?) {
            map[cell] = value
        }

        override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
           return map.filterValues(predicate).keys
        }

        override fun find(predicate: (T?) -> Boolean): Cell? {
            return map.filterValues(predicate).keys.first()
        }

        override fun any(predicate: (T?) -> Boolean): Boolean {
            return map.filterValues(predicate).keys.any()
        }

        override fun all(predicate: (T?) -> Boolean): Boolean {
            return map.values.all(predicate)
        }

    }
}

