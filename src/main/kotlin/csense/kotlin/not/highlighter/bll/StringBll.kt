package csense.kotlin.not.highlighter.bll


fun String.indexOfNotOrNull(): Int? {
    forEachKebabCase { string, index ->
        if (string.equals("not", ignoreCase = true)) {
            return@indexOfNotOrNull index
        }
    }
    return null
}