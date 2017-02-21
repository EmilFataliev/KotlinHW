package practice;

interface HelloWorld {
    fun print()
}

open class First : HelloWorld {
    override fun print() {
        println("Hello, world! I'm First class")
    }
}

open class Second : First() {
    override fun print() {
        println("Hello, world! I'm Second class")
    }
}

class Third : Second() {
    override fun print() {
        println("Hello, world! I'm Third class")
    }
}

fun main(args: Array<String>) {

    var obj: HelloWorld = First()

    obj.print()

    obj = Second()
    obj.print()

    obj = Third()
    obj.print()

}