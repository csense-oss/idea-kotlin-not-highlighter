package csense.kotlin.not.highlighter

import com.intellij.lang.annotation.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.idea.base.bll.kotlin.*
import csense.idea.base.bll.string.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

class NotNameAnnotator : Annotator {

    private val settings by lazy {
        NotHighlighterSettings.instance
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (settings.mayNotHighlight(element)) {
            return
        }
        when (element) {
            is KtNamedDeclaration -> element.highlight(holder)
            is KtNameReferenceExpression -> element.highlight(holder)
            is KtPrefixExpression -> element.highlight(holder)
            is KtIsExpression -> element.highlight(holder)
            is KtBinaryExpression -> element.highlight(holder)
        }
    }

    private fun KtBinaryExpression.highlight(holder: AnnotationHolder) {
        highlightRange(range = operationReference.textRange, holder = holder)
    }

    private fun KtNamedDeclaration.highlight(holder: AnnotationHolder) {
        highlightNotIn(element = nameIdentifier ?: return, holder = holder)
    }

    private fun KtNameReferenceExpression.highlight(holder: AnnotationHolder) {
        highlightNotIn(element = this, holder = holder)
    }

    private fun KtPrefixExpression.highlight(holder: AnnotationHolder) {
        highlightRange(range = textRangeOfOperator(), holder = holder)
    }

    private fun KtIsExpression.highlight(holder: AnnotationHolder) {
        highlightRange(range = textRangeOfOperator(), holder = holder)
    }


    private fun highlightNotIn(
        element: PsiElement,
        holder: AnnotationHolder
    ) {
        val startOffsetOfText = element.textRange.startOffset

        element.text.kebabCase.forEachKebabCase { string, stringStartIndex ->
            if (!string.isNotText()) {
                return@forEachKebabCase
            }
            val notTextRange = textRangeForNotText(startOffset = startOffsetOfText + stringStartIndex)
            highlightRange(
                range = notTextRange,
                holder = holder
            )
        }
    }

    private fun String.isNotText(): Boolean {
        return equals(notText, ignoreCase = true)
    }

    private fun textRangeForNotText(startOffset: Int) = TextRange(
        /* startOffset = */ startOffset,
        /* endOffset = */ startOffset + notText.length
    )

    private fun highlightRange(range: TextRange, holder: AnnotationHolder) {
        holder
            .newSilentAnnotation(
                /* severity = */ HighlightSeverity.INFORMATION
            )
            .range(range)
            .enforcedTextAttributes(settings.toTextAttributes())
            .create()
    }

    companion object {
        const val notText: String = "not"
    }
}
