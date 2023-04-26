package csense.kotlin.not.highlighter.bll

import com.intellij.openapi.util.TextRange
import csense.idea.base.bll.openApi.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.not.highlighter.settings.*

class TextHighlightDecider(
    private val namesToHighlight: List<String>
) {
    fun shouldNotHighlight(text: String): Boolean {
        return namesToHighlight.doesNotContain(other = text.trim(), ignoreCase = true)
    }

    fun getRangeToHighligt(text: String, originalTextRange: TextRange): TextRange {
        if (!needsTrim(text)) {
            return originalTextRange
        }
        return trimRange(text, originalTextRange)
    }

    private fun needsTrim(text: String): Boolean {
        return text.startsWithWhitespace() || text.endsWithWhitespace()
    }

    private fun trimRange(text: String, originalTextRange: TextRange): TextRange {
        val startingTrimmedIndex: Int = text.indexOfFirstOrNull { it.isNotWhitespace() } ?: 0
        val endingTrimmedLength: Int = text.indexOfLastOrNull { it.isNotWhitespace() } ?: text.length
        return TextRange(
            offset = originalTextRange.startOffset + startingTrimmedIndex,
            length = endingTrimmedLength
        )
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

