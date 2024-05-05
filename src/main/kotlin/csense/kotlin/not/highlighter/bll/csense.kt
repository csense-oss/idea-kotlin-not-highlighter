package csense.kotlin.not.highlighter.bll

import csense.kotlin.extensions.collections.typed.*

@Suppress("UNUSED_PARAMETER")
fun noOp(explanation: String? = null) {
}


/**
 * Does this iterable NOT contain the given string (potentially ignoring the casing)
 * @receiver [Iterable]<[String]> the collection to search tough
 * @param other [String] the string to find
 * @param ignoreCase [Boolean] if true will ignore casing
 * @return [Boolean] true if other is NOT contained (depending on ignore case) or false if contained.
 * @timecomplexity O(n)
 */
fun Iterable<String>.doesNotContain(
    other: String,
    ignoreCase: Boolean
): Boolean = !contains(
    other = other,
    ignoreCase = ignoreCase
)


fun String.startsWithWhitespace(): Boolean {
    val first: Char = firstOrNull() ?: return false
    return first.isWhitespace()
}

fun String.endsWithWhitespace(): Boolean {
    val last: Char = lastOrNull() ?: return false
    return last.isWhitespace()
}