//Change the 'sum' function so that it was declared as an extension to List<Int>:

// fun sum(list: List<Int>): Int {
//     var result = 0
//     for (i in list) {
//         result += i
//     }
//     return result
// }

// fun main(args: Array<String>) {
//     val sum = sum(listOf(1, 2, 3))
//     println(sum)    // 6
// }

//solution:

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {
//    val sum = sum(listOf(1, 2, 3))
    val list = listOf(1, 2, 3)
    val sum = list.sum()
    println(sum)    // 6
}
