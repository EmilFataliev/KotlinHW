package practice

interface Calculator
{
    fun calculate(expression : String) : Double
}

class MyCalculator: Calculator
{
    private fun delim(c: Char): Boolean {
        return c == ' '
    }

    private fun is_op(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%'
    }

    private fun priority(op: Char): Int {
        return if (op == '+' || op == '-') 1 else if (op == '*' || op == '/' || op == '%') 2 else -1

    }

    private fun process_op(st: MutableList<Double>, op: Char): MutableList<Double> {
        var r = st[st.size - 1]
        st.removeAt(st.size - 1)
        var l = st[st.size - 1]
        st.removeAt(st.size - 1)
        when (op) {
            '+' -> st.add(l + r)
            '-' -> st.add(l - r)
            '*' -> st.add(l * r)

            '%' -> st.add(l % r)
        }

        if (op == '/')
        {
            if (r == 0.0)
                throw ArithmeticException();

            st.add(l / r)
        }
        return st
    }

    override fun calculate(expression: String): Double {
        var st: MutableList<Double> =  mutableListOf()
        var op: MutableList<Char> = mutableListOf()
        var i = 0
        while (i < expression.length) {
            if (!delim(expression[i]))
                if (expression[i] == '(')
                    op.add('(')
                else if (expression[i] == ')') {
                    while (op[op.size - 1] != '(') {
                        st = process_op(st, op[op.size - 1])
                        op.removeAt(op.size - 1)
                    }
                    op.removeAt(op.size - 1)
                } else if (is_op(expression[i])) {
                    var curop: Char = expression[i]
                    while (!op.isEmpty() && priority(op[op.size - 1]) >= priority(expression[i])) {
                        st = process_op(st, op[op.size - 1])
                        op.removeAt(st.size - 1)
                    }
                    op.add(curop)
                } else {
                    var operand: String = ""
                    while (i < expression.length && Character.isLetterOrDigit(expression[i]) || i < expression.length && expression[i] == '.') {
                        operand += expression[i++]
                    }
                    --i
                    if (Character.isDigit(operand[0]))
                        st.add(operand.toDouble())
                }
            i++
        }
        while (!op.isEmpty()) {
            st = process_op(st, op[op.size - 1])
            op.removeAt(op.size - 1)

        }
        return st[st.size - 1]
    }

}

class AnotherCalculator(calc: Calculator) : Calculator by calc

fun main(args: Array<String>) {
    val expression: List<String> = listOf("(5+3)/2 - 2.5 + 0.5", "((5*3) - 5)/2 - 2", "(10 - 3.25)*100", "((5%3) - 2)","(((15/5)*3) + 1)*2 - 4.5")
    val results: List<Double> = listOf(2.0,3.0,625.0,0.0,15.5)
    var counter = 0
    val calc : MyCalculator = MyCalculator()
    for (i in expression) {
        try {
            assert(calc.calculate(i) == results[counter])
        } catch(e: ArithmeticException) {
            println("Деление на ноль!")
        }
        println("$i = ${results[counter]}")
        ++counter
    }


    println("\n Another realisation of calculator \n")
    counter = 0
    for (i in expression) {
        try {
            assert(AnotherCalculator(calc).calculate(i) == results[counter])
        } catch(e: ArithmeticException) {
            println("Деление на ноль!")
        }
        println("$i = ${results[counter]}")
        ++counter
    }

}