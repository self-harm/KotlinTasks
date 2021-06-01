//Implement the function that checks whether a string is a valid identifier.
//A valid identifier is a non-empty string that starts with a letter or underscore and consists of only letters, digits and underscores.

fun isValidIdentifier(s: String): Boolean {
    if(s.isEmpty() || s[0].isDigit()) return false
    for (c in s) {
        if (!(c == '_' || c.isLetterOrDigit())) return false
    }

    return true
}

fun isValidIdentifier1(s: String): Boolean {
    if (s.isEmpty() || s[0] in '0'..'9') return false
    for (c in s) {
        if (!(c == '_' || c in '0'..'9' || c in 'a'..'Z' || c in 'A'..'Z')) return false
    }

    return true
}

fun isValidIdentifier2(s: String): Boolean {
    if (s.isEmpty() || s[0].toInt() in 48..57) return false
    for (c in s) {
        var symbol = c.toInt()
        if (!(c == '_'
                        || symbol in 48..57
                        || symbol in 97..122
                        || symbol in 65..90)) return false
    }

    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier2("name"))   // true
    println(isValidIdentifier2("_name"))  // true
    println(isValidIdentifier2("_12"))    // true
    println(isValidIdentifier2(""))       // false
    println(isValidIdentifier2("012"))    // false
    println(isValidIdentifier2("no$"))    // false
}
