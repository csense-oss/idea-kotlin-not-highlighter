package csense.kotlin.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.highligter.elementHighligter.*

object DisabledHighlighter : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return false
    }

    override fun highlight(holder: AnnotationHolder) {
        noOp()
    }
}