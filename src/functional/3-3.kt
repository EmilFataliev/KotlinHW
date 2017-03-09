package functional

fun main(args: Array<String>) {
    val list: List<Int> = listOf(1, 2, 1, 8, 2, 3, 4, 3, 4, 5, 6, 5, 6, 7, 2)
    val set: Set<Int> = setOf(1, 2, 5, 8)
    var result = Array(set.size) { IntArray(list.size) }
    for (i in 0..result.size - 1)
        for (j in 0..result.size - 1)
            result[i][j] = -1
    set.elementAt(1)

    for (i in 0..set.size - 1) {
        for (j in 0..list.size - 1)
            if (list[j] == set.elementAt(i))
                result[i][j] = j
    }
    result.forEach {
        it.filter { it > 0 }.forEach { print("$it ") };
        print("\n")
    }
}

