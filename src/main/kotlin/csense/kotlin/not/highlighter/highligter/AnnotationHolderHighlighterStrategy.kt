package csense.kotlin.not.highlighter.highligter

import com.intellij.lang.annotation.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.idea.base.bll.*
import csense.idea.base.bll.string.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.settings.*
import org.jetbrains.kotlin.psi.*

class AnnotationHolderHighlighterStrategy(
    private val textHighlightDecider: TextHighlightDecider
) {
    fun highlightNameIdentifier(
        element: KtNamedDeclaration,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        val nameIdentifier: PsiElement = element.nameIdentifier ?: return
        highlightTextIn(
            element = nameIdentifier,
            holder = holder,
            setting = setting
        )
    }

    fun highlightTextIn(
        element: PsiElement,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        element.camelCase.forEachCamelCaseWordWithTextRange { originalRange: TextRange, string: String ->
            if (textHighlightDecider.shouldNotHighlight(string)) {
                return@forEachCamelCaseWordWithTextRange
            }

            val rangeToHighlight: TextRange = textHighlightDecider.getRangeToHighligt(
                text = string,
                originalTextRange = originalRange
            )

            highlightRange(
                range = rangeToHighlight,
                holder = holder,
                setting = setting
            )
        }
    }

    fun highlightRange(
        range: TextRange,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        holder.highlightTextRange(
            range = range,
            withStyle = setting.textAttributes()
        )
    }
}