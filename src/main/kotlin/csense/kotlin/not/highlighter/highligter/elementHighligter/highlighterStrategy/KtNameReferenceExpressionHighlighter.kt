package csense.kotlin.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import csense.idea.base.bll.kotlin.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.settings.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class KtNameReferenceExpressionHighlighter(
    private val element: KtNameReferenceExpression,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy
) : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        val isFunctionAndMayHighlight: Boolean = element.isFunction() && settings.highlightFunctionNames
        val mayHighlightVariableNames: Boolean = settings.highlightVariableNames
        return isFunctionAndMayHighlight || mayHighlightVariableNames
    }

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightTextIn(
            element = element,
            holder = holder,
            setting = HighlighterSettings.NameSettings
        )
    }
}