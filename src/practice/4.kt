package practice

interface IMatrix {
    fun getMatrixSize(): Int
    fun getMatrix(): Array<IntArray>

    fun turnMatrix()
    fun mirrorMatrix()
    fun transposeMatrix()
    fun getDeterminant(): Int
}

class Matrix : IMatrix {

    private var _size: Int
    private var matrix: Array<IntArray>

    init {
        _size = readSize()
        matrix = Array(_size) { IntArray(_size) }
        readMatrix()
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
        println("Матрица повернута")
    }

    // TODO : отражение матрицы
    override fun mirrorMatrix() {
        var temp: Int
        for (i in 0.._size - 1) {
            for (j in 0.._size / 2 - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[i][j + _size%2 + 1]
                matrix[i][j + _size%2 + 1] = temp
            }
        }
        println("Матрица отражена")
    }

    override fun transposeMatrix() {
        var temp: Int
        for (i in 0.._size - 1) {
            for (j in (i+1).._size - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        println("Матрица транспонирована")
    }

    override fun getDeterminant(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                        print("Введите [$i, $j] элемент: ")
                        val element = readLine()!!.toInt()
                        matrix[i][j] = element
                        break
                    } catch (e: Exception) {
                        println("Неверный ввод!")
                    }


    }

    override fun getMatrix(): Array<IntArray> {
        return matrix
    }

    override fun getMatrixSize(): Int {
        return _size
    }

    override fun toString(): String {
        val result: StringBuilder = StringBuilder()
        for (i in 0.._size - 1) {
            result.append("\n")
            for (j in 0.._size - 1)
                result.append("${matrix[i][j]} ")
        }

        return result.toString()
    }

}

fun main(args: Array<String>) {
    val matrix: Matrix = Matrix()
    println(matrix.toString())
    matrix.turnMatrix()
    println(matrix.toString())
    matrix.mirrorMatrix()
    println(matrix.toString())
    matrix.transposeMatrix()
    println(matrix.toString())
}