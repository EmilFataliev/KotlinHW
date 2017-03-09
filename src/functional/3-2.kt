package functional

fun main(args: Array<String>) {
    println("Введите строку:")
    val s: String = readLine()!!
    if (StringBuilder(s).reverse().toString().equals(s))
        println("да")
    else println("нет")
}