package csense.kotlin.not.highlighter.highligter

import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.idea.base.bll.*
import csense.idea.base.bll.string.*
import csense.kotlin.not.highlighter.bll.*
import org.jetbrains.kotlin.psi.*

class AnnotationHolderHighlighterStrategy(
    private val namesToHighlight: List<String>,
    private val textAttributes: TextAttributes
) {
    fun highlightNameIdentifier(element: KtNamedDeclaration, holder: AnnotationHolder) {
        val nameIdentifier: PsiElement = element.nameIdentifier ?: return
        highlightTextIn(element = nameIdentifier, holder = holder)
    }

    fun highlightTextIn(
        element: PsiElement,
        holder: AnnotationHolder
    ) {
        element.camelCase.forEachCamelCaseWordWithTextRange { range: TextRange, string: String ->
            if (string.shouldNotHighlight()) {
                return@forEachCamelCaseWordWithTextRange
            }
            highlightRange(
                range = range,
                holder = holder
            )
        }
    }

    fun highlightRange(range: TextRange, holder: AnnotationHolder) {
        holder.highlightTextRange(range = range, withStyle = textAttributes)
    }


    private fun String.shouldNotHighlight(): Boolean {
        return namesToHighlight.doesNotContain(other = this, ignoreCase = true)
    }
}
