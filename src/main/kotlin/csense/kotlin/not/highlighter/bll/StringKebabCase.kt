package csense.kotlin.not.highlighter.bll

import csense.kotlin.extensions.primitives.*

//TODO csense base or new module?
//TODO document this...
//for the starting "getOrNull(1)":
//the logic here is there are 2 cases; either we have uppercase starting or lowercase. (symbol should be excluded)
//thus if it is uppercase and the second char is lower its say "RunMyTest"
// if it is lowercase it would be "runMyTest" and in both regards we want the full "run".
//if it is "RUNmyTest" then it would also respect that casing.

inline fun String.forEachKebabCase(onKebabCase: (string: String, firstIndex: Int) -> Unit) {
    val currentString = StringBuilder()
    var kebabCaseToKeep = getOrNull(1)?.toKebabCaseBreakChar()
    forEachIndexed { currentIndex, c ->
        val shouldBreak = c.breakOnDifferent(kebabCaseToKeep)
        if (shouldBreak) {
            val firstIndexOfString = currentIndex - currentString.length
            onKebabCase(currentString.toString(), firstIndexOfString)
            currentString.clear()
            kebabCaseToKeep = getOrNull(currentIndex + 1)?.toKebabCaseBreakChar()
        }
        currentString.append(c)
    }

    if (currentString.isNotEmpty()) {
        val firstIndexOfString = length - currentString.length
        onKebabCase(currentString.toString(), firstIndexOfString)
    }
}

enum class KebabCaseBreakChar {
    UpperCase,
    LowerCase,
    Symbol
}

fun Char.breakOnDifferent(shouldKeep: KebabCaseBreakChar?): Boolean {
    if (shouldKeep == null) {
        return false
    }
    val thisKebab = toKebabCaseBreakChar()
    return thisKebab != shouldKeep
}

fun Char.toKebabCaseBreakChar(): KebabCaseBreakChar = when {
    isLowerCaseLetter() -> KebabCaseBreakChar.LowerCase
    isUpperCaseLetter() -> KebabCaseBreakChar.UpperCase
    else -> KebabCaseBreakChar.Symbol
}