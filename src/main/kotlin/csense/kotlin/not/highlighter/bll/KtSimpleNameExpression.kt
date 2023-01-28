package csense.kotlin.not.highlighter.bll

import org.jetbrains.kotlin.lexer.*
import org.jetbrains.kotlin.psi.*


fun KtSimpleNameExpression.containsNotToken(): Boolean {
    return getReferencedNameElementType() in setOf(
        KtTokens.NOT_IN,
        KtTokens.NOT_IS,
        KtTokens.EXCL,
        KtTokens.EXCLEQ,
        KtTokens.EXCLEQEQEQ
    )
}

