package csense.kotlin.not.highlighter

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.*
import csense.kotlin.not.highlighter.settings.*

class NotNameAnnotator : Annotator {

    private val settings: NotHighlighterSettings by lazy {
        NotHighlighterSettings.instance
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val highlighter: ElementHighlighter = getHighlighterFrom(element = element)
        if (highlighter.mayNotHighlight()) {
            return
        }
        highlighter.highlight(holder)
    }

    private fun getHighlighterFrom(element: PsiElement): ElementHighlighter {
        val highlighterStrategy: AnnotationHolderHighlighterStrategy = settings.toHighlighterStrategy()
        return ElementHighlighter.from(
            element = element,
            settings = settings,
            highlighterStrategy = highlighterStrategy
        )
    }
}

