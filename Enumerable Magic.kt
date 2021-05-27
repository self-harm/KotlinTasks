//Create a method that accepts a list and an item, and returns true if the item belongs to the list, otherwise false.

fun include(arr: IntArray, item: Int): Boolean {
    for (each in arr) {
        if (each.equals(item)) return true
    }
    return false
}

//better:
val include = IntArray::contains
