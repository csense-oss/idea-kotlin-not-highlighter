package csense.kotlin.not.highlighter.highligter.elementHighligter

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.highligter.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.highlighterStrategy.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.psi.*

interface ElementHighlighter {

    fun mayHighlight(): Boolean
    fun highlight(holder: AnnotationHolder)

    companion object
}


fun ElementHighlighter.mayNotHighlight(): Boolean =
    !mayHighlight()

fun ElementHighlighter.Companion.from(
    element: PsiElement,
    settings: NotHighlighterSettings,
    highlighterStrategy: AnnotationHolderHighlighterStrategy
): ElementHighlighter = when (settings.isDisabled) {
    true -> DisabledHighlighter
    false -> highlighterBy(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )
}

private fun ElementHighlighter.Companion.highlighterBy(
    element: PsiElement,
    settings: NotHighlighterSettings,
    highlighterStrategy: AnnotationHolderHighlighterStrategy
): ElementHighlighter = when (element) {
    is KtProperty -> KtPropertyHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtNamedDeclaration -> KtNamedDeclarationHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtPrefixExpression -> KtPrefixExpressionHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtNameReferenceExpression -> KtNameReferenceExpressionHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtBinaryExpression -> KtBinaryExpressionHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtIsExpression -> KtIsExpressionHighligter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiComment -> PsiCommentHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is KtStringTemplateEntry -> KtStringTemplateEntryHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    else -> DisabledHighlighter
}