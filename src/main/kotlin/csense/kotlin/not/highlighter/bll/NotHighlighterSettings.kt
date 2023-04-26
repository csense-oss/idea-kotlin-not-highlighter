package csense.kotlin.not.highlighter.bll

import com.intellij.openapi.editor.markup.*
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.not.highlighter.settings.*

val NotHighlighterSettings.isDisabled: Boolean
    get() = !isEnabled

fun NotHighlighterSettings.toTextAttributes(): TextAttributes =
    colorFontPanelData.toTextAttributes()


fun NotHighlighterSettings.textsToHiglight(): List<String> {
    return listOf(notText) + disabledTextsOrEmpty()
}

//TODO clean this?
private fun NotHighlighterSettings.disabledTextsOrEmpty(): List<String> {
    if (!highlightDisabledText) {
        return emptyList()
    }
    return listOf(
        disableText,
        disabledText
    )
}

private val NotHighlighterSettings.notText: String
    get() = "not"

private val NotHighlighterSettings.disableText: String
    get() = "disable"
private val NotHighlighterSettings.disabledText: String
    get() = "disabled"