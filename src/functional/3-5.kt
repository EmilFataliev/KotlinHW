import java.util.*

fun <T> getPermutation(set: Set<T>): ArrayList<ArrayList<T>> {
    val resultList = arrayListOf(arrayListOf<T>())
    set.forEach {
        val newElements = arrayListOf<ArrayList<T>>()
        for (resItem in resultList) {
            newElements.add(ArrayList(resItem))
            newElements.last().add(it)
        }
        resultList.addAll(newElements)
    }
    resultList.sortBy { it.size }
    return resultList
}

fun Set<Number>.printReplacements() : Unit = println(getPermutation(this))

fun main(args: Array<String>) {
    val set: Set<Int> = setOf(1,2,3,4,5)
    set.printReplacements()
}