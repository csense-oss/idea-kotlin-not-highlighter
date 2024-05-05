package csense.kotlin.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.settings.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class KtPropertyHighligter(
    private val element: KtProperty,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy
) : ElementHighlighter {
    override fun mayHighlight(): Boolean =
        settings.highlightVariableNames

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightNameIdentifier(
            element = element,
            holder = holder,
            setting = HighlighterSettings.NameSettings
        )
    }
}