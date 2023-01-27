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
    element is KtPrefixExpression -> element.operationReference.containsNotToken()
    element is KtNameReferenceExpression -> mayHighlight(element)
    element is KtBinaryExpression -> element.operationReference.containsNotToken()
    element is KtIsExpression -> element.isNegated
    else -> true
}

//TODO move
fun KtSimpleNameExpression.containsNotToken(): Boolean {
    return getReferencedNameElementType() in setOf(
        KtTokens.NOT_IN,
        KtTokens.NOT_IS,
        KtTokens.EXCL,
        KtTokens.EXCLEQ,
        KtTokens.EXCLEQEQEQ
    )
}


fun NotHighlighterSettings.mayHighlight(element: KtNameReferenceExpression): Boolean {
    val isFunctionAndMayHighlight = element.isFunction() && highlightFunctionNames
    val mayHighlightVariableNames = highlightVariableNames
    return isFunctionAndMayHighlight || mayHighlightVariableNames
}

fun NotHighlighterSettings.toTextAttributes(): TextAttributes =
    colorFontPanelData.toTextAttributes()
