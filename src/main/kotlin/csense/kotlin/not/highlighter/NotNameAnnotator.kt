package csense.kotlin.not.highlighter

import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.idea.base.bll.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.bll.StringBll.Companion.notText
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.lexer.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.*
import java.awt.*

class NotNameAnnotator : Annotator {

    private val settings = NotHighlighterSettings.instance


    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (settings.mayNotHighlight(element)) {
            return
        }

        when (element) {
            is KtNamedDeclaration -> element.highlight(holder)
            is KtNameReferenceExpression -> element.highlight(holder)
            is KtPrefixExpression -> element.highlight(holder)
        }
    }

    private fun KtNamedDeclaration.highlight(holder: AnnotationHolder) {
        highlightNotIn(element = nameIdentifier ?: return, holder = holder)
    }

    private fun KtNameReferenceExpression.highlight(holder: AnnotationHolder) {
        highlightNotIn(element = this, holder = holder)
    }

    private fun KtPrefixExpression.highlight(holder: AnnotationHolder) {
        if (operationToken != KtTokens.EXCL) {
            return
        }
        val elementRange = TextRange(
            /* startOffset = */ textRange.startOffset,
            /* endOffset = */ textRange.startOffset + exclamationMark.length
        )
        highlightRange(range = elementRange, holder = holder)
    }


    private fun highlightNotIn(
        element: PsiElement,
        holder: AnnotationHolder,
        length: Int = notText.length
    ) {
        val notStart = element.indexOfNotOrNull() ?: return
        val realNotStart = element.textRange.startOffset + notStart
        highlightRange(
            range = TextRange(
                /* startOffset = */ realNotStart,
                /* endOffset = */ realNotStart + length
            ),
            holder = holder
        )
    }

    private fun highlightRange(range: TextRange, holder: AnnotationHolder) {
        val settings = NotHighlighterSettings.instance
        holder.newSilentAnnotation(
            /* severity = */ HighlightSeverity.INFORMATION
        ).range(
            /* range = */ range
        ).enforcedTextAttributes(
            TextAttributes(
                /* foregroundColor = */  settings.foregroundColor,
                /* backgroundColor = */ settings.backgroundColor,
                /* effectColor = */ null,
                /* effectType = */ null,
                /* fontType = */ settings.fontSettings()
            )
        ).create()
    }


    private fun PsiElement.indexOfNotOrNull(): Int? {
        return text.bll.indexOfNotOrNull()
    }

    companion object {
        const val exclamationMark = "!"
    }
}


