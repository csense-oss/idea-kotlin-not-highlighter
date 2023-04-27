package csense.kotlin.not.highlighter.highligter.elementHighligter

import com.intellij.lang.annotation.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class KtNamedDeclarationHighligter(
    private val element: KtNamedDeclaration,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy

) : ElementHighlighter {
    override fun mayHighlight(): Boolean =
        settings.highlightFunctionNames

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightNameIdentifier(element = element, holder = holder)
    }

}