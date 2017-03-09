package functional

fun readAB(varName : String) : Int {

    while (true) {
        try {
            print("Введите $varName: ")
            val result = readLine()!!.toInt()
            if (result > -1)
                return result
            else
                throw Exception();
        } catch (e: Exception) {
            println("Неверный ввод!")
        }
    }
}
fun main(args: Array<String>) {
    println("Введите строку:")
    val s: String = readLine()!!
    val a: Int  = readAB("a")
    val b: Int = readAB("b")

    if (a >= b)
        throw Exception()
    println(s.substring(a,b))
}