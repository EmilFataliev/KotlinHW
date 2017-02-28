package practice

interface IMatrix {

    fun getMatrixSize(): Int
    fun getMatrix(): Array<IntArray>
    fun setMatrixDefault()

    fun turnMatrix()
    fun mirrorMatrix()
    fun transposeMatrix()
    fun getDeterminant(): Int
}

class Matrix : IMatrix {

    private var _size: Int
    private var matrix: Array<IntArray>
    private var defaultMatrix: Array<IntArray>

    init {
        _size = readSize()
        matrix = Array(_size) { IntArray(_size) }
        defaultMatrix = Array(_size) { IntArray(_size) }
        readMatrix()
        defaultMatrix = matrix
    }

    private fun readSize(): Int {
        var size: Int
        while (true) {
            try {
                print("Введите размерность матрицы N: ")
                size = readLine()!!.toInt()
                return size
            } catch (e: Exception) {
                println("Неверный ввод!")
            }
        }
    }

    private fun readMatrix() {
        println("Введите матрицу: ")
        for (i in 0.._size - 1)
            for (j in 0.._size - 1)
                while (true)
                    try {
                        print("Введите [${i + 1}, ${j + 1}] элемент: ")
                        val element = readLine()!!.toInt()
                        matrix[i][j] = element
                        break
                    } catch (e: Exception) {
                        println("Неверный ввод!")
                    }
    }


    override fun turnMatrix() {
        var temp: Int
        for (i in 0.._size / 2 - 1) {
            for (j in i.._size - 1 - i - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[_size - j - 1][i]
                matrix[_size - j - 1][i] = matrix[_size - i - 1][_size - j - 1]
                matrix[_size - i - 1][_size - j - 1] = matrix[j][_size - i - 1]
                matrix[j][_size - i - 1] = temp
            }
        }
        println("\nМатрица повернута")
    }

    override fun mirrorMatrix() {
        var column: Int
        var temp: Int
        for (i in 0.._size - 1) {
            column = _size - 1
            for (j in 0.._size / 2 - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[i][column]
                matrix[i][column] = temp
                --column
            }
        }
        println("\nМатрица отражена")
    }

    override fun transposeMatrix() {
        var temp: Int
        for (i in 0.._size - 1) {
            for (j in (i + 1).._size - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        println("\nМатрица транспонирована")
    }

    override fun getDeterminant(): Int {
        return determinant()
    }

    private fun determinant(matrix: Array<IntArray> = this.matrix, _size: Int = this._size): Int {
        var det = 0

        when (_size) {
            1 -> det = matrix[0][0]
            2 -> det = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]
            else -> {
                det = 0
                for (j1 in 0.._size - 1) {
                    val m: Array<IntArray> = Array(_size - 1) { IntArray(_size - 1) }
                    for (i in 1.._size - 1) {
                        (0.._size - 1)
                                .filter { it != j1 }
                                .forEachIndexed { j2, j -> m[i - 1][j2] = matrix[i][j] }
                    }
                    det += (Math.pow(-1.0, 1.0 + j1 + 1) * matrix[0][j1] * determinant(m, _size - 1)).toInt()
                }
            }
        }

        return det
    }

    override fun getMatrix(): Array<IntArray> {
        return matrix
    }

    override fun getMatrixSize(): Int {
        return _size
    }

    override fun setMatrixDefault() {
        matrix = defaultMatrix
    }

    override fun toString(): String {
        val result: StringBuilder = StringBuilder()
        for (i in 0.._size - 1) {
            result.append("\n")
            for (j in 0.._size - 1)
                result.append("${matrix[i][j]} ${getSpaces(matrix[i][j].toString().length)} ")
        }

        return result.toString()
    }

    private fun getSpaces(length: Int): String {
        val string: StringBuilder = StringBuilder()
        for (i in 0..(5 - length))
            string.append(" ")

        return string.toString()
    }

}

fun main(args: Array<String>) {

    // Initialise matrix
    val matrix: Matrix = Matrix()
    println(matrix.toString())

    // Turn matrix
    matrix.turnMatrix()
    println(matrix.toString())

    // Mirror matrix
    matrix.mirrorMatrix()
    println(matrix.toString())

    // Transpose matrix
    matrix.transposeMatrix()
    println(matrix.toString())

    // Set matrix in initial state and calculate determinant
    matrix.setMatrixDefault()
    print("Определитель матрицы = ")
    println(matrix.getDeterminant())

}