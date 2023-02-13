package csense.kotlin.not.highlighter.bll

import com.intellij.openapi.editor.markup.*
import com.intellij.psi.*
import com.intellij.ui.*
import com.intellij.util.ui.*
import csense.idea.base.bll.kotlin.*
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.not.highlighter.settings.*
import org.jetbrains.kotlin.lexer.*
import org.jetbrains.kotlin.psi.*

val NotHighlighterSettings.isNotEnabled: Boolean
    get() = !isEnabled

fun NotHighlighterSettings.mayNotHighlight(element: PsiElement): Boolean {
    return !mayHighlight(element)
}

fun NotHighlighterSettings.mayHighlight(element: PsiElement): Boolean = when {
    isNotEnabled -> false
    element is KtProperty -> highlightVariableNames
    element is KtNamedDeclaration -> highlightFunctionNames
    element is KtPrefixExpression -> highlightOperators && element.operationReference.containsNotToken()
    element is KtNameReferenceExpression -> mayHighlight(element)
    element is KtBinaryExpression -> highlightOperators && element.operationReference.containsNotToken()
    element is KtIsExpression -> highlightOperators && element.isNegated
    else -> true
}

fun NotHighlighterSettings.mayHighlight(element: KtNameReferenceExpression): Boolean {
    val isFunctionAndMayHighlight: Boolean = element.isFunction() && highlightFunctionNames
    val mayHighlightVariableNames: Boolean = highlightVariableNames
    return isFunctionAndMayHighlight || mayHighlightVariableNames
}

fun NotHighlighterSettings.toTextAttributes(): TextAttributes =
    colorFontPanelData.toTextAttributes()
