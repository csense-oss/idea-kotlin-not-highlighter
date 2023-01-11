package csense.kotlin.not.highlighter.settings

import com.intellij.openapi.application.*
import com.intellij.openapi.components.*
import com.intellij.psi.*
import csense.idea.base.bll.kotlin.*
import csense.kotlin.extensions.*
import org.jetbrains.kotlin.lexer.*
import org.jetbrains.kotlin.psi.*
import java.awt.*

@State(name = "CsenseNotHighlighter", storages = [(Storage("csense_not_highlighter.xml"))])
class NotHighlighterSettings : PersistentStateComponent<NotHighlighterSettings> {

    var isEnabled = true

    var foregroundColorInt: Int? = 0xBD57FF

    var backgroundColorInt: Int? = null

    var highlightVariableNames = true

    var highlightFunctionNames = true

    var italic = true

    var bold = true


    override fun loadState(state: NotHighlighterSettings) {
        this.isEnabled = state.isEnabled
        this.bold = state.bold
        this.italic = state.italic
        this.highlightFunctionNames = state.highlightFunctionNames
        this.highlightVariableNames = state.highlightVariableNames
        this.backgroundColor = state.backgroundColor
        this.foregroundColor = state.foregroundColor
    }

    override fun getState(): NotHighlighterSettings = this

    companion object {
        val instance: NotHighlighterSettings
            get() = ApplicationManager.getApplication().getService(type())
    }
}


val NotHighlighterSettings.isNotEnabled: Boolean
    get() = !isEnabled

var NotHighlighterSettings.foregroundColor: Color?
    get() = foregroundColorInt?.let { Color(it) }
    set(newValue) {
        foregroundColorInt = newValue?.rgb
    }

var NotHighlighterSettings.backgroundColor: Color?
    get() = backgroundColorInt?.let { Color(it) }
    set(newValue) {
        backgroundColorInt = newValue?.rgb
    }


fun NotHighlighterSettings.fontSettings(): Int =
    boldOrPlain() + italicOrPlain()

fun NotHighlighterSettings.boldOrPlain(): Int = bold.map(
    ifTrue = Font.BOLD,
    ifFalse = Font.PLAIN
)

fun NotHighlighterSettings.italicOrPlain(): Int = italic.map(
    ifTrue = Font.ITALIC,
    ifFalse = Font.PLAIN
)


fun NotHighlighterSettings.mayNotHighlight(element: PsiElement): Boolean {
    return !mayHighlight(element)
}

fun NotHighlighterSettings.mayHighlight(element: PsiElement): Boolean = when {
    isNotEnabled -> false
    element is KtProperty -> highlightVariableNames
    element is KtNamedDeclaration -> highlightFunctionNames
    element is KtPrefixExpression -> element.operationToken == KtTokens.EXCL
    element is KtNameReferenceExpression -> mayHighlight(element)
    else -> false
}


fun NotHighlighterSettings.mayHighlight(element: KtNameReferenceExpression): Boolean {
    val isFunctionAndMayHighlight = element.isFunction() && highlightFunctionNames
    val mayHighlightVariableNames = highlightVariableNames
    return isFunctionAndMayHighlight || mayHighlightVariableNames
}

