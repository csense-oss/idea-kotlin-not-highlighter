package csense.kotlin.not.highlighter.highligter.elementHighligter

import com.intellij.lang.annotation.*
import csense.kotlin.not.highlighter.bll.*

object DisabledHighlighter : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return false
    }

    override fun highlight(holder: AnnotationHolder) {
        noOp()
    }
}