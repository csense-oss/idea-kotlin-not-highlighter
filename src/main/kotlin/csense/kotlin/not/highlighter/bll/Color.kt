package csense.kotlin.not.highlighter.bll

import com.intellij.ui.*
import java.awt.*

fun Color.toJbColor(): JBColor = JBColor(
    /* regular = */ this,
    /* dark = */ this.darker()
)