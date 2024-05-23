package csense.kotlin.not.highlighter.bll

import com.intellij.openapi.project.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.repo.*
import csense.kotlin.not.highlighter.settings.*

val NotHighlighterSettings.isDisabled: Boolean
    get() = !isEnabled


fun NotHighlighterSettings.toHighlighterStrategy(project: Project): AnnotationHolderHighlighterStrategy {

    return AnnotationHolderHighlighterStrategy(
        textHighlightDecider = toTextHighlightDecider(project)
    )
}

fun NotHighlighterSettings.toTextHighlightDecider(project: Project): TextHighlightDecider {
    val texts: List<String> = NamesHighlighterRepo.from(project).getNames(this)
    return TextHighlightDecider(namesToHighlight = texts)
}