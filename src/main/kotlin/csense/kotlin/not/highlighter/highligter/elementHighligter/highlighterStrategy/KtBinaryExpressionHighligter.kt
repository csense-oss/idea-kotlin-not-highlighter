package csense.kotlin.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.settings.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class KtBinaryExpressionHighligter(
    private val element: KtBinaryExpression,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy

) : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return settings.highlightOperators && element.operationReference.containsNotToken()
    }

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightRange(
            range = element.operationReference.textRange,
            holder = holder,
            setting = HighlighterSettings.OperatorSettings
        )
    }
}