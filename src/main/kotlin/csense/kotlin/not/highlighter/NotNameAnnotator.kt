package csense.kotlin.not.highlighter

import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.kotlin.extensions.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.lexer.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.*
import java.awt.*

class NotNameAnnotator : Annotator {

    private val settings = NotHighlighterSettings.instance

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (settings.isNotEnabled) {
            return
        }
        when (element) {
            is KtNamedDeclaration -> {
                if (!settings.highlightVariableNames && element is KtProperty) {
                    return
                }
                highlightNotIn(element.nameIdentifier ?: return, holder)
            }
            is KtCallExpression -> {
                element.forEachDescendantOfType<KtNameReferenceExpression> {
                    highlightNotIn(it, holder)
                }
            }

            is KtNameReferenceExpression -> {
                if (!settings.highlightVariableNames) {
                    return
                }
                highlightNotIn(element, holder)
            }
            is KtPrefixExpression -> {
                if (element.operationToken == KtTokens.EXCL) {
                    val elementRange = TextRange(
                        /* startOffset = */ element.textRange.startOffset,
                        /* endOffset = */ element.textRange.startOffset + 1
                    )
                    highlightRange(elementRange, holder)
                }
            }
        }
    }

    private fun highlightNotIn(element: PsiElement, holder: AnnotationHolder) {
        val notStart = element.indexOfNotOrNull() ?: return
        val realNotStart = element.textRange.startOffset + notStart
        highlightRange(TextRange(realNotStart, realNotStart + 3), holder)
    }

    private fun highlightRange(range: TextRange, holder: AnnotationHolder) {
        val settings = NotHighlighterSettings.instance
        val fontSettings = settings.bold.map(
            ifTrue = Font.BOLD,
            ifFalse = Font.PLAIN
        ) + settings.italic.map(
            ifTrue = Font.ITALIC,
            ifFalse = Font.PLAIN
        )
        holder.newSilentAnnotation(
            HighlightSeverity.INFORMATION
        ).range(
            range
        ).enforcedTextAttributes(
            TextAttributes(
                /* foregroundColor = */  settings.foregroundColor,
                /* backgroundColor = */ settings.backgroundColor,
                /* effectColor = */ null,
                /* effectType = */ null,
                /* fontType = */ fontSettings
            )
        ).create()
    }

    private fun PsiElement.indexOfNotOrNull(): Int? {
        return text.indexOfNotOrNull()
    }
}
