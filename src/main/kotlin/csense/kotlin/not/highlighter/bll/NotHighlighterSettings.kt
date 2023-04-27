package csense.kotlin.not.highlighter.bll

import com.intellij.openapi.editor.markup.*
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.settings.*

val NotHighlighterSettings.isDisabled: Boolean
    get() = !isEnabled

fun NotHighlighterSettings.toTextAttributes(): TextAttributes =
    colorFontPanelData.toTextAttributes()

fun NotHighlighterSettings.toHighlighterStrategy(): AnnotationHolderHighlighterStrategy {
    return AnnotationHolderHighlighterStrategy(
        textHighlightDecider = toTextHighlightDecider(),
        textAttributes = toTextAttributes()
    )
}

fun NotHighlighterSettings.toTextHighlightDecider(): TextHighlightDecider {
    val textsToHighlight: List<String> = listOf(
        TextHighlightDecider.exclMarkText,
        TextHighlightDecider.notText,
    ) + TextHighlightDecider.disabledTextsOrEmpty(fromSettings = this)
    return TextHighlightDecider(namesToHighlight = textsToHighlight)
}

