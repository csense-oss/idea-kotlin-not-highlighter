package csense.kotlin.not.highlighter.highligter.elementHighligter

import com.intellij.lang.annotation.*
import csense.idea.base.bll.kotlin.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class KtIsExpressionHighligter(
    private val element: KtIsExpression,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy

) : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return settings.highlightOperators && element.isNegated
    }

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightRange(range = element.textRangeOfOperator(), holder = holder)
    }

}