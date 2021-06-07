package coursera

//Implement member extension functions 'record' and 'unaryPlus' so that the code below compiled and stored specified words.
//These functions should be unavailable outside of the 'Words' class.

class Words {
    private val list = mutableListOf<String>()

//    private operator fun Words.unaryPlus() {
//        TODO("Not yet implemented")
//    }

    operator fun String.unaryPlus() {
        TODO("Not yet implemented")
    }
    // TODO

    override fun toString() = list.toString()
}

fun main(args: Array<String>) {
    val words = Words()
    with(words) {
        // The following two lines should compile:
        "one".record()
        +"two"
    }
    words.toString() eq "[one, two]"
}


