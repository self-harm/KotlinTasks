package coursera

//Implement member extension functions 'record' and 'unaryPlus' so that the code below compiled and stored specified words.
//These functions should be unavailable outside of the 'Words' class.

class Words {
    private val list = mutableListOf<String>()

    operator fun String.unaryPlus() {
        list.add(this)
        //record()
    }

    fun String.record() {
        list += this
    }

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


