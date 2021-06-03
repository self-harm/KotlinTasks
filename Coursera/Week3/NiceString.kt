package nicestring

fun String.isNice(): Boolean {
    var firstCondition = true
    var secondCondition = false
    var thirdCondition = false

    var counter = 0
    val setOfVowels = setOf('a', 'e', 'i', 'o', 'u')
    val listOfPairs = this.zipWithNext()

    for (ch in this) {
        if (setOfVowels.contains(ch)) counter++
    }

    for (pair in listOfPairs) {
        if ( pair.first == 'b' && (pair.second == 'u' || pair.second == 'a' || pair.second == 'e')) firstCondition =
            false
        if (any { pair.first == pair.second }) secondCondition = true
    }

    if (counter >= 3) thirdCondition = true

    return (firstCondition && secondCondition)
        || (firstCondition && thirdCondition)
        || (secondCondition && thirdCondition)
}
