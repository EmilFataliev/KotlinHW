class Graph(v: Int) {

    enum class GraphColors {Red, Blue, Black, Yellow, Green, White, Brown}
    val colorsList: List<GraphColors> = listOf(GraphColors.Red, GraphColors.Blue, GraphColors.Black, GraphColors.Yellow, GraphColors.Green, GraphColors.White, GraphColors.Brown)

    var graphCharacter: Map<Int, GraphColors> = mapOf()

    init {
        graphCharacter.plus(Pair(0, GraphColors.Red))
        for (vIt in 1..v) {
            graphCharacter.plus(Pair(vIt, colorsList.firstOrNull { !(graphCharacter.values.contains(it)) }))

        }
    }

    fun printGraph()
    {
        graphCharacter.forEach {
            println(it.key.toString() + " " + it.value.toString())
        }
    }

}
fun main(args: Array<String>) {
    val graph = Graph(5)
    graph.printGraph()
}