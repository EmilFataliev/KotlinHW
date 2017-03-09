package functional

import java.util.*

fun readN(): Int {

    while (true) {
        try {
            print("Введите N: ")
            val result = readLine()!!.toInt()
            if (result > 0)
                return result
            else
                throw Exception();
        } catch (e: Exception) {
            println("Неверный ввод!")
        }
    }
}

fun generateNPosition(n: Int): List<Int> {
    var result: MutableList<Int> = mutableListOf()
    var rand = Random()
    for (i in 0..n)
        result.add(rand.nextInt(n))
    return result
}

fun main(args: Array<String>) {

    val list = listOf(5,7,11,12,14,33,124,421,11,2,101,75,28,63)
    val N = readN()
    val positions = generateNPosition(N)

    var meanList: MutableList<Double>  = mutableListOf()
    var mean = 0

    for (i in 0..N) {
        positions.
                forEach {
                    mean += list[it]
                    if (it == positions.last())
                        mean /= N
                }
        Collections.shuffle(list)

        meanList.add(mean.toDouble())
        mean = 0
    }
    println(meanList.average())
}