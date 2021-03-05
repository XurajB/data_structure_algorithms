package problems.kotlin

import kotlin.collections.ArrayDeque

fun main() {
    isValid("(()][")
}

fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    for (c in s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.addFirst(c)
        } else {
            if (stack.isEmpty() || getOpen(c) != stack.first()) {
                return false
            }
            stack.removeFirst()
        }
    }
    return stack.isEmpty()
}

fun getOpen(c: Char): Char {
    if (c == ')') {
        return '('
    } else if (c == '}') {
        return '{'
    }
    return '['
}