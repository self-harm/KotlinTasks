package coursera

//Implement the function that builds a sequence of Fibonacci numbers using 'sequence' function. Use 'yield'.

fun fibonacci(): Sequence<Int> = sequence {
    var a = 0
    var b = 1

    yield(a)
    yield(b)

    while (true) {
        var sum = a + b
        yield(sum)
        a = b
        b = sum
    }
    
/*  solution from coursera:

    var elements = Pair(0, 1)
    while (true) {
        yield(elements.first)
        elements = Pair(
            elements.second,
            elements.first + elements.second
        )
    }*/
}

fun main(args: Array<String>) {
    fibonacci().take(4).toList().toString() eq
            "[0, 1, 1, 2]"

    fibonacci().take(10).toList().toString() eq
            "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
}
