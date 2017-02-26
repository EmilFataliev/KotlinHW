package practice


fun delim(c: Char): Boolean {
    return c == ' '
}

fun is_op(c: Char): Boolean {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '%'
}

fun priority(op: Char): Int {
    return if (op == '+' || op == '-') 1 else if (op == '*' || op == '/' || op == '%') 2 else -1

}

fun process_op(st: MutableList<Double>, op: Char): MutableList<Double> {
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

fun calc(s: String): Double {
    var st: MutableList<Double> =  mutableListOf()
    var op: MutableList<Char> = mutableListOf()
    var i = 0
    while (i < s.length) {
        if (!delim(s[i]))
            if (s[i] == '(')
                op.add('(')
            else if (s[i] == ')') {
                while (op[op.size - 1] != '(') {
                    st = process_op(st, op[op.size - 1])
                    op.removeAt(op.size - 1)
                }
                op.removeAt(op.size - 1)
            } else if (is_op(s[i])) {
                var curop: Char = s[i]
                while (!op.isEmpty() && priority(op[op.size - 1]) >= priority(s[i])) {
                    st = process_op(st, op[op.size - 1])
                    op.removeAt(st.size - 1)
                }
                op.add(curop)
            } else {
                var operand: String = ""
                while (i < s.length && Character.isLetterOrDigit(s[i]) || i < s.length && s[i] == '.') {
                    operand += s[i++]
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


fun main(args: Array<String>) {
    val expression: List<String> = listOf("(5+3)/2 - 2.5 + 0.5", "((5*3) - 5)/2 - 2", "(10 - 3.25)*100", "((5%3) - 2)","(((15/5)*3) + 1)*2 - 4.5")
    val results: List<Double> = listOf(2.0,3.0,625.0,0.0,15.5)
    var counter = 0
    for (i in expression) {
        try {
            assert(calc(i) == results[counter])
        } catch(e: ArithmeticException) {
            println("Деление на ноль!")
        }
        println("$i = ${results[counter]}")
        ++counter
    }

}