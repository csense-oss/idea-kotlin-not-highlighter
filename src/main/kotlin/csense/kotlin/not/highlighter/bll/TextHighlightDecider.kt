package csense.kotlin.not.highlighter.bll

import csense.kotlin.not.highlighter.settings.*

class TextHighlightDecider(
    private val namesToHighlight: List<String>
) {
    fun shouldNotHighlight(text: String): Boolean {
        return namesToHighlight.doesNotContain(other = text, ignoreCase = true)
    }

    companion object {
        const val notText: String = "not"
        const val disableText: String = "disable"
        const val disabledText: String = "disabled"
    }
}

fun TextHighlightDecider.Companion.disabledTextsOrEmpty(fromSettings: NotHighlighterSettings): List<String> {
    if (!fromSettings.highlightDisabledText) {
        return emptyList()
    }
    return listOf(
        disableText,
        disabledText
    )
}

