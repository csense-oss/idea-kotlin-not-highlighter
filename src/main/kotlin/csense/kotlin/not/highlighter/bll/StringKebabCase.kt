package csense.kotlin.not.highlighter.bll

import csense.kotlin.extensions.primitives.*

@JvmInline
value class CharKebabCase(val char: Char) {
    companion object
}

inline val Char.kebabCase: CharKebabCase
    get() = CharKebabCase(this)

enum class KebabCaseBreakChar {
    UpperCase,
    LowerCase,
    Symbol
}

fun CharKebabCase.breakOnDifferent(shouldKeep: KebabCaseBreakChar?): Boolean {
    shouldKeep ?: return false
    val thisKebab = toKebabCaseBreakChar()
    return thisKebab != shouldKeep
}

fun CharKebabCase.toKebabCaseBreakChar(): KebabCaseBreakChar = when {
    char.isLowerCaseLetter() -> KebabCaseBreakChar.LowerCase
    char.isUpperCaseLetter() -> KebabCaseBreakChar.UpperCase
    else -> KebabCaseBreakChar.Symbol
}

@JvmInline
value class StringKebabCase(val string: String) {
    companion object
}

val String.kebabCase: StringKebabCase
    get() = StringKebabCase(this)


//for the starting "getOrNull(1)":
//the logic here is there are 2 cases; either we have uppercase starting or lowercase. (symbol should be excluded)
//thus if it is uppercase and the second char is lower its say "RunMyTest"
// if it is lowercase it would be "runMyTest" and in both regards we want the full "run".
//if it is "RUNmyTest" then it would also respect that casing.


inline fun StringKebabCase.forEachKebabCase(onKebabCase: (string: String, firstIndex: Int) -> Unit) {
    val currentString = StringBuilder()
    var kebabCaseToKeep = string.getOrNull(1)?.kebabCase?.toKebabCaseBreakChar()
    string.forEachIndexed { currentIndex, c ->
        val shouldBreak = c.kebabCase.breakOnDifferent(kebabCaseToKeep)
        if (shouldBreak) {
            val firstIndexOfString = currentIndex - currentString.length
            onKebabCase(currentString.toString(), firstIndexOfString)
            currentString.clear()
            kebabCaseToKeep = string.getOrNull(currentIndex + 1)?.kebabCase?.toKebabCaseBreakChar()
        }
        currentString.append(c)
    }

    if (currentString.isNotEmpty()) {
        val firstIndexOfString = string.length - currentString.length
        onKebabCase(currentString.toString(), firstIndexOfString)
    }
}